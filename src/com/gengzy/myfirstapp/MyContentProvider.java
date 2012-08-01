package com.gengzy.myfirstapp;

import com.gengzy.myfirstapp.MyApp.MyColumns;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class MyContentProvider extends ContentProvider{
	private static final String SQL_CREATE_MAIN_STRING = "CREATE TABLE main (_ID INTEGER PRIMARY KEY, MESSAGE TEXT)";
	private static final String DB_NAME = "my_db";
	private MySqlLiteHelper m_openHelper; 
//	private SQLiteDatabase m_db;
	private static final UriMatcher m_uriMatcher;
	private static final int MAIN_TABLE = 1;
	
	private static final class MySqlLiteHelper extends SQLiteOpenHelper {

		public MySqlLiteHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL(SQL_CREATE_MAIN_STRING);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			
		}
		
		
	}
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
//		m_db = m_openHelper.getWritableDatabase();
		
		if(m_uriMatcher.match(uri) != MAIN_TABLE){
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
		
		ContentValues contentValues;
		if(values != null){
			values = new ContentValues(values);
		}
		else{
			values = new ContentValues();
		}
		
		if(values.containsKey(MyColumns.MSG) != false){
			//values.put(key, value)
		}
		return null;
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		m_openHelper = new MySqlLiteHelper(getContext(), DB_NAME, null, 1);
		
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	static {
		m_uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		
		m_uriMatcher.addURI(MyApp.AUTHORITY, "table1", MAIN_TABLE);
	}
}
