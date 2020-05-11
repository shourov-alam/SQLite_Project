package com.shourov.sqlite_project;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

   // Context context;

    public static final String DATABASE_NAME="student.db";
    public static final int DATABASE_VERSION= 1;

    public static final String STUDENT_TABLE="student_table";

    public static final String COL_ID= "_id";
    public static final String COL_NAME= "name";
    public static final String COL_AGE= "age";
    public static final String COL_ADDRESS= "address";
    public static final String CREATE_TABLE = "create table "+STUDENT_TABLE+ "("+COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +COL_NAME+ " TEXT, "+COL_AGE+" INTEGER, " +COL_ADDRESS+ " TEXT " +")";




//@Nullable

    public DataBaseHelper( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
       // this.context=context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(" drop table if exists "+ STUDENT_TABLE);
        this.onCreate(db);

    }
}
