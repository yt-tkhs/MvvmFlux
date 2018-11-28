package app.ytak.tasks.base.util.ext

import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.uber.autodispose.android.lifecycle.scope
import com.uber.autodispose.android.scope
import com.uber.autodispose.autoDisposable
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers

inline fun <T> Flowable<T>.observeOnMain(owner: LifecycleOwner, crossinline onNext: (T) -> Unit) {
    observeOn(AndroidSchedulers.mainThread()).autoDisposable(owner.scope()).subscribe { onNext(it) }
}

inline fun <T> Flowable<T>.observeOnMain(view: View, crossinline onNext: (T) -> Unit) {
    observeOn(AndroidSchedulers.mainThread()).autoDisposable(view.scope()).subscribe { onNext(it) }
}
