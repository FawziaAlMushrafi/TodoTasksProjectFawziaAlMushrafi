package com.yameen.todotasks

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class Sqliteopenhelper (context:Context):
    SQLiteOpenHelper (context,DATABASE_NAME,null, DATABASE_VERSION){


    companion object{

        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "ToDoTasks" //
        private const val TABLE_MYTASKS = "mytasks"
        private const val COLUMN_ID = "_id"
        private const val COLUMN_DATE = "date"
        private const val COLUMN_TITLE = "title"
        private const val COLUMN_TASK = "task"



    }

    override fun onCreate(db: SQLiteDatabase?) {

        val CREATE_DATE_TABLE = ("CREATE TABLE "+ TABLE_MYTASKS + "("
                                     + COLUMN_ID + " INTEGER PRIMARY KEY," +
                                      COLUMN_DATE + " TEXT," +
                                      COLUMN_TITLE + " TEXT," +
                                      COLUMN_TASK + " TEXT" + ")")

        db?.execSQL(CREATE_DATE_TABLE)
    }

    override fun onUpgrade(dp: SQLiteDatabase?, p1: Int, p2: Int){
        dp!!.execSQL("DROP TABLE IF EXISTS " + TABLE_MYTASKS)

        onCreate(dp)
    }

    fun adddate (date:String,title:String,task:String):Long {
        val dp = this.writableDatabase // open db..

        val valuse = ContentValues()

        valuse.put(COLUMN_DATE,date)
        valuse.put(COLUMN_TITLE,title)
        valuse.put(COLUMN_TASK,task)

        // if return greater than 0 qur success if -1 not success..
       val sucsses= dp.insert(TABLE_MYTASKS,null,valuse)

        dp.close()
        return sucsses
    }

    @SuppressLint("Range")
    fun getalltaskelist():ArrayList<task_model>{
            val db = readableDatabase //

            val list = ArrayList<task_model>()

            val selectqury ="SELECT * FROM $TABLE_MYTASKS"

            try {

                val  cursor: Cursor = db.rawQuery(selectqury,null)

                var id:Int
                var title:String
                var date:String
                var task:String



                if (cursor.moveToFirst()){

                    do {

                        id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                        title = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE))
                        date = cursor.getString(cursor.getColumnIndex(COLUMN_DATE))
                        task = cursor.getString(cursor.getColumnIndex(COLUMN_TASK))


                        val task = task_model(id,date,title,task)
                        list.add(task)

                    }while (cursor.moveToNext())
                }
                cursor.close()

            }catch (e: SQLiteException){

                db.execSQL(selectqury)
                return ArrayList()
            }

            return list

        }

    fun updatetask (task:task_model):Int{

        val db = writableDatabase
        val content = ContentValues()

        content.put(COLUMN_TITLE,task.title)
        content.put(COLUMN_TASK,task.task)


        val sucses = db.update(TABLE_MYTASKS,content, COLUMN_ID + " = " + task._id ,null)

        db.close()
        return sucses
    }

    fun deletetask (task:task_model):Int{
        val db = writableDatabase

        val sucses = db.delete(TABLE_MYTASKS, COLUMN_ID + " = " + task._id,null)

        db.close()

        return sucses
    }
}

