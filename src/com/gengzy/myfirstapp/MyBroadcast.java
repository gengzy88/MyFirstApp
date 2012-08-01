package com.gengzy.myfirstapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class MyBroadcast extends BroadcastReceiver{
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Bundle bundle = intent.getExtras();
		Object messageObject[] = (Object[])bundle.get("pdus");
		int length = messageObject.length;
		for (int i = 0; i < length; ++i) {
			SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) messageObject[i]);
			String smsContent = smsMessage.getMessageBody();
			Message message = Message.obtain();
			message.setData(bundle);
			
			Intent intent2 = new Intent();
//			intent2.setClass(context, MainActivity.class);
//			intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//			context.startActivity(intent2);
			intent2.setClass(context, MyService.class);
			context.startService(intent2);
			
			abortBroadcast();
			Toast.makeText(context, smsContent, Toast.LENGTH_SHORT).show();
		}
	}
}
