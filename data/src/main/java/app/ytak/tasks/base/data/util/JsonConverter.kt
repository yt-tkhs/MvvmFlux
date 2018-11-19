package app.ytak.tasks.base.data.util

import com.jsoniter.JsonIterator
import com.jsoniter.output.JsonStream

object JsonConverter {

    inline fun <reified T> fromJson(json: String) =
        JsonIterator.deserialize(json, T::class.java)

    fun toJson(obj: Any) = JsonStream.serialize(obj)
}