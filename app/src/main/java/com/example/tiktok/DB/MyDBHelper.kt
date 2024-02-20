package com.example.tiktok.DB

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.tiktok.models.MyReel

class MyDBHelper(context:Context) : SQLiteOpenHelper(context,"db_video",null,1), MyDBInterface{
    override fun onCreate(db: SQLiteDatabase?) {
        val query = "create table video_table(id integer not null primary key autoincrement unique,title text not null,videLink text not null)"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    override fun addReels(MyReel: MyReel) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("title",MyReel.title)
        contentValues.put("videoLink",MyReel.videoLink)
        database.insert("video_table", null,contentValues)
        database.close()
    }

    override fun getAllReels(): ArrayList<MyReel> {
        val list = ArrayList<MyReel>()

        val database = this.readableDatabase
        val query = "select * from video_table"
        val cursor = database.rawQuery(query,null)
        if (cursor.moveToFirst()){
            do {
                val  MyReel = MyReel(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)
                )
                list.add(MyReel)
            }while (cursor.moveToNext())
        }


        return list
    }
}