package com.gengzy.myfirstapp;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.R.integer;

public class MySquare {
	private FloatBuffer vertextBuffer;
	private static float fZ = 0;
	public MySquare(){
		float square[] = {
			-0.5f, -0.5f, 0.3f,
			0.5f, -0.5f, 0.3f,
			-0.5f, 0.5f, 0.3f,
			0.5f, 0.5f, 0.3f,
		};
		
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(square.length*4);
		byteBuffer.order(ByteOrder.nativeOrder());
		vertextBuffer = byteBuffer.asFloatBuffer();
		vertextBuffer.put(square);
		vertextBuffer.position(0);
	}
	
	public void draw(GL10 gl){
		gl.glColor4f(0.3f, 0.5f, 1.0f, 1.0f);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertextBuffer);
//		gl.glTranslatef(0, 0, -0.5f);
//		gl.glRotatef(0.2f, 0.0f, 0.5f, 0.0f);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
		
	}
}
