package com.gengzy.myfirstapp;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.R.color;
import android.R.integer;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLU;
import android.opengl.GLUtils;
import android.provider.MediaStore.Images;

public class MyCube {
	private FloatBuffer floatBuffer;
	private FloatBuffer colorBuffer;
	private float xRot;
	private float yRot;
	private int textures[];
	private Bitmap images;
	public MyCube(){
		xRot = 1.0f;
		yRot = 0.5f;
		float box[] = new float[] {
            -0.5f, -0.5f,  0.5f,
             0.5f, -0.5f,  0.5f,
            -0.5f,  0.5f,  0.5f,
             0.5f,  0.5f,  0.5f,
            // BACK
            -0.5f, -0.5f, -0.5f,
            -0.5f,  0.5f, -0.5f,
             0.5f, -0.5f, -0.5f,
             0.5f,  0.5f, -0.5f,
            // LEFT
            -0.5f, -0.5f,  0.5f,
            -0.5f,  0.5f,  0.5f,
            -0.5f, -0.5f, -0.5f,
            -0.5f,  0.5f, -0.5f,
            // RIGHT
             0.5f, -0.5f, -0.5f,
             0.5f,  0.5f, -0.5f,
             0.5f, -0.5f,  0.5f,
             0.5f,  0.5f,  0.5f,
            // TOP
            -0.5f,  0.5f,  0.5f,
             0.5f,  0.5f,  0.5f,
             -0.5f,  0.5f, -0.5f,
             0.5f,  0.5f, -0.5f,
            // BOTTOM
            -0.5f, -0.5f,  0.5f,
            -0.5f, -0.5f, -0.5f,
             0.5f, -0.5f,  0.5f,
             0.5f, -0.5f, -0.5f,
		};
		
		float colorArray[] = new float[]{
			1.0f, 0, 0, 1.0f,
			0, 1.0f, 0, 1.0f,
			1.0f, 1.0f, 1.0f, 1.0f,
			0, 0, 1.0f, 1.0f,
			
			1.0f, 0, 0, 1.0f,
			0, 1.0f, 0, 1.0f,
			1.0f, 1.0f, 1.0f, 1.0f,
			0, 0, 1.0f, 1.0f,
			
			1.0f, 0, 0, 1.0f,
			0, 1.0f, 0, 1.0f,
			1.0f, 1.0f, 1.0f, 1.0f,
			0, 0, 1.0f, 1.0f,
			
			1.0f, 0, 0, 1.0f,
			0, 1.0f, 0, 1.0f,
			1.0f, 1.0f, 1.0f, 1.0f,
			0, 0, 1.0f, 1.0f,
			
			1.0f, 0, 0, 1.0f,
			0, 1.0f, 0, 1.0f,
			1.0f, 1.0f, 1.0f, 1.0f,
			0, 0, 1.0f, 1.0f,
			
			1.0f, 0, 0, 1.0f,
			0, 1.0f, 0, 1.0f,
			1.0f, 1.0f, 1.0f, 1.0f,
			0, 0, 1.0f, 1.0f,
		};
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(box.length*4);
		byteBuffer.order(ByteOrder.nativeOrder());
		floatBuffer = byteBuffer.asFloatBuffer();
		floatBuffer.put(box);
		floatBuffer.position(0);
		
		ByteBuffer colorByteBuffer = ByteBuffer.allocateDirect(colorArray.length*4);
		colorByteBuffer.order(ByteOrder.nativeOrder());
		colorBuffer = colorByteBuffer.asFloatBuffer();
		colorBuffer.put(colorArray);
		colorBuffer.position(0);
		
		textures = new int[6];
	}
	
	public void loadImage(Context context){
		images = BitmapFactory.decodeResource(context.getResources(), R.drawable.cloud);
		
	}
	
	public void loadTexture(GL10 gl){
		gl.glGenTextures(6, textures, 0);
		
		for (int i = 0; i < textures.length; i++) {
			gl.glBindTexture(GL10.GL_TEXTURE, textures[i]);
			gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
			gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
			GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, images, 0);
			images.recycle();
		}
	}
	
	public void onDraw(GL10 gl){
		
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		GLU.gluLookAt(gl, 0, 0, 3, 0, 0, 0, 0, 1, 0);//设置视点和模型中心位置
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		
		for (int i = 0; i < textures.length; i++) {
			gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[i]);
//			gl.glDrawElements(GL10.GL_TRIANGLE_STRIP, 4, GL10.GL_UNSIGNED_BYTE, images.);
		}
		
//		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, floatBuffer);
//		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
//
//		gl.glRotatef(xRot, 1, 0, 1);
////		gl.glRotatef(yRot, 0, 1, 0);
//		
////		gl.glColor4f(1.0f, 0, 0, 1.0f);
//		gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);
//		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
//		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 4, 4);
//		
////		gl.glColor4f(0f, 1.0f, 0, 1.0f);
////		gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);
//		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 8, 4);
//		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 12, 4);
//		
////		gl.glColor4f(0.0f, 0, 1.0f, 1.0f);
////		gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);
//		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 16, 4);
//		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 20, 4);
//		
//		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
		
		xRot += 1.0f;
		yRot += 1.0f;
	}

}

/*
 * 在Android中我们使用GLSurfaceView来显示OpenGL视图，GLSurfaceView:是其中很重要的一个类，此类位于android.opengl包下，用于管理是一块可以是复合视图机器人系统的内存的特殊的曲面。管理一个使表面呈现 OpenGL 的 EGL 显示。接受一个用户提供输入Render对象进行显示。从 UI 线程实现一个专用线程渲染界面实现3D性能。支持按需要和连续的呈现。 包装、 跟踪，和检查 OpenGL 渲染器调用的错误。所以首先我们需要创建一个GLSurfaceView。

01
public class mainActivity extends Activity {
02
    CubeRenderer mCubeRenderer;  //我们自定义的立方体Renderer
03
    @Override
04
    public void onCreate(Bundle savedInstanceState) {
05
        super.onCreate(savedInstanceState);
06
        requestWindowFeature(Window.FEATURE_NO_TITLE); // 去掉标题
07
        GLSurfaceView GLView = new GLSurfaceView(this); //创建一个GLSurfaceView
08
           mCubeRenderer = new CubeRenderer();
09
        GLView.setRenderer(mCubeRenderer);
10
        setContentView(GLView);
11
 
12
    }
13
}
接下来我们的主要工作就是去创建一个继承Renderer接口的CubeRenderer。Renderer是一个专门用来渲染3D的接口。继承它，我们需要重载以下方法：

01
public void onDrawFrame(GL10 gl)
02
{
03
    //渲染的绘图操作,重绘时调用
04
}
05
    
06
public void onSurfaceChanged(GL10 gl, int width, int height)
07
{
08
    //视窗改变时调用，通常在此设置视窗范围以及透视，投影范围
09
}
10
   
11
public void onSurfaceCreated(GL10 gl, EGLConfig config)   
12
{
13
    //创建时调用,通常在此进行初始化设置
14
}
以下是我们CubeRenderer的完整代码：

view sourceprint?
001
public class CubeRenderer implements Renderer {
002
 
003
    float box[] = new float[] {
004
            // FRONT
005
            -0.5f, -0.5f,  0.5f,
006
             0.5f, -0.5f,  0.5f,
007
            -0.5f,  0.5f,  0.5f,
008
             0.5f,  0.5f,  0.5f,
009
            // BACK
010
            -0.5f, -0.5f, -0.5f,
011
            -0.5f,  0.5f, -0.5f,
012
             0.5f, -0.5f, -0.5f,
013
             0.5f,  0.5f, -0.5f,
014
            // LEFT
015
            -0.5f, -0.5f,  0.5f,
016
            -0.5f,  0.5f,  0.5f,
017
            -0.5f, -0.5f, -0.5f,
018
            -0.5f,  0.5f, -0.5f,
019
            // RIGHT
020
             0.5f, -0.5f, -0.5f,
021
             0.5f,  0.5f, -0.5f,
022
             0.5f, -0.5f,  0.5f,
023
             0.5f,  0.5f,  0.5f,
024
            // TOP
025
            -0.5f,  0.5f,  0.5f,
026
             0.5f,  0.5f,  0.5f,
027
             -0.5f,  0.5f, -0.5f,
028
             0.5f,  0.5f, -0.5f,
029
            // BOTTOM
030
            -0.5f, -0.5f,  0.5f,
031
            -0.5f, -0.5f, -0.5f,
032
             0.5f, -0.5f,  0.5f,
033
             0.5f, -0.5f, -0.5f,
034
        };
035
 
036
    FloatBuffer cubeBuff;
037
     
038
    float xrot = 0.0f;
039
    float yrot = 0.0f;
040
     
041

046
    public FloatBuffer makeFloatBuffer(float[] arr) {
047
        ByteBuffer bb = ByteBuffer.allocateDirect(arr.length * 4);//分配缓冲空间，一个float占4个字节
048
        bb.order(ByteOrder.nativeOrder()); //设置字节顺序， 其中ByteOrder.nativeOrder()是获取本机字节顺序
049
        FloatBuffer fb = bb.asFloatBuffer(); //转换为float型
050
        fb.put(arr);        //添加数据
051
        fb.position(0);      //设置数组的起始位置
052
        return fb;
053
    }
054
     
055
    public CubeRenderer() {
056
        // TODO Auto-generated constructor stub
057
        cubeBuff = makeFloatBuffer(box);//转换float数组
058
    }
059
     
060
     
061
    protected void init(GL10 gl) {
062
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);//设置清屏时背景的颜色，R，G，B，A
063
         
064
        gl.glEnable(GL10.GL_DEPTH_TEST); //启用深度缓存
065
        gl.glEnable(GL10.GL_CULL_FACE);  //启用背面剪裁
066
        gl.glClearDepthf(1.0f);    // 设置深度缓存值
067
        gl.glDepthFunc(GL10.GL_LEQUAL);  // 设置深度缓存比较函数，GL_LEQUAL表示新的像素的深度缓存值小于等于当前像素的深度缓存值（通过gl.glClearDepthf(1.0f)设置）时通过深度测试  
068
        gl.glShadeModel(GL10.GL_SMOOTH);// 设置阴影模式GL_SMOOTH
069
    }
070
     
071
    @Override
072
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
073
        // TODO Auto-generated method stub
074
        init(gl);
075
    }
076
     
077
    @Override
078
    public void onSurfaceChanged(GL10 gl, int w, int h) {
079
        // TODO Auto-generated method stub
080
        gl.glViewport(0, 0, w, h); //设置视窗
081
        gl.glMatrixMode(GL10.GL_PROJECTION); // 设置投影矩阵
082
        gl.glLoadIdentity();  //设置矩阵为单位矩阵，相当于重置矩阵      
083
        GLU.gluPerspective(gl, 45.0f, ((float) w) / h, 0.1f, 10f);//设置透视范围 
084
    }
085
     
086
    @Override
087
    public void onDrawFrame(GL10 gl) {
088
        // TODO Auto-generated method stub
089
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);// 清除屏幕和深度缓存
090
         
091
        gl.glMatrixMode(GL10.GL_MODELVIEW);   //切换至模型观察矩阵
092
        gl.glLoadIdentity();// 重置当前的模型观察矩阵
093
        GLU.gluLookAt(gl, 0, 0, 3, 0, 0, 0, 0, 1, 0);//设置视点和模型中心位置
094
     
095
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, cubeBuff);//设置顶点数据
096
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
097
     
098
        gl.glRotatef(xrot, 1, 0, 0);  //绕着(0,0,0)与(1,0,0)即x轴旋转
099
        gl.glRotatef(yrot, 0, 1, 0);
100
         
101
        gl.glColor4f(1.0f, 0, 0, 1.0f);   //设置颜色，红色
102
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);  //绘制正方型FRONT面
103
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 4, 4);
104
     
105
        gl.glColor4f(0, 1.0f, 0, 1.0f);
106
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 8, 4);
107
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 12, 4);
108
         
109
        gl.glColor4f(0, 0, 1.0f, 1.0f);
110
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 16, 4);
111
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 20, 4);
112
     
113
        xrot += 1.0f;
114
        yrot += 0.5f;
115
    }
116
 
117
}
*/
