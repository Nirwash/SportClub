package com.nirwashh.android.sportclub.data

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.widget.Toast
import com.nirwashh.android.sportclub.data.ClubContentProvider.Companion.AUTHORITY
import com.nirwashh.android.sportclub.data.ClubContentProvider.Companion.PATH_MEMBERS
import com.nirwashh.android.sportclub.data.DatabaseContract.DatabaseEntry.KEY_ID
import com.nirwashh.android.sportclub.data.DatabaseContract.DatabaseEntry.TABLE_NAME
import java.lang.IllegalArgumentException

private const val MEMBERS = 111
private const val MEMBERS_ID = 222
private val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
    addURI(AUTHORITY, PATH_MEMBERS, MEMBERS)
    addURI(AUTHORITY, "$PATH_MEMBERS/#", MEMBERS_ID)
}

class ClubContentProvider : ContentProvider() {
    private lateinit var dbHelper: DatabaseHandler
    override fun onCreate() = true


    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        val db = dbHelper.readableDatabase
        return when (sUriMatcher.match(uri)) {
            111 -> {
                db.query(TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder)
            }
            222-> {
                val _selection = "${KEY_ID}=?"
                val _selectionArgs = arrayOf(ContentUris.parseId(uri).toString())
                db.query(TABLE_NAME, projection, _selection, _selectionArgs, null, null, sortOrder)
            }
            else -> {
                Toast.makeText(context, "Incorrect URI", Toast.LENGTH_SHORT).show()
                throw IllegalArgumentException("Can't query incorrect URI $uri")
            }
        }
    }

    override fun getType(uri: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
//        val db = dbHelper.writableDatabase
////        return when (sUriMatcher.match(uri)) {
////
////        }
        TODO()
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        TODO("Not yet implemented")
    }

    companion object {
        const val SCHEME = "content://"
        const val AUTHORITY = "com.nirwashh.android.sportclub"
        const val PATH_MEMBERS = "members"
    }
}