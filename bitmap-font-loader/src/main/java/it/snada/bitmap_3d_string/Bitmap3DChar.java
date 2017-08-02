package it.snada.bitmap_3d_string;

import it.snada.bitmap_font_loader.BitmapChar;
import it.snada.bitmap_font_loader.BitmapFont;

/**
 * Helper class to handle 3d bitmap chars
 */
public class Bitmap3DChar {
    BitmapFont font;

    BitmapChar bitmapChar;

    float scale;

    float[] position;

    public Bitmap3DChar(BitmapFont bitmapFont, BitmapChar bitmapChar, float cursorPosition, float stringYPosition, float stringZPosition, float scale) {
        this.font = bitmapFont;
        this.bitmapChar = bitmapChar;

        this.scale = scale;

        this.position = new float[3];
        this.position[0] = cursorPosition + bitmapChar.getXOffset();
        this.position[1] = stringYPosition - bitmapChar.getYOffset();
        this.position[2] = stringZPosition;
    }

    public float getTopLeftU() {
        return reverseLerp(0, font.getScaleW(), bitmapChar.getX());
    }

    public float getTopLeftV() {
        return reverseLerp(0, font.getScaleH(), bitmapChar.getY());
    }

    public float getBottomLeftU() {
        return reverseLerp(0, font.getScaleW(), bitmapChar.getX());
    }

    public float getBottomLeftV() {
        return reverseLerp(0, font.getScaleH(), bitmapChar.getY() + bitmapChar.getHeight());
    }

    public float getTopRightU() {
        return reverseLerp(0, font.getScaleW(), bitmapChar.getX() + bitmapChar.getWidth());
    }

    public float getTopRightV() {
        return reverseLerp(0, font.getScaleH(), bitmapChar.getY());
    }

    public float getBottomRightU() {
        return reverseLerp(0, font.getScaleW(), bitmapChar.getX() + bitmapChar.getWidth());
    }

    public float getBottomRightV() {
        return reverseLerp(0, font.getScaleH(), bitmapChar.getY() + bitmapChar.getHeight());
    }

    private static float reverseLerp(float a, float b, float value) {
        return (value - a) / (b - a);
    }

    public void setPositionX(float amount) {
        this.position[0] = amount;
    }

    public void setPositionY(float amount) {
        this.position[1] = amount;
    }

    public void setPositionZ(float amount) {
        this.position[2] = amount;
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
}
