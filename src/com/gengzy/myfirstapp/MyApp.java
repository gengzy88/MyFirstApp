package com.gengzy.myfirstapp;

import android.net.Uri;
import android.provider.BaseColumns;

public class MyApp {
    public static final String AUTHORITY = "com.gengzy.myfirstapp.provider";
    
    public MyApp() {
		// TODO Auto-generated constructor stub
	}
    
    public static final class MyColumns implements BaseColumns {
    	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/MyApp");
    	public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.com.gengzy.myfirstapp.table1";
    	public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.com.gengzy.myfirstapp.table1";
    	
    	public static final String NAME = "name";
    	public static final String PHONE_NO = "number";
    	public static final String MSG = "msg";
    }
}
