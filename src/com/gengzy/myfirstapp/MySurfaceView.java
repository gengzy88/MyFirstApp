package com.gengzy.myfirstapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.MotionEvent.PointerCoords;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback{
	private MySurfaceThread m_thread;
	private SurfaceHolder m_surfaceHolder;
	private boolean m_threadIsRunning;
	
	public MySurfaceView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		m_surfaceHolder = getHolder();
		m_thread = new MySurfaceThread(m_surfaceHolder);
		m_threadIsRunning = true;
		m_thread.start();
		
	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.draw(canvas);
	}

	@Override
	public SurfaceHolder getHolder() {
		// TODO Auto-generated method stub
		return super.getHolder();
	}

	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	public void surfaceCreated(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		
	}

	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		m_threadIsRunning = false;
	}
	
	public void onTouch(MotionEvent event){
		Point point = new Point();//(event.getX(0),event.getY(0));
		point.x = (int)event.getX(0);
		point.y = (int)event.getY(0);

		Canvas canvas = null;
		
		try {
			synchronized (m_surfaceHolder) {
				canvas = m_surfaceHolder.lockCanvas();
				canvas.drawColor(Color.BLACK);
				Paint paint = new Paint();
				paint.setColor(Color.BLUE);
				Rect rect = new Rect(point.x, point.y, point.x + 30, point.y + 30);
				canvas.drawRect(rect, paint);
				//canvas.
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		finally{
			if(canvas != null){
				m_surfaceHolder.unlockCanvasAndPost(canvas);
			}
		}
	}
}

class MySurfaceThread extends Thread{
	private SurfaceHolder m_surfaceHolder;
	public MySurfaceThread(SurfaceHolder surfaceHolder) {
		// TODO Auto-generated constructor stub
		m_surfaceHolder = surfaceHolder;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			Canvas canvas = null;
			
			try {
				synchronized (m_surfaceHolder) {
					canvas = m_surfaceHolder.lockCanvas();
					canvas.drawColor(Color.BLACK);
					Paint paint = new Paint();
					paint.setColor(Color.BLUE);
					Rect rect = new Rect(100, 50, 300, 250);
					canvas.drawRect(rect, paint);
					//canvas.
					
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			finally{
				if(canvas != null){
					m_surfaceHolder.unlockCanvasAndPost(canvas);
				}
			}
			
			try {
				sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
}
