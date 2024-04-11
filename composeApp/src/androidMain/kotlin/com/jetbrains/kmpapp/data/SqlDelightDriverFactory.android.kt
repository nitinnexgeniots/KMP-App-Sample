package com.jetbrains.kmpapp.data

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.jetbrains.kmpapp.MainApplication

//actual class SqlDelightDriverFactory actual constructor() {
//    actual fun createDriver(): SqlDriver {
//        return AndroidSqliteDriver(
//            schema = Database.Schema,
//            context = MainApplication.appContext,
//            name = "test.db"
//        )
//    }
//}