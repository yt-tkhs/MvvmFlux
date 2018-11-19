package app.ytak.tasks.base.data.di

import app.ytak.tasks.base.data.flux.action.TaskAction
import app.ytak.tasks.base.data.flux.store.TaskStore

interface DataComponentModules {

    // Action
    fun taskAction(): TaskAction

    // Store
    fun taskStore(): TaskStore
}