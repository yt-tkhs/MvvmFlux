package app.ytak.tasks.base.data.util.ext

import app.ytak.tasks.base.data.util.Optional
import io.reactivex.Flowable

fun <T : Any> Flowable<Optional<T>>.filterOptionalNotNull(): Flowable<T> = filter { it.isPresent() }.map { it.get() }