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
		GLU.gluLookAt(gl, 0, 0, 3, 0, 0, 0, 0, 1, 0);//�����ӵ��ģ������λ��
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
 * ��Android������ʹ��GLSurfaceView����ʾOpenGL��ͼ��GLSurfaceView:�����к���Ҫ��һ���࣬����λ��android.opengl���£����ڹ�����һ������Ǹ�����ͼ������ϵͳ���ڴ����������档����һ��ʹ������� OpenGL �� EGL ��ʾ������һ���û��ṩ����Render���������ʾ���� UI �߳�ʵ��һ��ר���߳���Ⱦ����ʵ��3D���ܡ�֧�ְ���Ҫ�������ĳ��֡� ��װ�� ���٣��ͼ�� OpenGL ��Ⱦ�����õĴ�����������������Ҫ����һ��GLSurfaceView��

01
public class mainActivity extends Activity {
02
    CubeRenderer mCubeRenderer;  //�����Զ����������Renderer
03
    @Override
04
    public void onCreate(Bundle savedInstanceState) {
05
        super.onCreate(savedInstanceState);
06
        requestWindowFeature(Window.FEATURE_NO_TITLE); // ȥ������
07
        GLSurfaceView GLView = new GLSurfaceView(this); //����һ��GLSurfaceView
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
���������ǵ���Ҫ��������ȥ����һ���̳�Renderer�ӿڵ�CubeRenderer��Renderer��һ��ר��������Ⱦ3D�Ľӿڡ��̳�����������Ҫ�������·�����

01
public void onDrawFrame(GL10 gl)
02
{
03
    //��Ⱦ�Ļ�ͼ����,�ػ�ʱ����
04
}
05
    
06
public void onSurfaceChanged(GL10 gl, int width, int height)
07
{
08
    //�Ӵ��ı�ʱ���ã�ͨ���ڴ������Ӵ���Χ�Լ�͸�ӣ�ͶӰ��Χ
09
}
10
   
11
public void onSurfaceCreated(GL10 gl, EGLConfig config)   
12
{
13
    //����ʱ����,ͨ���ڴ˽��г�ʼ������
14
}
����������CubeRenderer���������룺

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
        ByteBuffer bb = ByteBuffer.allocateDirect(arr.length * 4);//���仺��ռ䣬һ��floatռ4���ֽ�
048
        bb.order(ByteOrder.nativeOrder()); //�����ֽ�˳�� ����ByteOrder.nativeOrder()�ǻ�ȡ�����ֽ�˳��
049
        FloatBuffer fb = bb.asFloatBuffer(); //ת��Ϊfloat��
050
        fb.put(arr);        //�������
051
        fb.position(0);      //�����������ʼλ��
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
        cubeBuff = makeFloatBuffer(box);//ת��float����
058
    }
059
     
060
     
061
    protected void init(GL10 gl) {
062
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);//��������ʱ��������ɫ��R��G��B��A
063
         
064
        gl.glEnable(GL10.GL_DEPTH_TEST); //������Ȼ���
065
        gl.glEnable(GL10.GL_CULL_FACE);  //���ñ������
066
        gl.glClearDepthf(1.0f);    // ������Ȼ���ֵ
067
        gl.glDepthFunc(GL10.GL_LEQUAL);  // ������Ȼ���ȽϺ�����GL_LEQUAL��ʾ�µ����ص���Ȼ���ֵС�ڵ��ڵ�ǰ���ص���Ȼ���ֵ��ͨ��gl.glClearDepthf(1.0f)���ã�ʱͨ����Ȳ���  
068
        gl.glShadeModel(GL10.GL_SMOOTH);// ������ӰģʽGL_SMOOTH
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
        gl.glViewport(0, 0, w, h); //�����Ӵ�
081
        gl.glMatrixMode(GL10.GL_PROJECTION); // ����ͶӰ����
082
        gl.glLoadIdentity();  //���þ���Ϊ��λ�����൱�����þ���      
083
        GLU.gluPerspective(gl, 45.0f, ((float) w) / h, 0.1f, 10f);//����͸�ӷ�Χ 
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
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);// �����Ļ����Ȼ���
090
         
091
        gl.glMatrixMode(GL10.GL_MODELVIEW);   //�л���ģ�͹۲����
092
        gl.glLoadIdentity();// ���õ�ǰ��ģ�͹۲����
093
        GLU.gluLookAt(gl, 0, 0, 3, 0, 0, 0, 0, 1, 0);//�����ӵ��ģ������λ��
094
     
095
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, cubeBuff);//���ö�������
096
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
097
     
098
        gl.glRotatef(xrot, 1, 0, 0);  //����(0,0,0)��(1,0,0)��x����ת
099
        gl.glRotatef(yrot, 0, 1, 0);
100
         
101
        gl.glColor4f(1.0f, 0, 0, 1.0f);   //������ɫ����ɫ
102
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);  //����������FRONT��
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
