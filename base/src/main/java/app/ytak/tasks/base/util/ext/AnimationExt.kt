package app.ytak.tasks.base.util.ext

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.TimeInterpolator
import android.view.View
import app.ytak.tasks.base.util.AppDuration


fun Any.animatorAlpha(vararg values: Float) = ObjectAnimator.ofFloat(this, View.ALPHA.name, *values)

fun Any.animatorAlpha(vararg values: Int) = ObjectAnimator.ofInt(this, View.ALPHA.name, *values)

fun Any.animatorTranslateX(vararg values: Float) = ObjectAnimator.ofFloat(this, View.TRANSLATION_X.name, *values)

fun Any.animatorTranslateY(vararg values: Float) = ObjectAnimator.ofFloat(this, View.TRANSLATION_Y.name, *values)

fun Any.animatorScaleX(vararg values: Float) = ObjectAnimator.ofFloat(this, View.SCALE_X.name, *values)

fun Any.animatorScaleY(vararg values: Float) = ObjectAnimator.ofFloat(this, View.SCALE_Y.name, *values)

fun Animator.setListener(
    onAnimationStart: (Animator) -> Unit = {}, onAnimationEnd: (Animator) -> Unit = {},
    onAnimationCancel: (Animator) -> Unit = {}, onAnimationRepeat: (Animator) -> Unit = {}
) = apply {
    addListener(object : Animator.AnimatorListener {
        override fun onAnimationStart(animation: Animator) {
            onAnimationStart(animation)
        }

        override fun onAnimationEnd(animation: Animator) {
            onAnimationEnd(animation)
        }

        override fun onAnimationCancel(animation: Animator) {
            onAnimationCancel(animation)
        }

        override fun onAnimationRepeat(animation: Animator) {
            onAnimationRepeat(animation)
        }
    })
}

fun Animator.setStartDelayExt(startDelay: Long) = apply { setStartDelay(startDelay) }

fun Animator.setInterpolatorExt(value: TimeInterpolator) = apply { interpolator = value }

fun AnimatorSet.playTogetherExt(vararg items: Animator) = apply { playTogether(*items) }

fun AnimatorSet.playTogetherFilterNotNull(vararg items: Animator?) = apply { playTogether(items.filterNotNull()) }

fun AnimatorSet.playSequentiallyExt(vararg items: Animator) = apply { playSequentially(*items) }

fun View.fadeIn(duration: Long = AppDuration.NORMAL) {
    if (isVisible() && alpha == 1f) return

    animatorAlpha(alpha, 1f)
        .setDuration(duration)
        .setListener(onAnimationStart = { toVisible() })
        .also {
            (tag as? Animator)?.run { if (isRunning) cancel() }
            tag = it
        }
        .start()
}

fun View.fadeOut(duration: Long = AppDuration.NORMAL) {
    if (!isVisible() && alpha == 0f) return

    animatorAlpha(alpha, 0f)
        .setDuration(duration)
        .setListener(onAnimationEnd = { toInvisible() })
        .also {
            (tag as? Animator)?.run { if (isRunning) cancel() }
            tag = it
        }
        .start()
}