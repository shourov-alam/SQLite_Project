package com.shourov.sqlite_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DatabaseSource {

    DataBaseHelper dataBaseHelper;
    SQLiteDatabase sqLiteDatabase;
    Model model;


    public DatabaseSource(Context context){

        dataBaseHelper=new DataBaseHelper(context);


    }

    public void open(){

        sqLiteDatabase=dataBaseHelper.getWritableDatabase();

    }

    public void close(){

        dataBaseHelper.close();


    }

    public boolean addStudent(Model model){

        this.open();
        ContentValues contentValues = new ContentValues();

        contentValues.put(DataBaseHelper.COL_NAME,model.getName());
        contentValues.put(DataBaseHelper.COL_AGE,model.getAge());
        contentValues.put(DataBaseHelper.COL_ADDRESS,model.getAddress());

        Long insertRow=sqLiteDatabase.insert(DataBaseHelper.STUDENT_TABLE,null,contentValues);

        this.close();

        if(insertRow>0){
            return true;


        }

        else return false;


    }


    public boolean updateStudent(Model model){



        this.open();


        ContentValues contentValues = new ContentValues();

        contentValues.put(DataBaseHelper.COL_NAME,model.getName());
        contentValues.put(DataBaseHelper.COL_AGE,model.getAge());
        contentValues.put(DataBaseHelper.COL_ADDRESS,model.getAddress());

                    int updatedRow  =   sqLiteDatabase.update(DataBaseHelper.STUDENT_TABLE,contentValues,DataBaseHelper.COL_ID+" =?",new String[]{String.valueOf(model.getId())});


                    if(updatedRow>0)
                        return true;

                    else return false;










    }












    public ArrayList<Model> getAllStudent(){


        //dataBaseHelper.getReadableDatabase();

        this.open();
        
        ArrayList<Model> arrayList=new ArrayList<>();

                        Cursor cursor = sqLiteDatabase.query(dataBaseHelper.STUDENT_TABLE, null, null, null, null, null,null,null);

                        if(cursor.moveToFirst()){

                            do{

                              String name=  cursor.getString(cursor.getColumnIndex(dataBaseHelper.COL_NAME));
                               int age= cursor.getInt(cursor.getColumnIndex(dataBaseHelper.COL_AGE));
                               String address= cursor.getString(cursor.getColumnIndex(dataBaseHelper.COL_ADDRESS));
                                int id= cursor.getInt(cursor.getColumnIndex(dataBaseHelper.COL_ID));

                                Model model = new Model(name,address,age,id);
                                arrayList.add(model);


                            }
                            while (cursor.moveToNext());


                        }

        this.close();
        cursor.close();
        return arrayList;



    }

    public boolean deleteStudent(Model model){


        this.open();
      int deleteRow =  sqLiteDatabase.delete(DataBaseHelper.STUDENT_TABLE,DataBaseHelper.COL_ID+" =?",new String[]{String.valueOf(model.getId())});

      this.close();

      if(deleteRow>0){

          return true;

      }else return false;




    }


}
