package com.classycoder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;


public class Subscribers extends SQLiteOpenHelper {
	
	public static final String DATABASE_NAME = "Subscribers.db";
	public static final int VERSION_NO = 1;
	
	public Subscribers(Context context) {
		super(context, DATABASE_NAME, null, VERSION_NO);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String CREATE_SUBSCRIBERS_TABLE = ""
		db.execSQL(SQL_CREATE_ENTRIES);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	

	
}
