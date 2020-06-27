package com.strugglelin.im.bean.db

import android.app.Application
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*
import java.security.PrivateKey

/**
 *  @author strugglelin
 *  @date 2020/6/27
 *  description:
 */
class DatabaseHelper(context:Application) :ManagedSQLiteOpenHelper(context,NAME,null,VERSION){

    companion object {
        val NAME = "im.db"
        val VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(ContactTable.NAME,true,
            ContactTable.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            ContactTable.CONTACT to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(ContactTable.NAME,true)
        onCreate(db)
    }

}