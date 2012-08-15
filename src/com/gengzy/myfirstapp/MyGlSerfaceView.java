package com.gengzy.myfirstapp;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class MyGlSerfaceView extends GLSurfaceView{
	private MyTriangle myTriangle;
	private MySquare mySquare;
	private MyGlRender myRender;
	public MyGlSerfaceView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		myTriangle = new MyTriangle();
		myRender = new MyGlRender();
		mySquare = new MySquare();
		setRenderer(myRender);
		
		
	}

	private class MyGlRender implements GLSurfaceView.Renderer{

		public void onDrawFrame(GL10 gl) {
			// TODO Auto-generated method stub
			//gl.glClearColor(1.0f, 0.5f, 0.5f, 0.5f);
			gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
			myTriangle.draw(gl);
			mySquare.draw(gl);
		}

		public void onSurfaceChanged(GL10 gl, int width, int height) {
			// TODO Auto-generated method stub
			gl.glViewport(0, 0, width, height);
		}

		public void onSurfaceCreated(GL10 gl, EGLConfig config) {
			// TODO Auto-generated method stub
			gl.glClearColor(1.0f, 0.5f, 0.5f, 0.5f);
			gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		}
		
	}
}
