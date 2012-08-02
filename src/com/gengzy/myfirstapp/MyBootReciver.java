package com.gengzy.myfirstapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class MyBootReciver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		BroadcastReceiver broadcastReceiver = new MyBroadcast();
		IntentFilter intentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
		context.registerReceiver(broadcastReceiver, intentFilter);
	}

}
