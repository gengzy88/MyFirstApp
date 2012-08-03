package com.gengzy.myfirstapp;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class MyBroadcast extends BroadcastReceiver{
	private MyDatabaseHelper m_databaseHelper;
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		m_databaseHelper = MyDatabaseHelper.getMyDatabaseHelper(context);
		SQLiteDatabase db = m_databaseHelper.getWritableDatabase();
//		db.beginTransaction();
		
		Bundle bundle = intent.getExtras();
		
		Handler handler = MyService.getMyServiceHandler();
		if(null == handler){
			return;
		}
		Message message = Message.obtain();
		message.what = MyService.RECV_MSG;
		message.obj = bundle;
		handler.sendMessage(message);
		//message.
		Object messageObject[] = (Object[])bundle.get("pdus");
		int length = messageObject.length;
		for (int i = 0; i < length; ++i) {
			ContentValues contentValues = new ContentValues();
			SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) messageObject[i]);
			String smsContent = smsMessage.getMessageBody();
			String no = smsMessage.getOriginatingAddress();
			SystemClock.sleep(5000);
//			Message message = Message.obtain();
//			message.setData(bundle);
			
			contentValues.put(MyDatabaseHelper.NAME_COLUME, no);
			contentValues.put(MyDatabaseHelper.MESSAGE_COLUME, smsContent);
			long id = db.insert(MyDatabaseHelper.MYTABLENAME, null, contentValues);
			
			Intent intent2 = new Intent();
//			intent2.setClass(context, MainActivity.class);
//			intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//			context.startActivity(intent2);
			intent2.setClass(context, MyService.class);
			context.startService(intent2);
			
			abortBroadcast();
			
			//Toast.makeText(context, smsContent, Toast.LENGTH_SHORT).show();
		}
//		db.endTransaction();
//		db.setTransactionSuccessful();
	}
}
