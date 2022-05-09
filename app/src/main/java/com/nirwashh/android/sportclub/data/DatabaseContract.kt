package com.nirwashh.android.sportclub.data

import android.provider.BaseColumns

object DatabaseContract {
    object DatabaseEntry : BaseColumns {
        const val TABLE_NAME = "members"
        const val KEY_ID = "id"
        const val KEY_FIRST_NAME = "first_name"
        const val KEY_LAST_NAME = "last_name"
        const val KEY_GENDER = "gender"
        const val KEY_SPORT = "sport"

        const val SQL_CREATE_ENTRIES =
            "CREATE TABLE $TABLE_NAME (" +
                    "$KEY_ID INTEGER PRIMARY KEY," +
                    "$KEY_FIRST_NAME TEXT," +
                    "$KEY_LAST_NAME TEXT," +
                    "$KEY_GENDER TEXT," +
                    "$KEY_SPORT TEXT)"

        const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $TABLE_NAME"

    }
}