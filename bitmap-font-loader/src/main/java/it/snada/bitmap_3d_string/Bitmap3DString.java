package it.snada.bitmap_3d_string;

import java.util.ArrayList;
import java.util.List;

import it.snada.bitmap_font_loader.BitmapFont;

/**
 * Helper class to handle 3d bitmap fonts
 */
public class Bitmap3DString {
    private BitmapFont font;

    private String text;

    private float scale;

    private List<Bitmap3DChar> chars3d;

    Bitmap3DString(BitmapFont font, String text) {
        this.font = font;
        this.scale = 1.0f;

        this.setText(text);
    }

    public BitmapFont getBitmapFont() {
        return this.font;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;

        this.chars3d = new ArrayList<>(text.length());
        for(int counter = 0; counter < text.length(); counter++) {
            Bitmap3DChar newChar = new Bitmap3DChar(font, text.charAt(counter));
            chars3d.add(newChar);
        }
    }

    public List<Bitmap3DChar> get3dChars() {
        return this.chars3d;
    }
}
