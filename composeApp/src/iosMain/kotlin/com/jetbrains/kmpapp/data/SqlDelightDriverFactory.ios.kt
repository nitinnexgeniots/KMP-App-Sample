package com.jetbrains.kmpapp.data

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver


//actual class SqlDelightDriverFactory {
//    actual fun createDriver(): SqlDriver {
//        return NativeSqliteDriver(
//            schema = Database.Schema,
//            name = "test.db"
//        )
//    }
//
//}