package com.example.marcelba.mystudyschedule;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;


/**
 * Created by marcel on 11/04/2015.
 */
public class DBProxy extends SQLiteOpenHelper {

    final public static String DB_NAME = "StudySchedule.db";
    final public static int DB_VERSION= 1;

    final public static String DB_SUBJECTS_TABLE_NAME = "subjects";
    final public static String DB_SUBJECTS_COL_ID = BaseColumns._ID;
    final public static String DB_SUBJECTS_COL_NAME = "name";
    final public static String DB_SUBJECTS_COL_COLOR = "color";
    final public static String DB_SUBJECTS_COL_TEACHER = "teacher";

      public DBProxy(Context context){
        super(context, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+DB_SUBJECTS_TABLE_NAME+"("+DB_SUBJECTS_COL_ID+" INTEGER PRIMARY KEY,"+DB_SUBJECTS_COL_NAME+" TEXT,"+DB_SUBJECTS_COL_COLOR+" TEXT,"+DB_SUBJECTS_COL_TEACHER+"TEXT)" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void AddSubject (String name, String color, String teacher){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.putNull(DB_SUBJECTS_COL_ID);
        values.put(DB_SUBJECTS_COL_NAME, name);
        values.put(DB_SUBJECTS_COL_COLOR, color);
        values.put(DB_SUBJECTS_COL_TEACHER, teacher);
        db.insert(DB_SUBJECTS_TABLE_NAME, null, values);


    }


}
