package it.snada.bitmap_3d_string;

import it.snada.bitmap_font_loader.BitmapChar;
import it.snada.bitmap_font_loader.BitmapFont;

/**
 * Helper class to handle 3d bitmap chars
 */
public class Bitmap3DChar {
    BitmapFont font;

    BitmapChar bitmapChar;

    public Bitmap3DChar(BitmapFont bitmapFont, BitmapChar bitmapChar) {
        this.font = bitmapFont;
        this.bitmapChar = bitmapChar;
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
}
