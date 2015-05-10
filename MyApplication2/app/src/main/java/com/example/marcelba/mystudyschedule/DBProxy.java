package com.example.marcelba.mystudyschedule;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;


/**
 * mio
 * Created by marcel on 11/04/2015.
 */
public class DBProxy extends SQLiteOpenHelper {

    final public static String DB_NAME = "StudySchedule.db";
    final public static int DB_VERSION = 1;

 /*   final public static String DB_SUBJECTS_TABLE_NAME = "subjects";
    final public static String DB_SUBJECTS_COL_ID = BaseColumns._ID;
    final public static String DB_SUBJECTS_COL_NAME = "name";
    final public static String DB_SUBJECTS_COL_COLOR = "color";
    final public static String DB_SUBJECTS_COL_TEACHER = "teacher";*/


    final public static String DB_TASK_TABLE_NAME = "ToDo";
    final public static String DB_TASK_COL_ID = BaseColumns._ID;
    final public static String DB_TASK_COL_NAME = "TareaNombre";
    final public static String DB_TASK_COL_SUBJECT_ID = "TareaAsignaturaID";
    final public static String DB_TASK_COL_ENDDATE = "TareaFechaEntrega";
    final public static String DB_TASK_COL_PRIORITY = "TareaPrioridad";
    final public static String DB_TASK_COL_DESC = "TareaDescripcion";
    final public static String DB_TASK_COL_DONE = "TareaDone";


    public DBProxy(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       // db.execSQL("CREATE TABLE " + DB_SUBJECTS_TABLE_NAME + "(" + DB_SUBJECTS_COL_ID + " INTEGER PRIMARY KEY," + DB_SUBJECTS_COL_NAME + ")");
        db.execSQL("CREATE TABLE " + DB_TASK_TABLE_NAME + "(" +
                DB_TASK_COL_ID + " INTEGER PRIMARY KEY," +
                DB_TASK_COL_NAME + " TEXT," +
                DB_TASK_COL_SUBJECT_ID + " INTEGER," +
                DB_TASK_COL_ENDDATE + " TEXT," +
                DB_TASK_COL_PRIORITY + " FLOAT," +
                DB_TASK_COL_DONE + " INTEGER," +
                DB_TASK_COL_DESC + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

   /* public void AddSubject(String nom, int subjectId) {

        Log.i("Asignatura", nom);

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DB_SUBJECTS_COL_ID,subjectId);
        values.put(DB_SUBJECTS_COL_NAME, nom);
        db.insert(DB_SUBJECTS_TABLE_NAME, null, values);

    }*/

   /* public Cursor ReadSubject() {
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = new String[]{DB_SUBJECTS_COL_ID, DB_SUBJECTS_COL_NAME};
        return db.query(DB_SUBJECTS_TABLE_NAME, columns, null, null, null, null, null);
    }*/


    public Cursor ReadTasks() {
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = new String[]{DB_TASK_COL_ID, DB_TASK_COL_NAME,DB_TASK_COL_SUBJECT_ID,DB_TASK_COL_ENDDATE,DB_TASK_COL_PRIORITY,DB_TASK_COL_DESC,DB_TASK_COL_DONE};
        return db.query(DB_TASK_TABLE_NAME, columns, "TareaDone = ?",new String[] { "0" }, null, null, null);
    }

    public Cursor ReadTask(Long id){
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = new String[]{DB_TASK_COL_ID, DB_TASK_COL_NAME,DB_TASK_COL_SUBJECT_ID,DB_TASK_COL_ENDDATE,DB_TASK_COL_PRIORITY,DB_TASK_COL_DESC,DB_TASK_COL_DONE};
        return db.query(DB_TASK_TABLE_NAME, columns, DB_TASK_COL_ID+" = ?",new String[] { id+"" }, null, null, null);
    }

    public void AddTask(String addNuevaTarea, String addDescTarea, String addFechaTarea, Float addRatTarea, Integer addAsigTarea, Integer addTareaEcha) {


        // Log.i("descripción", addDescTarea);
        // Log.i("fecha", addFechaTarea);
        // Log.i("prioridad", ""+ addRatTarea);
        // Log.i("Asig", ""+ addAsigTarea);

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.putNull(DB_TASK_COL_ID);
        values.put(DB_TASK_COL_NAME, addNuevaTarea);
        values.put(DB_TASK_COL_SUBJECT_ID, addAsigTarea);
        values.put(DB_TASK_COL_ENDDATE, addFechaTarea);
        values.put(DB_TASK_COL_PRIORITY, addRatTarea);
        values.put(DB_TASK_COL_DESC, addDescTarea);
        values.put(DB_TASK_COL_DONE, addTareaEcha);
        db.insert(DB_TASK_TABLE_NAME, null, values);
    }

    public void UpdateTask(Long id,String addNuevaTarea, String addDescTarea, String addFechaTarea, Float addRatTarea, Integer addAsigTarea, Integer addTareaEcha)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DB_TASK_COL_NAME, addNuevaTarea);
        values.put(DB_TASK_COL_SUBJECT_ID, addAsigTarea);
        values.put(DB_TASK_COL_ENDDATE, addFechaTarea);
        values.put(DB_TASK_COL_PRIORITY, addRatTarea);
        values.put(DB_TASK_COL_DESC, addDescTarea);
        values.put(DB_TASK_COL_DONE, addTareaEcha);

        String selection = DB_TASK_COL_ID + " LIKE ?";
        String[] selectionArgs = { String.valueOf(id) };
        db.update(DB_TASK_TABLE_NAME,values,selection,selectionArgs);
    }
}
