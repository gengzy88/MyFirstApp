package com.gengzy.myfirstapp;

import java.lang.ref.WeakReference;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
//import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.widget.Toast;

public class MyService extends Service{

	HandlerThread m_thread;
	MyServiceHandler m_handler;
	
	public final static class MyServiceHandler extends Handler{
		WeakReference<MyService> m_servicReference;
		public MyServiceHandler(Looper looper) {
			// TODO Auto-generated constructor stub
			super(looper);
		}
		
		public MyServiceHandler(MyService service){
			m_servicReference = new WeakReference<MyService>(service);
		}
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			synchronized (this) {
				SystemClock.sleep(5000);
				Toast.makeText(m_servicReference.get(), "Hello", Toast.LENGTH_SHORT).show();
			}
			super.handleMessage(msg);
		}
		public void setService(MyService service){
			m_servicReference = new WeakReference<MyService>(service);
		}
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		System.out.println("MyService onBind");
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		System.out.println("MyService onCreate");
//		m_thread = new MySecondThread("SecondThread");
//		m_thread = new HandlerThread("Data thread"){
//			
//		};
		HandlerThread thread = new MySecondThread("SecondThread");
		thread.start();
		Looper looper = thread.getLooper();
		m_handler = new MyServiceHandler(looper);
		m_handler.setService(this);
		
		BroadcastReceiver broadcastReceiver = new MyBroadcast();
		IntentFilter intentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
		this.registerReceiver(broadcastReceiver, intentFilter);
		
		super.onCreate();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		System.out.println("MyService onStart");
		Message msg = m_handler.obtainMessage();
		msg.arg1 = startId;
		m_handler.sendMessage(msg);
		
//		Context.registerReceiver(, null);
		
		super.onStart(intent, startId);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		System.out.println("MyService onDestroy");
		super.onDestroy();
	}

	
}
