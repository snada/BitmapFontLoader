package it.snada.bitmap_3d_string;

import android.opengl.Matrix;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import it.snada.bitmap_font_loader.BitmapChar;

/**
 * Helper class to handle 3d bitmap chars
 */
public class Bitmap3DChar extends Bitmap3DObject{
    Bitmap3DString string3D;

    BitmapChar bitmapChar;

    private FloatBuffer uvBuffer;

    public Bitmap3DChar(Bitmap3DString string3D, BitmapChar bitmapChar, float cursorPosition) {
        super(
            cursorPosition + bitmapChar.getXOffset(),
            -bitmapChar.getYOffset(),
            0,
            0.0f, 0.0f, 0.0f,
            1.0f, 1.0f, 1.0f
        );

        this.string3D = string3D;
        this.bitmapChar = bitmapChar;

        float[] uvs = {
            this.getTopLeftU(), this.getTopLeftV(),
            this.getBottomLeftU(), this.getBottomLeftV(),
            this.getBottomRightU(), this.getBottomRightV(),
            this.getTopRightU(), this.getTopRightV()
        };
        ByteBuffer ulb = ByteBuffer.allocateDirect(uvs.length * 4);
        ulb.order(ByteOrder.nativeOrder());
        uvBuffer = ulb.asFloatBuffer();
        uvBuffer.put(uvs);
        uvBuffer.position(0);
    }

    /**
     * Returns the uv buffer to apply over square geometry
     * @return FloatBuffer containing uv data
     */
    public FloatBuffer getUvBuffer() {
        return this.uvBuffer;
    }

    /**
     * Returns a model matrix to transform a 1.0f x 1.0f geometry centered on it's top left corner ready to be drawn as part of a 3d string on screen.
     * @return a float array representing a model matrix
     */
    public float[] getModelMatrix() {
        float[] positionMatrix = new float[16];
        float[] rotationMatrix = new float[16];
        float[] scaleMatrix = new float[16];
        Matrix.setIdentityM(positionMatrix, 0);
        Matrix.setIdentityM(rotationMatrix, 0);
        Matrix.setIdentityM(scaleMatrix, 0);

        Matrix.scaleM(
            scaleMatrix,
            0,
            (this.bitmapChar.getWidth() * this.getScaleX()),
            (this.bitmapChar.getHeight() * this.getScaleY()),
            this.getScaleZ()
        );

        //Painful Android bug. Could be done with:
        //Matrix.setRotateEulerM(rotationMatrix, 0, rotation[0], rotation[1], rotation[2]);
        //But returns a wrong matrix on Y axis
        Matrix.rotateM(rotationMatrix, 0, this.getRotationX(), 1.0f, 0.0f, 0.0f);
        Matrix.rotateM(rotationMatrix, 0, this.getRotationY(), 0.0f, 1.0f, 0.0f);
        Matrix.rotateM(rotationMatrix, 0, this.getRotationZ(), 0.0f, 0.0f, 1.0f);

//        Matrix.translateM(
//            positionMatrix, 0,
//            this.string3D.getPositionX() + (this.getPositionX() * this.string3D.getScaleX()),
//            this.string3D.getPositionY() + (this.getPositionY() * this.string3D.getScaleY()),
//            this.string3D.getPositionZ() + (this.getPositionZ() * this.string3D.getScaleZ())
//        );


        Matrix.translateM(
            positionMatrix, 0,
            this.string3D.getPositionX() + (this.getPositionX()),
            this.string3D.getPositionY() + (this.getPositionY()),
            this.string3D.getPositionZ() + (this.getPositionZ())
        );

        float[] tmpMatrix1 = new float[16];
        Matrix.multiplyMM(tmpMatrix1, 0, rotationMatrix, 0, scaleMatrix, 0);

        float[] tmpMatrix2 = new float[16];
        Matrix.multiplyMM(tmpMatrix2, 0, positionMatrix, 0, tmpMatrix1, 0);

        float[] tmpMatrix3 = new float[16];
        Matrix.multiplyMM(tmpMatrix3, 0, this.string3D.getScaleMatrix(), 0, tmpMatrix2, 0);

        float[] tmpMatrix4 = new float[16];
        Matrix.multiplyMM(tmpMatrix4, 0, this.string3D.getRotationMatrix(), 0, tmpMatrix3, 0);

        float[] modelMatrix = new float[16];
        Matrix.multiplyMM(modelMatrix, 0, this.string3D.getTranslationMatrix(), 0, tmpMatrix4, 0);

        return modelMatrix;
    }

    public float getTopLeftU() {
        return reverseLerp(0, this.string3D.getBitmapFont().getScaleW(), bitmapChar.getX());
    }

    public float getTopLeftV() {
        return reverseLerp(0, this.string3D.getBitmapFont().getScaleH(), bitmapChar.getY());
    }

    public float getBottomLeftU() {
        return reverseLerp(0, this.string3D.getBitmapFont().getScaleW(), bitmapChar.getX());
    }

    public float getBottomLeftV() {
        return reverseLerp(0, this.string3D.getBitmapFont().getScaleH(), bitmapChar.getY() + bitmapChar.getHeight());
    }

    public float getTopRightU() {
        return reverseLerp(0, this.string3D.getBitmapFont().getScaleW(), bitmapChar.getX() + bitmapChar.getWidth());
    }

    public float getTopRightV() {
        return reverseLerp(0, this.string3D.getBitmapFont().getScaleH(), bitmapChar.getY());
    }

    public float getBottomRightU() {
        return reverseLerp(0, this.string3D.getBitmapFont().getScaleW(), bitmapChar.getX() + bitmapChar.getWidth());
    }

    public float getBottomRightV() {
        return reverseLerp(0, this.string3D.getBitmapFont().getScaleH(), bitmapChar.getY() + bitmapChar.getHeight());
    }

    private static float reverseLerp(float a, float b, float value) {
        return (value - a) / (b - a);
    }
}
