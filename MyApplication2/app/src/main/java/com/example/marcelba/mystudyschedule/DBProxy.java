package com.example.marcelba.mystudyschedule;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;


/**
 * mio
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

    final public static String DB_HORARY_TABLE_NAME = "horary";
    final public static String DB_HORARY_COL_ID = BaseColumns._ID;
    final public static String DB_HORARY_COL_SUBJECT_ID = BaseColumns._ID;
    final public static String DB_HORARY_COL_WEEKDAY = "weekday";
    final public static String DB_HORARY_COL_STARTTIME = "startime";
    final public static String DB_HORARY_COL_ENDTIME = "endtime";

    public DBProxy(Context context){
        super(context, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+DB_SUBJECTS_TABLE_NAME+"("+DB_SUBJECTS_COL_ID+" INTEGER PRIMARY KEY,"+DB_SUBJECTS_COL_NAME+" TEXT,"+DB_SUBJECTS_COL_COLOR+" TEXT,"+DB_SUBJECTS_COL_TEACHER+"TEXT)" );
        db.execSQL("CREATE TABLE "+DB_HORARY_TABLE_NAME+"("+DB_HORARY_COL_ID+" INTEGER PRIMARY KEY,"+DB_HORARY_COL_SUBJECT_ID+" INTEGER,"+DB_HORARY_COL_WEEKDAY+" INTEGER,"+DB_HORARY_COL_STARTTIME+" TEXT,"+DB_HORARY_COL_ENDTIME+"TEXT)" );
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

   /* public Cursor ReadNotes() {

        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {DB_COL_ID, DB_COL_TITLE, DB_COL_NOTE};
        return db.query(DB_TABLE_NAME, columns, null, null, null, null, null);

    }

    public void updateNote(long id, String title, String body)
    {
        SQLiteDatabase db = getWritableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(DB_COL_TITLE, title);
        values.put(DB_COL_NOTE, body);

// Which row to update, based on the ID
        String selection = DB_COL_ID + " LIKE ?";
        String[] selectionArgs = { String.valueOf(id) };

        int count = db.update(
                DB_TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }

    public void DelNote(long id)
    {
        SQLiteDatabase db = getWritableDatabase();
        String table = DB_TABLE_NAME;
        String whereClause = "_id" + "=?";
        String[] whereArgs = new String[] { String.valueOf(id) };
        db.delete(table, whereClause, whereArgs);


    }*/
}
