package it.snada.bitmapfontloaderapp;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLUtils;

public class Texture {
    private int id;

    public Texture(Bitmap bitmap) {
        int[] handle = new int[1];
        GLES20.glGenTextures(1, handle, 0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, handle[0]);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);
        bitmap.recycle();

        if(handle[0] == 0) {
            throw new RuntimeException("Error loading texture");
        }

        id = handle[0];
    }

    public int getId() {
        return this.id;
    }
}
