package com.gengzy.myfirstapp;

import android.app.Activity;
import android.os.Bundle;

public class MySurfaceActivity extends Activity{
	private MySurfaceView m_surfaceView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		m_surfaceView = new MySurfaceView(this);
		setContentView(m_surfaceView);
		
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
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
