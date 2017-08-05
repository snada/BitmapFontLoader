package it.snada.bitmap_3d_string;

import android.opengl.Matrix;

import java.util.ArrayList;
import java.util.List;

import it.snada.bitmap_font_loader.BitmapChar;
import it.snada.bitmap_font_loader.BitmapFont;

/**
 * Sample class to handle 3D rendering of a string using the bitmap-font-loader library
 * Apply transformations to an instance of this class to have them applied on every character of the string
 */
public class Bitmap3DString extends Bitmap3DObject {
    private BitmapFont font;

    private String text;

    private List<Bitmap3DChar> chars3d;

    private int width = 0;

    private boolean centered = false;
    private float[] centerMatrix;

    /**
     * Create a 3D string of a BitmapFont, centered in the origin, with no rotation and original scale
     * @param font a BitmapFont containing AngelCode data
     * @param text String object
     */
    public Bitmap3DString(BitmapFont font, String text) {
        this(font, text, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
    }

    /**
     * Create a 3D string of a BitmapFont, with custom position, rotation and scale
     * @param font a BitmapFont containing AngelCode data
     * @param text String object
     * @param xPosition position on x axis
     * @param yPosition position on y axis
     * @param zPosition position on z axis
     * @param xRotation rotation on x axis
     * @param yRotation rotation on y axis
     * @param zRotation rotation on z axis
     * @param xScale scale on x axis
     * @param yScale scale on y axis
     * @param zScale scale on z axis
     */
    public Bitmap3DString(BitmapFont font, String text, float xPosition, float yPosition, float zPosition, float xRotation, float yRotation, float zRotation, float xScale, float yScale, float zScale) {
        super(xPosition, yPosition, zPosition, xRotation, yRotation, zRotation, xScale, yScale, zScale);

        this.font = font;
        this.setText(text);

        this.centerMatrix = new float[16];
        Matrix.setIdentityM(this.centerMatrix, 0);
    }

    /**
     * Returns the BitmapFont instance this string is dependent on
     * @return BitmapFont object
     */
    public BitmapFont getBitmapFont() {
        return this.font;
    }

    /**
     * Returns the current text this 3D string is displaying
     * @return String object
     */
    public String getText() {
        return this.text;
    }

    /**
     * Changes the text this 3D string is displaying. Will change the character list and width, but not transformations
     * @param text String object
     */
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

    /**
     * Gets the current list of 3D chars
     * @return Bitmap3DChar List object
     */
    public List<Bitmap3DChar> get3dChars() {
        return this.chars3d;
    }

    /**
     * Gets the matrix that will be used to scale this 3DString
     * @return float[] object
     */
    public float[] getScaleMatrix() {
        Matrix.setIdentityM(this.scaleMatrix, 0);

        Matrix.scaleM(this.scaleMatrix, 0, this.getScaleX(), this.getScaleY(), this.getScaleZ());

        return this.scaleMatrix;
    }

    /**
     * Gets the matrix that will be used to rotate this 3DString
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
     * Gets the matrix that will be used to translate this 3DString
     * @return float[] object
     */
    public float[] getTranslationMatrix() {
        Matrix.setIdentityM(this.translationMatrix, 0);

        Matrix.translateM(this.translationMatrix, 0, this.getPositionX(), this.getPositionY(), this.getPositionZ());

        return this.translationMatrix;
    }

    /**
     * Gets the matrix that will be applied to every character after their own transformation
     * @return float[] object
     */
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

    /**
     * Set this to true to center this 3D String both horizontally and vertically
     * @param value boolean value
     */
    public void setCentered(boolean value) {
        if(value) {
            Matrix.translateM(this.centerMatrix, 0, -(this.width / 2), this.font.getLineHeight() / 2, 0);
        } else {
            Matrix.setIdentityM(this.centerMatrix, 0);
        }
        centered = value;
    }

    /**
     * Gets if this string is centered or not
     * @return boolean value
     */
    public boolean getCentered() {
        return centered;
    }
}
