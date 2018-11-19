package app.ytak.tasks.base.data.db.dao

import android.arch.persistence.db.SupportSQLiteDatabase
import android.content.ContentValues
import app.ytak.tasks.base.data.util.JsonConverter
import app.ytak.tasks.base.data.util.Optional
import app.ytak.tasks.base.data.util.SqlCursor
import app.ytak.tasks.base.data.util.SqlDao
import app.ytak.tasks.base.data.util.optional
import app.ytak.tasks.common.model.Task
import com.squareup.sqlbrite3.BriteDatabase
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskDao @Inject constructor(db: BriteDatabase) : SqlDao<TaskDao.Dto>(db) {

    object Columns {
        const val ID = "id"
        const val TASK_ID = "task_id"
        const val JSON = "json"
    }

    data class Dto(
        val taskId: String,
        val json: String,
        override val id: Int? = null
    ) : SqlDao.Dto()

    companion object {
        private const val TABLE_NAME = "tasks"

        fun createTable(db: SupportSQLiteDatabase) = db.execSQL(
            """
            CREATE TABLE $TABLE_NAME (
                ${Columns.ID} INTEGER PRIMARY KEY,
                ${Columns.TASK_ID} TEXT NOT NULL UNIQUE,
                ${Columns.JSON} TEXT NOT NULL
            )
            """.trimIndent()
        )
    }

    override fun toObject(cursor: SqlCursor): Dto = Dto(
        cursor.getString(Columns.TASK_ID),
        cursor.getString(Columns.JSON),
        cursor.getInt(Columns.ID)
    )

    override fun toContentValues(dto: Dto): ContentValues = ContentValues().apply {
        put(Columns.ID, dto.id)
        put(Columns.TASK_ID, dto.taskId)
        put(Columns.JSON, dto.json)
    }

    fun replaceAll(tasks: List<Task>) {
        db.newTransaction().run {
            try {
                deleteAll(TABLE_NAME)
                if (tasks.isNotEmpty()) tasks.forEach { upsert(it) }
                else emptyFlowable.onNext(Unit)
                markSuccessful()
            } finally {
                end()
            }
        }
    }

    fun upsert(task: Task) = insert(TABLE_NAME, task.toDto())

    fun delete(taskId: String) = delete(TABLE_NAME, "${Columns.TASK_ID}=?", taskId)

    fun find(taskId: String) = createQuery(TABLE_NAME, "SELECT * FROM $TABLE_NAME WHERE ${Columns.TASK_ID}=?", taskId)
        .map { dto -> dto.mapNotNull { JsonConverter.fromJson<Task>(it.json) }.firstOrNull().optional() }
        .mergeWith(emptyFlowable.map { Optional.empty<Task>() })

    fun findAll(): Flowable<List<Task>> = createQuery(TABLE_NAME, "SELECT * FROM $TABLE_NAME")
        .map { dto -> dto.mapNotNull { JsonConverter.fromJson<Task>(it.json) } }
        .mergeWith(emptyFlowable.map { emptyList<Task>() })

    private fun Task.toDto() = Dto(id, JsonConverter.toJson(this))
}