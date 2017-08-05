package it.snada.bitmap_3d_string;

import android.opengl.Matrix;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import it.snada.bitmap_font_loader.BitmapChar;

/**
 * Sample class to represent a single 3D char of a 3D string.
 * Contains transformation for the single char as well as uv coordinates and buffers of the bitmap texture
 */
public class Bitmap3DChar extends Bitmap3DObject{
    Bitmap3DString string3D;

    BitmapChar bitmapChar;

    private FloatBuffer uvBuffer;

    /**
     * Creates a 3D char
     * @param string3D a Bitmap3DString instance
     * @param bitmapChar the char of the string
     * @param cursorPosition passed by the Bitmap3DString containing this 3D char, is the cursor position
     */
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
     * Gets the matrix that will be used to translate this 3D char
     * @return float[] object
     */
    public float[] getTranslationMatrix() {
        Matrix.setIdentityM(this.translationMatrix, 0);
        Matrix.translateM(
            this.translationMatrix, 0,
            this.string3D.getPositionX() + this.getPositionX(),
            this.string3D.getPositionY() + this.getPositionY(),
            this.string3D.getPositionZ() + this.getPositionZ()
        );
        return this.translationMatrix;
    }

    /**
     * Gets the matrix that will be used to rotate this 3D char
     * @return float[] object
     */
    public float[] getRotationMatrix() {
        Matrix.setIdentityM(this.rotationMatrix, 0);
        //Painful Android bug. Could be done with:
        //Matrix.setRotateEulerM(rotationMatrix, 0, rotation[0], rotation[1], rotation[2]);
        //But returns a wrong matrix on Y axis
        Matrix.rotateM(this.rotationMatrix, 0, this.getRotationX(), 1.0f, 0.0f, 0.0f);
        Matrix.rotateM(this.rotationMatrix, 0, this.getRotationY(), 0.0f, 1.0f, 0.0f);
        Matrix.rotateM(this.rotationMatrix, 0, this.getRotationZ(), 0.0f, 0.0f, 1.0f);

        return this.rotationMatrix;
    }

    /**
     * Gets the matrix that will be used to scale this 3D char
     * @return float[] object
     */
    public float[] getScaleMatrix() {
        Matrix.setIdentityM(scaleMatrix, 0);

        Matrix.scaleM(
            scaleMatrix,
            0,
            (this.bitmapChar.getWidth() * this.getScaleX()),
            (this.bitmapChar.getHeight() * this.getScaleY()),
            this.getScaleZ()
        );

        return this.scaleMatrix;
    }

    /**
     * Returns a model matrix to transform a 1.0f x 1.0f geometry centered on it's top left corner ready to be drawn as part of a 3d string on screen.
     * @return a float array representing a model matrix
     */
    public float[] getModelMatrix() {
        float[] tmpMatrix1 = new float[16];
        Matrix.multiplyMM(tmpMatrix1, 0, this.getRotationMatrix(), 0, this.getScaleMatrix(), 0);

        float[] tmpMatrix2 = new float[16];
        Matrix.multiplyMM(tmpMatrix2, 0, this.getTranslationMatrix(), 0, tmpMatrix1, 0);

        float[] modelMatrix = new float[16];
        Matrix.multiplyMM(modelMatrix, 0, this.string3D.getModelMatrix(), 0, tmpMatrix2, 0);

        return modelMatrix;
    }

    /**
     * U value for top left corner of the quad
     * @return float value
     */
    public float getTopLeftU() {
        return reverseLerp(0, this.string3D.getBitmapFont().getScaleW(), bitmapChar.getX());
    }

    /**
     * V value for top left corner of the quad
     * @return float value
     */
    public float getTopLeftV() {
        return reverseLerp(0, this.string3D.getBitmapFont().getScaleH(), bitmapChar.getY());
    }

    /**
     * U value for bottom left corner of the quad
     * @return float value
     */
    public float getBottomLeftU() {
        return reverseLerp(0, this.string3D.getBitmapFont().getScaleW(), bitmapChar.getX());
    }

    /**
     * V value for bottom left corner of the quad
     * @return float value
     */
    public float getBottomLeftV() {
        return reverseLerp(0, this.string3D.getBitmapFont().getScaleH(), bitmapChar.getY() + bitmapChar.getHeight());
    }

    /**
     * U value for top right corner of the quad
     * @return float value
     */
    public float getTopRightU() {
        return reverseLerp(0, this.string3D.getBitmapFont().getScaleW(), bitmapChar.getX() + bitmapChar.getWidth());
    }

    /**
     * V value for top right corner of the quad
     * @return float value
     */
    public float getTopRightV() {
        return reverseLerp(0, this.string3D.getBitmapFont().getScaleH(), bitmapChar.getY());
    }

    /**
     * U value for bottom right corner of the quad
     * @return float value
     */
    public float getBottomRightU() {
        return reverseLerp(0, this.string3D.getBitmapFont().getScaleW(), bitmapChar.getX() + bitmapChar.getWidth());
    }

    /**
     * V value for bottom right corner of the quad
     * @return float value
     */
    public float getBottomRightV() {
        return reverseLerp(0, this.string3D.getBitmapFont().getScaleH(), bitmapChar.getY() + bitmapChar.getHeight());
    }

    /**
     * Linear interpolation inverse, from two values and a middle one, getting t value.
     * Transforms a texture pixel dimension in a [0, 1] range coordinate
     * @param a first value
     * @param b second value
     * @param value value between a and b
     * @return the linear interpolation value between 0 and 1 that would return value
     */
    private static float reverseLerp(float a, float b, float value) {
        return (value - a) / (b - a);
    }
}
