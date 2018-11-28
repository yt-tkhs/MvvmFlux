package app.ytak.tasks.installed.app.taskdetail

import android.animation.AnimatorSet
import android.view.animation.AccelerateDecelerateInterpolator
import app.ytak.tasks.base.util.AppDuration
import app.ytak.tasks.base.util.ext.animatorAlpha
import app.ytak.tasks.base.util.ext.animatorTranslateY
import app.ytak.tasks.base.util.ext.playTogetherExt
import app.ytak.tasks.base.util.ext.setInterpolatorExt
import app.ytak.tasks.base.util.ext.setListener
import app.ytak.tasks.base.util.ext.toGone
import app.ytak.tasks.base.util.ext.toVisible
import timber.log.Timber

class TaskDetailBehavior(
    private val view: TaskDetailView
) {

    var onStateChange: ((ViewerState) -> Unit)? = null

    fun toNormal() {
        Timber.d("yt/ toNormal")
        AnimatorSet().playTogetherExt(
            view.mainLayout.animatorTranslateY(view.mainLayout.translationY, 0f),
            view.shadowView.animatorAlpha(view.shadowView.alpha, 1f)
        )
            .setDuration(AppDuration.NORMAL)
            .setInterpolatorExt(AccelerateDecelerateInterpolator())
            .setListener(onAnimationStart = {
                view.mainLayout.toVisible()
                view.shadowView.toVisible()
                view.mainLayout.alpha = 1f
                view.shadowView.alpha = 0f
            })
            .start()
    }

    fun toMini() {
        Timber.d("yt/ toMini: toY=${view.mainLayout.measuredHeight - view.context.resources.displayMetrics.density * 56}")
        AnimatorSet().playTogetherExt(
            view.mainLayout.animatorTranslateY(
                view.mainLayout.translationY,
                view.measuredHeight - view.context.resources.displayMetrics.density * 56
            ),
            view.shadowView.animatorAlpha(view.shadowView.alpha, 0f)
        )
            .setDuration(AppDuration.NORMAL)
            .setInterpolatorExt(AccelerateDecelerateInterpolator())
            .start()
    }

    fun toNone(isInstant: Boolean) {
        Timber.d("yt/ toNone: isInstant=$isInstant, measuredHeight=${view.measuredHeight}")
        AnimatorSet().playTogetherExt(
            view.mainLayout.animatorTranslateY(
                view.mainLayout.translationY,
                view.measuredHeight.toFloat()
            ),
            view.mainLayout.animatorAlpha(view.alpha, 0f),
            view.shadowView.animatorAlpha(view.shadowView.alpha, 0f)
        )
            .setDuration(if (isInstant) 0L else AppDuration.NORMAL)
            .setInterpolatorExt(AccelerateDecelerateInterpolator())
            .setListener(onAnimationEnd = {
                view.shadowView.toGone()
                view.mainLayout.toGone()
            })
            .start()
    }
}