package com.nirwashh.android.sportclub.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.nirwashh.android.sportclub.data.DatabaseContract.DatabaseEntry.SQL_CREATE_ENTRIES
import com.nirwashh.android.sportclub.data.DatabaseContract.DatabaseEntry.SQL_DELETE_ENTRIES

class DatabaseHandler(context: Context?) : SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }
    companion object {
        const val DATABASE_NAME = "SportClubDB"
        const val DATABASE_VERSION = 1
    }
}