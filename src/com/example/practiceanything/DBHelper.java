package com.example.practiceanything;

import java.security.acl.LastOwnerException;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "db";
	private static final String TABLE_USERS = "users";
	private static final String KEY_ID = "_id";
	private static final String COLUMN_FIRSTNAME = "firstname";
	private static final String COLUMN_LASTNAME = "lastname";
	private static final String TABLE_EVENTLOG = "eventlog2";
	
	private static final String COLUMN_TASKNAME = "taskname";
	private static final String COLUMN_DURATION = "duration";
	private static final String COLUMN_NAME = "name";
	private static final String COLUMN_YEAR = "year";
	private static final String COLUMN_MONTH = "month";
	private static final String COLUMN_DAY = "day";
	private static final String TABLE_USERS_CREATE = "create table if not exists users('_id' integer primary key autoincrement, firstname text);";
	private static final String TABLE_EVENTLOG_CREATE = "create table if not exists eventlog2('_id' integer primary key autoincrement, taskname text, duration real, name text, year text, month text," +
			"day text);"; //duration is int because i want to add up the values over time
	private static final String TABLE_USERS_PERSONALEVENTS_CREATE = "create table if not exists usersevents('_id' integer primary key autoincrement, taskname text);"; 
				//constructor
	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			db.execSQL(TABLE_USERS_CREATE);
			db.execSQL(TABLE_EVENTLOG_CREATE);
			db.execSQL(TABLE_USERS_PERSONALEVENTS_CREATE);
		} catch (SQLiteException e){
			Log.e("createFAIL", e.toString(), e);
		}
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		
	}

	void addUser(String firstname) {
		

		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(COLUMN_FIRSTNAME, firstname);
		db.insert(TABLE_USERS, null, values);
		
		
		try { 
			Cursor c = db.rawQuery("SELECT * FROM users;", null);
			if (c!=null) {
				c.moveToLast();
				String lastUser = c.getString(1).toString();
				System.out.println("User added: " + lastUser);

			} else {
				Context context = null;
				Toast.makeText(context, "fail", Toast.LENGTH_LONG).show();
			}

		} catch (SQLiteException e ) {
			Log.e("addUSERfailed", e.toString(), e);
		}
		db.close();
	}
	
	public final List<String> getAllUsers() {
		final List<String> userList = new ArrayList<String>();
		String selectQuery = "SELECT * FROM " + TABLE_USERS + ";";
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);
		if (c.moveToFirst()) {
            do {
                String string = new String();
                string = c.getString(1);
                // Adding contact to list
                userList.add(string);
                System.out.println(string);
            } while (c.moveToNext());
        }
		return userList;
	}
	
	public final List<String> getFullRecordsFromEventLog(String name, String year, String month, String day) {
		final List<String> recordList = new ArrayList<String>();
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cc = db.rawQuery("select * from eventlog2 where name = " + "'"+name+"'" 
		+ "AND taskname is not null " +
		"AND year = " + "'"+year+"'" + " AND month = " + "'"+month+"'" + " AND day = " + "'"+day+"'" + ";", null);
		
				 
		if (cc!=null) {
			int cursorCount = cc.getCount();
			System.out.println("# of rows in cursor: " + String.valueOf(cursorCount));
			cc.moveToFirst();
			
			if (cc.getCount()==0){
				recordList.add("No Data Entered");
			}
			
			for (int i = 0;i<cursorCount;i++){

				int rowNum2 = cc.getInt(0);
				final String lastRowId2 = String.valueOf(rowNum2);
				float durationFloat = cc.getFloat(2);
				String tasknamePrint = cc.getString(1).toString();
				String durationPrint = String.valueOf(durationFloat);
				String namePrint = cc.getString(3).toString();
				String yearPrint = cc.getString(4).toString();
				String monthPrint = cc.getString(5).toString();
				String dayPrint = cc.getString(6).toString();
				String listFullTaskNameAndDuration = tasknamePrint + " \t\t\t " + durationPrint;
				System.out.println("====\nDB Row: " + lastRowId2 +" \nTaskname: " + tasknamePrint +" \nDuration: " + durationPrint +  " \nName: " +  namePrint + 
						"\nYear: " + yearPrint + " Month: " + monthPrint + " Day: " + dayPrint + "\n====");
				cc.moveToNext();
				recordList.add(listFullTaskNameAndDuration);
			}
			
		}
		else {
			System.out.println("Fail. No records found");
			recordList.add("No Data Entered");
		}
		return recordList;
	}
	
	public void deleteUser(String username) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_USERS, COLUMN_FIRSTNAME + "= ?", new String[]{username});
		System.out.println("User deleted: " + username);
		db.close();
		
	}
	
	public void addUserTask(String username) { 
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(COLUMN_NAME, username);
		db.insert(TABLE_EVENTLOG, null, values);
		
		try {
			Cursor c = db.rawQuery("select * from eventlog2;", null);
			if (c!=null) {
				c.moveToLast();
				String usernamePrint = c.getString(3).toString();
				int rowNum = c.getInt(0);
				String rowPrint = String.valueOf(rowNum);
				System.out.println("Row #: " + rowPrint + "\n" + "Name added: " + usernamePrint);
				c.close();
				db.close();
			}

		} catch (SQLiteException e) {
			Log.e("adduserFAIL", e.toString(), e);
		}
		
	}
	
	
	
	public void updateLastAddedTask(String year, String month, String day){
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor c = db.rawQuery("select * from eventlog2;", null);
		c.moveToLast();
		int rowNum = c.getInt(0);
		final String lastRowId = String.valueOf(rowNum);
		System.out.println("Last row: " + lastRowId);		

		ContentValues values = new ContentValues();
		values.put(COLUMN_YEAR, year);
		values.put(COLUMN_MONTH, month);
		values.put(COLUMN_DAY, day);
		db.update(TABLE_EVENTLOG, values, KEY_ID + " = " + lastRowId, null);

		Cursor cc = db.rawQuery("select * from eventlog2;", null);
		cc.moveToLast();
		int rowNum2 = cc.getInt(0);
		final String lastRowId2 = String.valueOf(rowNum2);
		String yearPrint = cc.getString(4).toString();
		String monthPrint = cc.getString(5).toString();
		String dayPrint = cc.getString(6).toString();
		System.out.println("Row: " + lastRowId2 + " Year: " + yearPrint + " Month: " + monthPrint + " Day: " + dayPrint);
		db.close();
	}
	
	public String getLastRowNumber() {
		SQLiteDatabase db = this.getWritableDatabase();
		
		Cursor c = db.rawQuery("select * from eventlog2;", null);
		c.moveToLast();
		int rowNum = c.getInt(0);
		final String lastRowNumber = String.valueOf(rowNum);
		System.out.println("Last row: " + lastRowNumber);
		return lastRowNumber;
	}
	
	public String getLastRowUserName() {
		SQLiteDatabase db = this.getWritableDatabase();
		String lastUsername = null;
		Cursor c = db.rawQuery("select * from eventlog2;", null);
			
			if (c!=null) {
				c.moveToLast();
				lastUsername = c.getString(3).toString();
				System.out.println(lastUsername);
			}
		return lastUsername;
		}
	
	public String getLastRowDuration() {
		SQLiteDatabase db = this.getWritableDatabase();
		String lastDuration = null;
		Cursor cc = db.rawQuery("select * from eventlog2;", null);
			
			if (cc!=null) {
				cc.moveToLast();
				lastDuration = String.valueOf(cc.getFloat(2)); 
				System.out.println(lastDuration);
			}
		return lastDuration;		
	}
	
	public String getLastTaskname() {
		SQLiteDatabase db = this.getWritableDatabase();
		String lastTaskname = null;
		Cursor c = db.rawQuery("select * from eventlog2;", null);
			
			if (c!=null) {
				c.moveToLast();
				lastTaskname = c.getString(1).toString();
				System.out.println(lastTaskname);
			}
		return lastTaskname;
	}
	
	public String getLastRowYear() {
		SQLiteDatabase db = this.getWritableDatabase();
		String lastRowYear = null;
		Cursor c = db.rawQuery("select * from eventlog2;", null);
			
			if (c!=null) {
				c.moveToLast();
				lastRowYear = c.getString(4).toString();
				System.out.println(lastRowYear);
			}
		return lastRowYear;
		}
	

	public String getLastRowMonth() {
		SQLiteDatabase db = this.getWritableDatabase();
		String lastRowMonth = null;
		Cursor c = db.rawQuery("select * from eventlog2;", null);
			
			if (c!=null) {
				c.moveToLast();
				lastRowMonth = c.getString(5).toString();
				System.out.println(lastRowMonth);
			}
		return lastRowMonth;
		}
	
	
	public String getLastRowDay() {
		SQLiteDatabase db = this.getWritableDatabase();
		String lastRowDay = null;
		Cursor c = db.rawQuery("select * from eventlog2;", null);
			
			if (c!=null) {
				c.moveToLast();
				lastRowDay = c.getString(6).toString();
				System.out.println(lastRowDay);
			}
		return lastRowDay;
		}
	
	
	public void createPersonalizedTask(String taskname) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(COLUMN_TASKNAME, taskname);
		db.insert(TABLE_USERS_PERSONALEVENTS_CREATE, COLUMN_TASKNAME, values);
	}
	
	public void updateTaskDuration(String duration) {
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor c = db.rawQuery("select * from eventlog2;", null);
		c.moveToLast();
		int rowNum = c.getInt(0);
		final String lastRowId = String.valueOf(rowNum);
		
		ContentValues values = new ContentValues();
		values.put(COLUMN_DURATION, duration);
		db.update(TABLE_EVENTLOG, values, KEY_ID + " = " + lastRowId, null);
//		
		Cursor cc = db.rawQuery("select * from eventlog2;", null);
		cc.moveToLast();
		int rowNum2 = cc.getInt(0);
		final String lastRowId2 = String.valueOf(rowNum2);
		String durationPrint = cc.getString(2).toString();
		System.out.println("Row: " + lastRowId2 + " Duration: " + durationPrint);
		db.close();
		
		//need to duplicate last record's info then create a new record
		
	}
	
	public void updateTasknameIntoEventlog(String taskname) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		Cursor c = db.rawQuery("select * from eventlog2;", null);
		c.moveToLast();
		int rowNum = c.getInt(0);
		final String lastRowId = String.valueOf(rowNum);
		
		values.put(COLUMN_TASKNAME, taskname);
		db.update(TABLE_EVENTLOG, values, KEY_ID + " = " + lastRowId, null);
		db.close();
	}
	
	public void insertCompleteTasknameIntoEventlog(String taskname, float duration, String name, String year, String month, String day) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(COLUMN_TASKNAME, taskname);
		values.put(COLUMN_DURATION, duration);
		values.put(COLUMN_NAME, name);
		values.put(COLUMN_YEAR, year);
		values.put(COLUMN_MONTH, month);
		values.put(COLUMN_DAY, day);
		db.insert(TABLE_EVENTLOG, COLUMN_TASKNAME, values);
		
		Cursor cc = db.rawQuery("select * from eventlog2;", null);
		cc.moveToLast();
		int rowNum2 = cc.getInt(0);
		final String lastRowId2 = String.valueOf(rowNum2);
		String tasknamePrint = cc.getString(1).toString();
		float durationFloat = cc.getFloat(2);
		String durationPrint = String.valueOf(durationFloat);
		String namePrint = cc.getString(3).toString();
		String yearPrint = cc.getString(4).toString();
		String monthPrint = cc.getString(5).toString();
		String dayPrint = cc.getString(6).toString();
		System.out.println("====\nDB Row: " + lastRowId2 + " \nTaskname: " + tasknamePrint + " \nDuration: " + durationPrint + " \nName: " + namePrint + 
				"\nYear: " + yearPrint + " Month: " + monthPrint + " Day: " + dayPrint + "\n====");
	}
	
	public void deleteEmptyRecordsFromEventlog() { 
		SQLiteDatabase db = this.getWritableDatabase();
	}
	

	

	
}
