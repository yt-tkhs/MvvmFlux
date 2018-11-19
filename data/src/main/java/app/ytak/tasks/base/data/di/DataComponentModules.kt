package app.ytak.tasks.base.data.di

import android.content.Context
import app.ytak.tasks.base.data.flux.action.TaskAction
import app.ytak.tasks.base.data.flux.store.TaskStore

interface DataComponentModules {

    fun context(): Context

    // Action
    fun taskAction(): TaskAction

    // Store
    fun taskStore(): TaskStore
}