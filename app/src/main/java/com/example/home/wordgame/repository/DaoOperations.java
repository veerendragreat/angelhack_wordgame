package com.example.home.wordgame.repository;

import android.content.ContentValues;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.home.wordgame.dtos.Words;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by HOME on 6/18/2017.
 */

public class DaoOperations {
    public static final String LOGTAG = "EMP_MNGMNT_SYS";

    SQLiteOpenHelper dbhandler;
    SQLiteDatabase database;

    private static final String[] allColumns = {
            DatabaseHandler.COLUMN_ID,
            DatabaseHandler.COLUMN_WORD,
            DatabaseHandler.COLUMN_MEANING,
            DatabaseHandler.COLUMN_DESCRIPTION
    };

    public DaoOperations(Context context){
        dbhandler = new DatabaseHandler(context);
    }

    public void open(){
        Log.i(LOGTAG,"Database Opened");
        database = dbhandler.getWritableDatabase();


    }
    public void close(){
        Log.i(LOGTAG, "Database Closed");
        dbhandler.close();

    }
    public Words addWords(Words wordsDto){
        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.COLUMN_WORD, wordsDto.getWord());
        values.put(DatabaseHandler.COLUMN_MEANING, wordsDto.getMeaning());
        values.put(DatabaseHandler.COLUMN_DESCRIPTION, wordsDto.getDescription());
        long insertId = database.insert(DatabaseHandler.TABLE_WORDS,null,values);
        wordsDto.setId(insertId);
        return wordsDto;

    }
/*
    // Getting single Employee
    public Employee getEmployee(long id) {

        Cursor cursor = database.query(DatabaseHandler.TABLE_words,allColumns,DatabaseHandler.COLUMN_ID + "=?",new String[]{String.valueOf(id)},null,null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Employee e = new Employee(Long.parseLong(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));
        // return Employee
        return e;
    }**/

    public List<Words> getAllwords(boolean isLimiReq,int startIndex,int endIndex) {

        Log.d("Android : ", "IN getAllwords method . Start :  "+startIndex+", end index : "+endIndex);
        //Cursor cursor = database.query(DatabaseHandler.TABLE_WORDS,allColumns, null,null,null, null,"id", startIndex+","+endIndex);
        Cursor cursor = database.query(DatabaseHandler.TABLE_WORDS,allColumns, null,null,null, null,"id", isLimiReq?startIndex+","+endIndex:null);



        List<Words> words = new ArrayList<>();
        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                Log.d("Android : Count", cursor.getCount()+"");
                Words employee = new Words();
                employee.setId(cursor.getLong(cursor.getColumnIndex(DatabaseHandler.COLUMN_ID)));
                employee.setWord(cursor.getString(cursor.getColumnIndex(DatabaseHandler.COLUMN_WORD)));
                employee.setMeaning(cursor.getString(cursor.getColumnIndex(DatabaseHandler.COLUMN_MEANING)));
                words.add(employee);
            }
        }
        // return All words
        return words;
    }

/**


    // Updating Employee
    public int updateEmployee(Words wordsDto) {

        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.COLUMN_WORD, wordsDto.getFirstname());
        values.put(DatabaseHandler.COLUMN_MEANING, wordsDto.getLastname());
        values.put(DatabaseHandler.COLUMN_DESCRIPTION, wordsDto.getGender());

        // updating row
        return database.update(DatabaseHandler.TABLE_words, values,
                DatabaseHandler.COLUMN_ID + "=?",new String[] { String.valueOf(employee.getEmpId())});
    }

    // Deleting Employee
    public void removeEmployee(Employee employee) {

        database.delete(DatabaseHandler.TABLE_words, DatabaseHandler.COLUMN_ID + "=" + employee.getEmpId(), null);
    }*/


}
