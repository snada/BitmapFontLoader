package it.snada.bitmapfontloaderapp;

import android.opengl.Matrix;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class Plane {
    public FloatBuffer vertexBuffer;
    public FloatBuffer colorBuffer;
    public ShortBuffer indexBuffer;
    public FloatBuffer uvBuffer;

    private float[] position;
    private float[] positionMatrix;

    private float[] rotation;
    private float[] rotationMatrix;

    public Plane(float[] vertices, short[] drawOrder, float[] colors, float[] uvs) {
        if(vertices.length != 12) {
            throw new RuntimeException("You must provide 12 coordinates for a plane to be constructed");
        }

        if(drawOrder.length != 6) {
            throw new RuntimeException("You must provide 6 values for indices");
        }

        if(colors.length != 16) {
            throw new RuntimeException("You must provide 4 values per vertex, R, G, B, A");
        }

        if(uvs.length != 8) {
            throw new RuntimeException("You must provide 2 values per vertex");
        }

        ByteBuffer bb = ByteBuffer.allocateDirect(vertices.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);

        ByteBuffer dlb = ByteBuffer.allocateDirect(drawOrder.length * 2);
        dlb.order(ByteOrder.nativeOrder());
        indexBuffer = dlb.asShortBuffer();
        indexBuffer.put(drawOrder);
        indexBuffer.position(0);

        ByteBuffer clb = ByteBuffer.allocateDirect(colors.length * 4);
        clb.order(ByteOrder.nativeOrder());
        colorBuffer = clb.asFloatBuffer();
        colorBuffer.put(colors);
        colorBuffer.position(0);

        ByteBuffer ulb = ByteBuffer.allocateDirect(uvs.length * 4);
        ulb.order(ByteOrder.nativeOrder());
        uvBuffer = ulb.asFloatBuffer();
        uvBuffer.put(uvs);
        uvBuffer.position(0);

        position = new float[3];
        positionMatrix = new float[16];
        Matrix.setIdentityM(positionMatrix, 0);

        rotation = new float[3];
        rotationMatrix = new float[16];
        Matrix.setIdentityM(rotationMatrix, 0);
    }

    public void setRotationX(float amount) {
        rotation[0] = amount;
    }

    public void setRotationY(float amount) {
        rotation[1] = amount;
    }

    public void setRotationZ(float amount) {
        rotation[2] = amount;
    }

    public void setPositionX(float amount) {
        position[0] = amount;
    }

    public void setPositionY(float amount) {
        position[1] = amount;
    }

    public void setPositionZ(float amount) {
        position[2] = amount;
    }

    public float getPositionX() {
        return position[0];
    }

    public float getPositionY() {
        return position[1];
    }

    public float getPositionZ() {
        return position[2];
    }

    public float getRotationX() {
        return rotation[0];
    }

    public float getRotationY() {
        return rotation[1];
    }

    public float getRotationZ() {
        return rotation[2];
    }

    public float[] getModelMatrix() {
        //Painful Android bug. Could be done with:
        //Matrix.setRotateEulerM(rotationMatrix, 0, rotation[0], rotation[1], rotation[2]);
        //But returns a wrong matrix on Y axis
        Matrix.setIdentityM(rotationMatrix, 0);
        Matrix.rotateM(rotationMatrix, 0, rotation[0], 1.0f, 0.0f, 0.0f);
        Matrix.rotateM(rotationMatrix, 0, rotation[1], 0.0f, 1.0f, 0.0f);
        Matrix.rotateM(rotationMatrix, 0, rotation[2], 0.0f, 0.0f, 1.0f);
        Matrix.translateM(positionMatrix, 0, position[0], position[1], position[2]);

        float[] modelMatrix = new float[16];
        Matrix.multiplyMM(modelMatrix, 0, positionMatrix, 0, rotationMatrix, 0);
        return modelMatrix;
    }
}
