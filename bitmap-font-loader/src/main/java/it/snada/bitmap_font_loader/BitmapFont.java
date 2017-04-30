package it.snada.bitmap_font_loader;

import android.util.SparseArray;

import java.util.HashMap;
import java.util.Map;

public class BitmapFont {
    /**
     * Font face (name)
     */
    private String face;

    /**
     * Font size (in pixels)
     */
    private int size;

    /**
     * Font is bold
     */
    private boolean bold;

    /**
     * Font is italic
     */
    private boolean italic;

    /**
     * Font is unicode
     */
    private boolean unicode;

    /**
     * Font charset, if not unicode
     */
    private String charset;

    /**
     * Font height stretch
     */
    private int heightPercentage;

    /**
     * Font smooth value
     */
    private boolean smooth;

    /**
     * Font super sampling
     */
    private int superSampling;

    /**
     * Font outline thickness
     */
    private int outline;

    /**
     * Font padding up
     */
    private int paddingUp;

    /**
     * Font padding right
     */
    private int paddingRight;

    /**
     * Font padding down
     */
    private int paddingDown;

    /**
     * Font padding left
     */
    private int paddingLeft;

    /**
     * Font horizontal spacing
     */
    private int horizontalSpacing;

    /**
     * Font vertical spacing
     */
    private int verticalSpacing;

    /**
     * Font pages
     */
    private SparseArray<String> pages;

    public BitmapFont() {
        pages = new SparseArray<String>();
    }

    /**
     * Font face name
     * @return String
     */
    public String getFace() {
        return face;
    }

    /**
     * Sets font face name
     * @param face String value
     */
    public void setFace(String face) {
        this.face = face;
    }

    /**
     * Font size
     * @return int value
     */
    public int getSize() {
        return size;
    }

    /**
     * Sets font size
     * @param size
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Font bold value
     * @return boolean value
     */
    public boolean isBold() {
        return bold;
    }

    /**
     * Sets font bold value
     * @param bold boolean value
     */
    public void setBold(boolean bold) {
        this.bold = bold;
    }

    /**
     * Font italic value
     * @return boolean value
     */
    public boolean isItalic() {
        return italic;
    }

    /**
     * Sets font italic value
     * @param italic boolean value
     */
    public void setItalic(boolean italic) {
        this.italic = italic;
    }

    /**
     * Font unicode value
     * @return boolean value
     */
    public boolean isUnicode() {
        return unicode;
    }

    /**
     * Sets font unicode value
     * @param unicode boolean value
     */
    public void setUnicode(boolean unicode) {
        this.unicode = unicode;
    }

    /**
     * Font charset, if not unicode
     * @return String value
     */
    public String getCharset() {
        return charset;
    }

    /**
     * Sets font charset
     * @param charset String value
     */
    public void setCharset(String charset) {
        this.charset = charset;
    }

    /**
     * Font height percentage, or height stretch
     * @return int value
     */
    public int getHeightPercentage() {
        return heightPercentage;
    }

    /**
     * Sets font height percentage, or height stretch
     * @param heightPercentage int value
     */
    public void setHeightPercentage(int heightPercentage) {
        this.heightPercentage = heightPercentage;
    }

    /**
     * Font smooth value
     * @return boolean value
     */
    public boolean isSmooth() {
        return smooth;
    }

    /**
     * Sets font smooth value
     * @param smooth boolean value
     */
    public void setSmooth(boolean smooth) {
        this.smooth = smooth;
    }

    /**
     * Font super sampling value, between 1 (no super sampling) and 4
     * @return int value
     */
    public int getSuperSampling() {
        return superSampling;
    }

    /**
     * Font super sampling, should be between 1 (no super sampling) and 4
     * @param superSampling
     */
    public void setSuperSampling(int superSampling) {
        this.superSampling = superSampling;
    }

    /**
     * Font outline thickness, greater or equal than 0
     * @return
     */
    public int getOutline() {
        return outline;
    }

    /**
     * Sets font outline thickness, should be greater or equal 0
     * @param outline
     */
    public void setOutline(int outline) {
        this.outline = outline;
    }

    /**
     * Font up padding
     * @return int value
     */
    public int getPaddingUp() {
        return paddingUp;
    }

    /**
     * Sets font up padding, greater or equal 0
     * @param paddingUp int value
     */
    public void setPaddingUp(int paddingUp) {
        this.paddingUp = paddingUp;
    }

    /**
     * Font right padding
     * @return int value
     */
    public int getPaddingRight() {
        return paddingRight;
    }

    /**
     * Sets font right padding, greater or equal 0
     * @param paddingRight int value
     */
    public void setPaddingRight(int paddingRight) {
        this.paddingRight = paddingRight;
    }

    /**
     * Font down padding
     * @return int value
     */
    public int getPaddingDown() {
        return paddingDown;
    }

    /**
     * Sets font down padding, greater or equal 0
     * @param paddingDown int value
     */
    public void setPaddingDown(int paddingDown) {
        this.paddingDown = paddingDown;
    }

    /**
     * Font left padding
     * @return int value
     */
    public int getPaddingLeft() {
        return paddingLeft;
    }

    /**
     * Sets font left padding, greater or equal 0
     * @param paddingLeft int value
     */
    public void setPaddingLeft(int paddingLeft) {
        this.paddingLeft = paddingLeft;
    }

    /**
     * Font horizaontal spacing
     * @return int value
     */
    public int getHorizontalSpacing() {
        return horizontalSpacing;
    }

    /**
     * Sets font horizontal spacing, greater or equal 0
     * @param horizontalSpacing int value
     */
    public void setHorizontalSpacing(int horizontalSpacing) {
        this.horizontalSpacing = horizontalSpacing;
    }

    /**
     * Font vertical spacing
     * @return int value
     */
    public int getVerticalSpacing() {
        return verticalSpacing;
    }

    /**
     * Sets font vertical spacing, greater or equal 0
     * @param verticalSpacing int value
     */
    public void setVerticalSpacing(int verticalSpacing) {
        this.verticalSpacing = verticalSpacing;
    }

    public void insertPage(int id, String value) {
        pages.append(id, value);
    }

    public String getPage(int id) {
        return pages.get(id);
    }
}
