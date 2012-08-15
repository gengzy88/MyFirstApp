package com.gengzy.myfirstapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.Window;
import android.view.WindowManager;

public class MySurfaceActivity extends Activity{
	private MySurfaceView m_surfaceView;
	private SurfaceHolder m_surfaceHolder;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
//		m_surfaceView = new MySurfaceView(this);
//		m_surfaceHolder = m_surfaceView.getHolder();
		MyGlSerfaceView glSurfaceView = new MyGlSerfaceView(this);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,   
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
//		setContentView(m_surfaceView);
		setContentView(glSurfaceView);
		
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		//m_surfaceView.onTouch(event);
		return true;
		//return super.onTouchEvent(event);
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

}
