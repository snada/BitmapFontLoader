package it.snada.bitmap_3d_string;

import it.snada.bitmap_font_loader.BitmapChar;
import it.snada.bitmap_font_loader.BitmapFont;

/**
 * Helper class to handle 3d bitmap chars
 */
public class Bitmap3DChar {
    int chr;

    BitmapFont font;

    BitmapChar bitmapChar;

    Bitmap3DString bitmap3DString;

    public Bitmap3DChar(Bitmap3DString bitmap3DString, String chr) {
        this(bitmap3DString, chr.charAt(0));
    }

    public Bitmap3DChar(Bitmap3DString bitmap3DString, char chr) {
        this(bitmap3DString, (int)chr);
    }

    public Bitmap3DChar(Bitmap3DString bitmap3DString, int chr) {
        this.chr = chr;
        this.bitmapChar = font.getChar(chr);
        this.font = bitmap3DString.getBitmapFont();
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
