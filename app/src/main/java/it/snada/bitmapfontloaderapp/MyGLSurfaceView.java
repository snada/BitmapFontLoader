package it.snada.bitmapfontloaderapp;

import android.content.Context;
import android.opengl.GLSurfaceView;

/**
 * Created by snada on 15/04/2017.
 */

public class MyGLSurfaceView extends GLSurfaceView {
    public MyGLSurfaceView(Context context) {
        super(context);
        setEGLContextClientVersion(2);

        setRenderer((MainActivity)context);
    }
}
