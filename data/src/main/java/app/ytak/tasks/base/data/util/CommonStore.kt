package app.ytak.tasks.base.data.util

open class CommonStore(private val dispatcher: CommonDispatcher) {

    fun errors() = dispatcher.errorProcessor
}