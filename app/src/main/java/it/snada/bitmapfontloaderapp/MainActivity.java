package it.snada.bitmapfontloaderapp;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.Bundle;
import android.util.Log;

import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import it.snada.bitmap_font_loader.AngelCodeXmlLoader;
import it.snada.bitmap_font_loader.BitmapFont;

public class MainActivity extends Activity implements GLSurfaceView.Renderer {
    private GLSurfaceView mGLView;

    protected String TAG = "MainActivity";

    private float[] view;
    private float[] projection;

    Plane plane;

    Texture texture;

    ShaderProgram program;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGLView = new MyGLSurfaceView(this);
        setContentView(mGLView);
    }

    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        GLES20.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);

        view = new float[16];
        Matrix.setLookAtM(view, 0,
            0.0f, 0.0f, 1.5f,
            0.0f, 0.0f, 0.0f,
            0.0f, 1.0f, 0.0f
        );

        projection = new float[16];
        Matrix.setIdentityM(projection, 0);

        float[] vertices = {
            -0.5f, 0.5f, 0.0f, // top left
            -0.5f, -0.5f, 0.0f, // bottom left
            0.5f, -0.5f, 0.0f, // bottom right
            0.5f,0.5f, 0.0f // top right
        };
        short[] indices = {
            0, 1, 2, 0, 2, 3
        };
        float[] colors = {
            1.0f, 0.0f, 0.0f, 1.0f,
            0.0f, 1.0f, 0.0f, 1.0f,
            0.0f, 0.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 0.0f, 1.0f
        };
        float[] uvs = {
            0.0f, 1.0f,
            0.0f, 0.0f,
            1.0f, 0.0f,
            1.0f, 1.0f
        };
        plane = new Plane(vertices, indices, colors, uvs);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.arial, options);
        texture = new Texture(bitmap);

        program = new ShaderProgram(readRawTextFile(R.raw.vertex_shader), readRawTextFile(R.raw.fragment_shader));

        try {
            BitmapFont font = AngelCodeXmlLoader.load(getResources().openRawResource(R.raw.arial));
        } catch(XmlPullParserException e) {
            Log.e(TAG, "Your xml file has an error: " + e);
        } catch(IOException e) {
            Log.e(TAG, "There's an error with your file: " + e);
        }
    }

    public void onDrawFrame(GL10 unused) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        plane.setRotationY(plane.getRotationY() + 0.5f);

        GLES20.glUseProgram(program.getId());

        int positionHandle = GLES20.glGetAttribLocation(program.getId(), "vPosition");
        GLES20.glEnableVertexAttribArray(positionHandle);
        GLES20.glVertexAttribPointer(positionHandle, 3, GLES20.GL_FLOAT, false, 3 * 4, plane.vertexBuffer);

        int colorHandle = GLES20.glGetAttribLocation(program.getId(), "vColor");
        GLES20.glEnableVertexAttribArray(colorHandle);
        GLES20.glVertexAttribPointer(colorHandle, 4, GLES20.GL_FLOAT, false, 4 * 4, plane.colorBuffer);

        int uvHandle = GLES20.glGetAttribLocation(program.getId(), "vUv");
        GLES20.glEnableVertexAttribArray(uvHandle);
        GLES20.glVertexAttribPointer(uvHandle, 2, GLES20.GL_FLOAT, false, 2 * 4, plane.uvBuffer);

        int modelMatrixHandle = GLES20.glGetUniformLocation(program.getId(), "model");
        GLES20.glUniformMatrix4fv(modelMatrixHandle, 1, false, plane.getModelMatrix(), 0);

        int viewMatrixHandle = GLES20.glGetUniformLocation(program.getId(), "view");
        GLES20.glUniformMatrix4fv(viewMatrixHandle, 1, false, view, 0);

        int projectionMatrixHandle = GLES20.glGetUniformLocation(program.getId(), "projection");
        GLES20.glUniformMatrix4fv(projectionMatrixHandle, 1, false, projection, 0);

        int textureUniformHandle = GLES20.glGetUniformLocation(program.getId(), "texture");

        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texture.getId());

        GLES20.glUniform1i(textureUniformHandle, 0);

        GLES20.glDrawElements(GLES20.GL_TRIANGLE_STRIP, 6, GLES20.GL_UNSIGNED_SHORT, plane.indexBuffer);

        GLES20.glDisableVertexAttribArray(positionHandle);
    }

    public void onSurfaceChanged(GL10 unused, int width, int height) {
        GLES20.glViewport(0, 0, width, height);

        float ratio = (float)width/(float)height;

        Matrix.perspectiveM(projection, 0, 45.0f, ratio, 0.1f, 10.0f);
    }

    /**
     * Converts a raw text file into a string: useful to load shaders from text files
     *
     * @param resId The resource ID of the raw text file about to be turned into a shader.
     * @return The context of the text file, or null in case of error.
     */
    private String readRawTextFile(int resId) {
        InputStream inputStream = getResources().openRawResource(resId);
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            reader.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
