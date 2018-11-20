package app.ytak.tasks.base.data.util

import com.squareup.moshi.Moshi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JsonConverter @Inject constructor(private val moshi: Moshi) {

    fun <T : Any> fromJson(json: String, type: Class<T>): T =
        moshi.adapter<T>(type).fromJson(json)
            ?: throw IllegalArgumentException("Failed to parse json")

    fun toJson(obj: Any): String = moshi.adapter<Any>(Any::class.java).toJson(obj)
}