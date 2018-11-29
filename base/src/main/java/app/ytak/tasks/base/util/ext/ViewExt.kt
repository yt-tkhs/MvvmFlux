package app.ytak.tasks.base.util.ext

import android.view.View
import android.view.ViewTreeObserver

fun View.isVisible() = visibility == View.VISIBLE

fun View.isGone() = visibility == View.GONE

var View.visible: Boolean
    get() = isVisible()
    set(value) {
        if (value) toVisible() else toGone()
    }

fun View.toVisible() {
    visibility = View.VISIBLE
}

fun View.toInvisible() {
    visibility = View.INVISIBLE
}

fun View.toGone() {
    visibility = View.GONE
}

inline fun View.onPreDraw(crossinline handler: () -> Unit) {
    viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
        override fun onPreDraw(): Boolean {
            viewTreeObserver.removeOnPreDrawListener(this)
            handler()
            return true
        }
    })
}