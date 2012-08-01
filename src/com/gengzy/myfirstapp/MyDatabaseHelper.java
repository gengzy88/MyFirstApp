package com.gengzy.myfirstapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper{
	public static final String NAME_COLUME = "NAME";
	public static final String MESSAGE_COLUME = "MESSAGE";
	public static final String DATE_COLUME = "DATE";
	private static MyDatabaseHelper m_MyDatabaseHelper;
	public static final String MYTABLENAME = "my_table"; 
	private static final String CREATE_MYTABLE_SQL = "CREATE TABLE "+ MYTABLENAME + " (_id INTEGER PRIMARY KEY, " +
			NAME_COLUME + " TEXT, " + MESSAGE_COLUME + " TEXT, " + DATE_COLUME + " TEXT)";
	private static final String DB_NAME = "my_db";
	
	private MyDatabaseHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_MYTABLE_SQL);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

	public static MyDatabaseHelper getMyDatabaseHelper(Context context){
		if(null == m_MyDatabaseHelper){
			m_MyDatabaseHelper = new MyDatabaseHelper(context, DB_NAME, null, 1);
		}
		
		return m_MyDatabaseHelper;
	}
}
