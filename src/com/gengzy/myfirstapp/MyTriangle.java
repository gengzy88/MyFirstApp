package com.gengzy.myfirstapp;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

//import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES10;
import android.opengl.GLSurfaceView;

public class MyTriangle {
	private FloatBuffer floatBuffer;
	public MyTriangle(){
		float triangleCoods[] = {
				-0.5f, -0.25f, 0.6f,  
                0.5f, -0.25f, 0.6f,  
                0.0f,  0.559016994f, -0.6f	
		};
		
		int len = triangleCoods.length;
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(triangleCoods.length*4);//ByteBuffer.allocate(triangleCoods.length*4);
		byteBuffer.order(ByteOrder.nativeOrder());
		floatBuffer = byteBuffer.asFloatBuffer();
		floatBuffer.put(triangleCoods);
		floatBuffer.position(0);
		//floatBuffer.order();
	}
	
	public void draw(GL10 gl){
		gl.glColor4f(0.5f, 0.3f, 0.3f, 0.5f);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, floatBuffer);
		gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);
	}
}
