package com.example.assignmentsix;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseAdapter {
	public static final String DB_ROWID = "id";
	public static final String DB_USERNAME = "username";
	public static final String DB_PASSWORD = "password";
	public static final String DB_PHONE = "phone";
	public static final String DB_EMAIL = "email";
	public static final String DB_CONTEST = "contest";
	
	private static final String DB_NAME = "Memegen";
	private static final String DB_TABLE = "users";
	private static final int DB_VERSION = 2;
	
	private static final String DB_CREATE = 
			"create table if not exists users (id integer primary key autoincrement, username VARCHAR not null, password VARCHAR not null, phone VARCHAR not null, " +
			"email VARCHAR not null, contest BOOLEAN)";
	private final Context myContext;
	
	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;
	
	private static class DatabaseHelper extends SQLiteOpenHelper{
		public DatabaseHelper (Context context){
			super(context, DB_NAME, null, DB_VERSION);	
		}
		
		@Override
		public void onCreate(SQLiteDatabase db){
			db.execSQL(DB_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
			// TODO Auto-generated method stub
		}
		
	}
	
	public DatabaseAdapter (Context context){
		this.myContext = context;
	}
	
	public DatabaseAdapter open(){
		DBHelper = new DatabaseHelper(myContext);
		db = DBHelper.getWritableDatabase();
		return this;
	}
	
	public void close(){
		DBHelper.close();
	}
	
	public long createUsers(String username, String password, String phone, String email, Boolean contest){
		ContentValues v = new ContentValues();
		v.put(DB_USERNAME, username);
		v.put(DB_PASSWORD, password);
		v.put(DB_PHONE, phone);
		v.put(DB_EMAIL, email);
		v.put(DB_CONTEST, contest);
		open();
		return db.insert("users", null, v);
	}
	
	   public Cursor getAllUser(long id) 
	   {
		   return db.query(DB_TABLE, new String[] {"id", "username"}, 
			         null, null, null, null, "username");
	   }
}