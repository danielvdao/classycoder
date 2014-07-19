package com.classycoder;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;


public class PhoneNumHandler extends SQLiteOpenHelper {
	
	public static final String DATABASE_NAME = "Subscribers.db";
	public static final int VERSION_NO = 1;
	public static final String PHONE_NUM = "phone_nums";
	
	public PhoneNumHandler(Context context) {
		super(context, DATABASE_NAME, null, VERSION_NO);
	}

	public void onCreate(SQLiteDatabase db) {
		String CREATE_SUBSCRIBERS_TABLE = "CREATE TABLE subscribers(" +
				PHONE_NUM + " TEXT" + ");";
		db.execSQL(CREATE_SUBSCRIBERS_TABLE);
	}

	/* Delete the old table if a new version happens */
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS subscribers");
	}
	
	/* Add a new phone number */
	public void insertRow(String phoneNum){
		SQLiteDatabase db = this.getWritableDatabase();
		
		/* Class is used to store a set of values */
		ContentValues values = new ContentValues();
		values.put("phone_nums", phoneNum);
		
		db.insert(DATABASE_NAME, null, values);
		db.close();
	}
	
	/* Return a list of all the subscribed phone numbers */
	public List<String> getAllNums(){
		List<String> numList = new ArrayList<String>();
		
		String selectQuery = "SELECT * FROM subscribers";
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		
		/* If there are subscribers in the table */
		if (cursor.moveToFirst()){
			do{
				numList.add(cursor.getString(0));
				
			} while (cursor.moveToNext());
		}
		
		/* To stay consistent, quite similar to C's version of free */
		cursor.close();
		db.close();
		
		return numList;
	}
}
