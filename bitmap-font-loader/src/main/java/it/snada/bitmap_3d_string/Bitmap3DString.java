package it.snada.bitmap_3d_string;

import android.opengl.Matrix;

import java.util.ArrayList;
import java.util.List;

import it.snada.bitmap_font_loader.BitmapChar;
import it.snada.bitmap_font_loader.BitmapFont;

/**
 * Helper class to handle 3d bitmap fonts
 */
public class Bitmap3DString extends Bitmap3DObject {
    private BitmapFont font;

    private String text;

    private List<Bitmap3DChar> chars3d;

    private int width = 0;

    private boolean centered = false;
    private float[] centerMatrix;

    public Bitmap3DString(BitmapFont font, String text) {
        this(font, text, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
    }

    public Bitmap3DString(BitmapFont font, String text, float xPosition, float yPosition, float zPosition, float xRotation, float yRotation, float zRotation, float xScale, float yScale, float zScale) {
        super(xPosition, yPosition, zPosition, xRotation, yRotation, zRotation, xScale, yScale, zScale);

        this.font = font;
        this.setText(text);

        this.centerMatrix = new float[16];
        Matrix.setIdentityM(this.centerMatrix, 0);
    }

    public BitmapFont getBitmapFont() {
        return this.font;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
        this.chars3d = new ArrayList<>(text.length());

        int cursor = 0;
        for(int counter = 0; counter < text.length(); counter++) {
            BitmapChar chr = font.getChar(text.charAt(counter));
            Bitmap3DChar newChar = new Bitmap3DChar(
                    this,
                    chr,
                    cursor
            );
            chars3d.add(newChar);

            int kerningValue = 0;
            if(counter != text.length() -1) {
                try {
                    kerningValue = font.getKerning(chr.getId(), text.charAt(counter + 1));
                } catch(IllegalArgumentException e) {
                    //Nothing to do here
                }
            }

            cursor += (chr.getXAdvance() + kerningValue);
        }
        this.width = cursor;
    }

    public List<Bitmap3DChar> get3dChars() {
        return this.chars3d;
    }

    public float[] getScaleMatrix() {
        Matrix.setIdentityM(this.scaleMatrix, 0);

        Matrix.scaleM(this.scaleMatrix, 0, this.getScaleX(), this.getScaleY(), this.getScaleZ());

        return this.scaleMatrix;
    }

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

    public float[] getTranslationMatrix() {
        Matrix.setIdentityM(this.translationMatrix, 0);

        Matrix.translateM(this.translationMatrix, 0, this.getPositionX(), this.getPositionY(), this.getPositionZ());

        return this.translationMatrix;
    }

    public float[] getModelMatrix() {
        float[] translationMatrix = this.getTranslationMatrix();
        float[] rotationMatrix = this.getRotationMatrix();
        float[] scaleMatrix = this.getScaleMatrix();

        float[] tmpMatrix = new float[16];
        Matrix.multiplyMM(tmpMatrix, 0, scaleMatrix, 0, this.centerMatrix, 0);

        float[] tmp1Matrix = new float[16];
        Matrix.multiplyMM(tmp1Matrix, 0, rotationMatrix, 0, tmpMatrix, 0);

        float[] modelMatrix = new float[16];
        Matrix.multiplyMM(modelMatrix, 0, translationMatrix, 0, tmp1Matrix, 0);

        return modelMatrix;
    }

    public void setCentered(boolean value) {
        if(value) {
            Matrix.translateM(this.centerMatrix, 0, -(this.width / 2), this.font.getLineHeight() / 2, 0);
        } else {
            Matrix.setIdentityM(this.centerMatrix, 0);
        }
        centered = value;
    }

    public boolean getCentered() {
        return centered;
    }
}
