package com.metropolitan.treciprimer.baza

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class Baza(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    companion object {
        val DATABASE_NAME = "FIT.db"
        val TABLE_NAME = "korisnik"
        val ID = "ID"
        val USERNAME = "USERNAME"
        val PASSWORD = "PASSWORD"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table $TABLE_NAME (ID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT, PASSWORD TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }
    fun insertData(username: String, password: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(USERNAME, username)
        contentValues.put(PASSWORD, password)
        db.insert(TABLE_NAME, null, contentValues)
        Log.d("Uspesno insertovao: ", "username: $username, password: $password")
        return true;
    }
    fun getParticularUserData(username: String): UserInfo {
        Log.d("Pokusavam da uzmem", username)
        var userInfo = UserInfo()
        val db = this.readableDatabase
        val selectQuery = "SELECT * FROM $TABLE_NAME WHERE $USERNAME = '$username'"
        val cursor = db.rawQuery(selectQuery, null)
        try {
            if (cursor.count != 0) {
                cursor.moveToFirst();
                if (cursor.count > 0) {
                    do {
                        userInfo.id = cursor.getInt(cursor.getColumnIndex(ID))
                        userInfo.username = cursor.getString(cursor.getColumnIndex(USERNAME))
                        userInfo.password = cursor.getString(cursor.getColumnIndex(PASSWORD))
                    } while ((cursor.moveToNext()));
                }
            }
        } finally {
            cursor.close();
        }
        return userInfo
    }
    fun getAllUserData(): ArrayList<UserInfo> {
        val userList: ArrayList<UserInfo> = arrayListOf<UserInfo>()
        val cursor: Cursor = getReadableDatabase().query(
            TABLE_NAME,
            arrayOf(ID, USERNAME, PASSWORD),
            null,
            null,
            null,
            null,
            null
        )
        try {
            if (cursor.getCount() != 0) {
                cursor.moveToFirst()
                if (cursor.getCount() > 0) {
                    do {
                        val id: Int = cursor.getInt(cursor.getColumnIndex(ID))
                        val username: String = cursor.getString(cursor.getColumnIndex(USERNAME))
                        val password: String = cursor.getString(cursor.getColumnIndex(PASSWORD))
                        val userInfo = UserInfo()
                        userInfo.id = id
                        userInfo.username = username
                        userInfo.password = password
                        userList.add(userInfo)
                    } while ((cursor.moveToNext()))
                }
            }
        } finally {
            cursor.close()
        }

        return userList
    }
}