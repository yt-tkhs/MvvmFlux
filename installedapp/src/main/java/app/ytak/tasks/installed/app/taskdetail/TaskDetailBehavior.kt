package app.ytak.tasks.installed.app.taskdetail

import android.animation.AnimatorSet
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.core.view.animation.PathInterpolatorCompat
import app.ytak.tasks.base.util.AppDuration
import app.ytak.tasks.base.util.ext.animatorAlpha
import app.ytak.tasks.base.util.ext.animatorTranslateY
import app.ytak.tasks.base.util.ext.playTogetherExt
import app.ytak.tasks.base.util.ext.setInterpolatorExt
import app.ytak.tasks.base.util.ext.setListener
import app.ytak.tasks.base.util.ext.toGone
import app.ytak.tasks.base.util.ext.toVisible
import timber.log.Timber

class TaskDetailBehavior(private val view: TaskDetailView) {

    var onStateChange: ((ViewerState) -> Unit)? = null

    fun toNormal() {
        AnimatorSet().playTogetherExt(
            view.mainLayout.animatorTranslateY(view.mainLayout.translationY, 0f),
            view.shadowView.animatorAlpha(view.shadowView.alpha, 1f)
        )
            .setDuration(AppDuration.SLOW)
            .setInterpolatorExt(PathInterpolatorCompat.create(0.4f, 0f, 0.2f, 1f))
            .setListener(onAnimationStart = {
                view.mainLayout.toVisible()
                view.shadowView.toVisible()
                view.mainLayout.alpha = 1f
                view.shadowView.alpha = 0f
                onStateChange?.invoke(ViewerState.NORMAL)
            })
            .start()
    }

    fun toMini() {
        AnimatorSet().playTogetherExt(
            view.mainLayout.animatorTranslateY(
                view.mainLayout.translationY,
                view.measuredHeight - (view.measuredHeight * 0.2f)
            ),
            view.shadowView.animatorAlpha(view.shadowView.alpha, 0f)
        )
            .setDuration(AppDuration.SLOW)
            .setInterpolatorExt(PathInterpolatorCompat.create(0.4f, 0f, 0.2f, 1f))
            .setListener(onAnimationStart = {
                onStateChange?.invoke(ViewerState.MINI)
            })
            .start()
    }

    fun toNone(isInstant: Boolean) {
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
            .setListener(
                onAnimationStart = {
                    onStateChange?.invoke(ViewerState.NONE)
                },
                onAnimationEnd = {
                    view.shadowView.toGone()
                    view.mainLayout.toGone()
                })
            .start()
    }
}