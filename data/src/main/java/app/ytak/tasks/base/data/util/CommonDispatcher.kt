package app.ytak.tasks.base.data.util

import io.reactivex.processors.PublishProcessor

open class CommonDispatcher {

    val errorProcessor = PublishProcessor.create<ErrorEvent>()

    fun onError(error: ErrorEvent) = errorProcessor.onNext(error)

    fun onError(action: Any, cause: Throwable, extras: Any? = null, recoverAction: (() -> Unit)? = null) =
        errorProcessor.onNext(ErrorEvent(action, cause, ErrorEvent.Kind.OTHER, extras, recoverAction))
}
