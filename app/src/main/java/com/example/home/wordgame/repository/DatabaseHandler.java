package com.example.home.wordgame.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by HOME on 6/18/2017.
 */

public class DatabaseHandler  extends SQLiteOpenHelper{


        private static final String DATABASE_NAME = "wordgame.db";
        private static final int DATABASE_VERSION = 2;

        public static final String TABLE_WORDS = "Words";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_WORD = "word";
        public static final String COLUMN_MEANING = "meaning";
        public static final String COLUMN_DESCRIPTION = "description";

        private static final String TABLE_CREATE =
                "CREATE TABLE " + TABLE_WORDS + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_WORD + " TEXT, " +
                        COLUMN_MEANING + " TEXT, " +
                        COLUMN_DESCRIPTION + " TEXT " +
                        ")";


        public DatabaseHandler(Context context){
            super(context,DATABASE_NAME,null,DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(TABLE_CREATE);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS "+TABLE_WORDS);
            db.execSQL(TABLE_CREATE);
        }
    }