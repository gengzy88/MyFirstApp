package com.gengzy.myfirstapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends Activity {
	private ListView m_ListView;
	String m_buttonName = new String("new Button");
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        
//        Button button = (Button)findViewById(R.id.button1);
//        button.setText(m_buttonName);
//        button.setOnClickListener(new Button.OnClickListener() {
//			
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Intent intent = getIntent();
//				intent.setClass(MainActivity.this, MyService.class);
//				startService(intent);
//			}
//		});
        
        m_ListView = new ListView(this){
			@Override
			public boolean onTouchEvent(MotionEvent ev) {
				// TODO Auto-generated method stub
				return super.onTouchEvent(ev);
			}
        	
        };
        
        Cursor cursor = MyDatabaseHelper.getMyDatabaseHelper(this).getReadableDatabase().rawQuery("select * from " + MyDatabaseHelper.MYTABLENAME, null);
        startManagingCursor(cursor);
        
//        ListAdapter listAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_expandable_list_item_1, 
//        		cursor, new String[]{MyDatabaseHelper.MESSAGE_COLUME, MyDatabaseHelper.NAME_COLUME}, new int[]{android.R.id.text1, android.R.id.text2});
        ListAdapter listAdapter = new SimpleCursorAdapter(this, R.layout.msg_item, 
        		cursor, new String[]{"_id", MyDatabaseHelper.NAME_COLUME, MyDatabaseHelper.MESSAGE_COLUME}, new int[]{R.id.textView3, R.id.textView1, R.id.textView2}){
        	
       };

        m_ListView.setAdapter(listAdapter);
		setContentView(m_ListView);
		MyDatabaseHelper.getMyDatabaseHelper(this).close();
		//MyDatabaseHelper.getMyDatabaseHelper(this).getReadableDatabase().rawQuery("select * from " + MyDatabaseHelper.MYTABLENAME, null);
    }

    public void onClickMsg(View v) {
//    	int pos = m_ListView.getPositionForView(v);
//    	SQLiteDatabase db = MyDatabaseHelper.getMyDatabaseHelper(this).getWritableDatabase();
//    	TextView textView = (TextView)m_ListView.findFocus().findViewById(R.id.textView3);
//    	String string = (String) textView.getText();
//    	String sqlString = "delete from " + MyDatabaseHelper.MYTABLENAME + " where _id = " + string;
//    	db.delete(MyDatabaseHelper.MYTABLENAME, "_id = " + string, null);
//    	MyDatabaseHelper.getMyDatabaseHelper(this).close();

    	Intent intent = getIntent();
    	intent.setClass(this, MyService.class);
    	finish();
    	startService(intent);
    	Intent surfaceIntent = new Intent();
    	surfaceIntent.setClass(this, MySurfaceActivity.class);
    	startActivity(surfaceIntent);
    	
    	//m_ListView.postInvalidate();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    
}
