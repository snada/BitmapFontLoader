package it.snada.bitmapfontloaderapp;

import android.opengl.GLES20;

public class ShaderProgram {
    private int id;

    public ShaderProgram(String vertexShaderCode, String fragmentShaderCode) {
        int vertexShader = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER);
        GLES20.glShaderSource(vertexShader, vertexShaderCode);
        GLES20.glCompileShader(vertexShader);
        checkCompile(vertexShader);

        int fragmentShader = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);
        GLES20.glShaderSource(fragmentShader, fragmentShaderCode);
        GLES20.glCompileShader(fragmentShader);
        checkCompile(fragmentShader);

        id = GLES20.glCreateProgram();
        GLES20.glAttachShader(id, vertexShader);
        GLES20.glAttachShader(id, fragmentShader);
        GLES20.glLinkProgram(id);
    }

    private void checkCompile(int shader) {
        final int[] compileStatus = new int[1];
        GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compileStatus, 0);

        if(compileStatus[0] == 0) {
            GLES20.glDeleteShader(shader);
            throw new RuntimeException("Failed to compile shader: " + GLES20.glGetShaderInfoLog(shader));
        }
    }

    public int getId() {
        return id;
    }
}
