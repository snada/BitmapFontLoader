package it.snada.bitmap_3d_string;

import java.util.ArrayList;
import java.util.List;

import it.snada.bitmap_font_loader.BitmapChar;
import it.snada.bitmap_font_loader.BitmapFont;

/**
 * Helper class to handle 3d bitmap fonts
 */
public class Bitmap3DString {
    private BitmapFont font;

    private String text;

    private float scale;

    private List<Bitmap3DChar> chars3d;

    float[] position;

    public Bitmap3DString(BitmapFont font, String text) {
        this(font, text, 0.0f, 0.0f, 0.0f);
    }

    public Bitmap3DString(BitmapFont font, String text, float xPosition, float yPosition, float zPosition) {
        this.font = font;
        this.scale = 1.0f;

        this.position = new float[3];
        this.position[0] = xPosition;
        this.position[1] = yPosition;
        this.position[2] = zPosition;

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

        float cursor = this.getPositionX();
        for(int counter = 0; counter < text.length(); counter++) {
            BitmapChar chr = font.getChar(text.charAt(counter));
            Bitmap3DChar newChar = new Bitmap3DChar(
                    this.font,
                    chr,
                    cursor,
                    this.getPositionY(),
                    this.getPositionZ(),
                    this.getScale()
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
    }

    public List<Bitmap3DChar> get3dChars() {
        return this.chars3d;
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
