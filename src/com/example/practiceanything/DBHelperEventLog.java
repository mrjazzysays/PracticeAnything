package com.example.practiceanything;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelperEventLog extends SQLiteOpenHelper{

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "db2";
	private static final String TABLE_EVENTLOG = "eventlog";
	private static final String KEY_ID = "_id";
	private static final String COLUMN_TASKNAME = "taskname";
	private static final String COLUMN_DURATION = "duration";
	private static final String COLUMN_NAME = "name";
	private static final String COLUMN_YEAR = "year";
	private static final String COLUMN_MONTH = "month";
	private static final String COLUMN_DAY = "day";
	private static final String DATABASE_CREATE = "create table if not exists eventlog('_id' integer primary key autoincrement, taskname text, duration int, name text, year int, month int," +
			"day int);";

	public DBHelperEventLog(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			db.execSQL(DATABASE_CREATE);
		} catch (SQLiteException e){
			Log.e("createFAIL", e.toString(), e);
		}
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	public void addTask(String taskname) { 
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(COLUMN_TASKNAME, taskname);
		db.insert(TABLE_EVENTLOG, null, values);
		
		try {
			Cursor c = db.rawQuery("select * from eventlog;", null);
			if (c!=null) {
				c.moveToLast();
				String tasknamePrint = c.getString(1).toString();
				System.out.println("Task added: " + tasknamePrint);
			}

		} catch (SQLiteException e) {
			Log.e("addtaskFAIL", e.toString(), e);
		}
		
	}
	
	public void updateTask(String update){
		
	}
}
