package com.nirwashh.android.sportclub.data

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.nirwashh.android.sportclub.data.DatabaseContract.DatabaseEntry.KEY_FIRST_NAME
import com.nirwashh.android.sportclub.data.DatabaseContract.DatabaseEntry.KEY_GENDER
import com.nirwashh.android.sportclub.data.DatabaseContract.DatabaseEntry.KEY_ID
import com.nirwashh.android.sportclub.data.DatabaseContract.DatabaseEntry.KEY_LAST_NAME
import com.nirwashh.android.sportclub.data.DatabaseContract.DatabaseEntry.KEY_SPORT
import com.nirwashh.android.sportclub.data.DatabaseContract.DatabaseEntry.TABLE_NAME
import com.nirwashh.android.sportclub.model.Member

const val TAG = "Member"
class DatabaseManager(context: Context) {
    private val dbHelper = DatabaseHandler(context)

    fun addMember(member: Member) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(KEY_FIRST_NAME, member.firstName)
            put(KEY_LAST_NAME, member.lastName)
            put(KEY_GENDER, member.gender)
            put(KEY_SPORT, member.sport)
        }
        db?.insert(TABLE_NAME, null, values)
        Log.d(
            TAG,
            "ADD = NAME: ${member.firstName} ${member.lastName}, GENDER: ${member.gender}, SPORT CLUB: ${member.sport}"
        )
        db.close()
    }

    fun getMember(id: Int): Member {
        val db = dbHelper.readableDatabase
        val columns = arrayOf(KEY_ID, KEY_FIRST_NAME, KEY_LAST_NAME, KEY_GENDER, KEY_SPORT)
        val selection = "$KEY_ID=?"
        try {
            val selectionArgs = arrayOf(id.toString())
            val cursor =
                db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null, null)
            cursor?.moveToFirst()
            val memberID = cursor.getString(cursor.getColumnIndexOrThrow(KEY_ID)).toInt()
            val firstName = cursor.getString(cursor.getColumnIndexOrThrow(KEY_FIRST_NAME))
            val lastName = cursor.getString(cursor.getColumnIndexOrThrow(KEY_LAST_NAME))
            val gender = cursor.getString(cursor.getColumnIndexOrThrow(KEY_GENDER))
            val sportGroup = cursor.getString(cursor.getColumnIndexOrThrow(KEY_SPORT))
            val member =  Member(memberID, firstName, lastName, gender, sportGroup)
            Log.d(
                TAG,
                "FIND FROM ID = ID: ${member.id},  NAME: ${member.firstName} ${member.lastName}, GENDER: ${member.gender}, SPORT CLUB: ${member.sport}"
            )
            cursor.close()
            return member
        } catch (e: Exception) {
            Log.d(TAG, "member not found")
        }
        return Member()
    }

    fun getMember(_firstName: String): Member {
        val db = dbHelper.readableDatabase
        val selection = "$KEY_FIRST_NAME like ?"
        try {
            val selectionArgs = arrayOf("%$_firstName%")
            val cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null)
            cursor.moveToFirst()
            val firstName = cursor.getString(cursor.getColumnIndexOrThrow(KEY_FIRST_NAME))
            val lastName = cursor.getString(cursor.getColumnIndexOrThrow(KEY_LAST_NAME))
            val gender = cursor.getString(cursor.getColumnIndexOrThrow(KEY_GENDER))
            val sportGroup = cursor.getString(cursor.getColumnIndexOrThrow(KEY_SPORT))
            val member =  Member(firstName = firstName, lastName = lastName, gender = gender, sport = sportGroup)
            Log.d(
                TAG,
                "FIND FROM NAME = NAME: ${member.firstName} ${member.lastName}, GENDER: ${member.gender}, SPORT CLUB: ${member.sport}"
            )
            cursor.close()
            return member
        } catch (e: Exception) {
            Log.d(TAG, "member not found")
        }
        return Member()
    }

    fun getAllMembers(): List<Member> {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        val members = mutableListOf<Member>()
        with(cursor) {
            while (moveToNext()) {
                val memberID = cursor.getString(cursor.getColumnIndexOrThrow(KEY_ID)).toInt()
                val firstName = cursor.getString(cursor.getColumnIndexOrThrow(KEY_FIRST_NAME))
                val lastName = cursor.getString(cursor.getColumnIndexOrThrow(KEY_LAST_NAME))
                val gender = cursor.getString(cursor.getColumnIndexOrThrow(KEY_GENDER))
                val sportGroup = cursor.getString(cursor.getColumnIndexOrThrow(KEY_SPORT))
                val member =  Member(memberID, firstName, lastName, gender, sportGroup)
                members.add(member)
            }
        }
        cursor.close()
        return members.toList()
    }

    fun updateMember(member: Member): Int {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(KEY_FIRST_NAME, member.firstName)
            put(KEY_LAST_NAME, member.lastName)
            put(KEY_GENDER, member.gender)
            put(KEY_SPORT, member.sport)
        }
        val int = db.update(TABLE_NAME, values, "$KEY_ID=?", arrayOf(member.id.toString()))
        Log.d(
            TAG,
            "UPDATE = NAME: ${member.firstName} ${member.lastName}, GENDER: ${member.gender}, SPORT CLUB: ${member.sport}"
        )
        return int
    }

    fun deleteMember(member: Member) {
        val db = dbHelper.writableDatabase
        db.delete(TABLE_NAME, "$KEY_ID=?", arrayOf(member.id.toString()))
        Log.d(
            TAG,
            "DELETE = ID: ${member.id},  NAME: ${member.firstName} ${member.lastName}, GENDER: ${member.gender}, SPORT CLUB: ${member.sport}"
        )
        db.close()
    }

    fun getMembersCount(): Int {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        val count = cursor.count
        cursor.close()
        return count
    }

    fun closeDb() {
        dbHelper.close()
    }


}