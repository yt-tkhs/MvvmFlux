package app.ytak.tasks.base.data.util

data class Optional<T : Any>(private var data: T?) {

    companion object {

        fun <T : Any> of(data: T) = Optional(data)

        fun <T : Any> ofNullable(data: T?) = Optional(data)

        fun <T : Any> empty() = Optional<T>(null)
    }

    fun isPresent() = data != null

    fun orNull(): T? = data

    fun get(): T = data!!
}

fun <T : Any> T?.optional() = Optional(this)