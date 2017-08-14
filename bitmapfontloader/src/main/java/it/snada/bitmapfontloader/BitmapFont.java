package it.snada.bitmapfontloader;

import android.util.SparseArray;

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
     * Font distance, in pixel, between each line of text
     */
    private int lineHeight;

    /**
     * The number of pixels from the absolute top of the line to the base of the characters
     */
    private int base;

    /**
     * Textures width
     */
    private int scaleW;

    /**
     * Textures height
     */
    private int scaleH;

    /**
     * Set to 1 if the monochrome characters have been packed into each of the texture channels. In this case alphaChnl describes what is stored in each channel
     */
    private boolean packed;

    /**
     * Texture alpha channel content
     */
    private TextureChannelContent alphaChannel;

    /**
     * Texture red channel content
     */
    private TextureChannelContent redChannel;

    /**
     * Texture green channel content
     */
    private TextureChannelContent greenChannel;

    /**
     * Texture blue channel content
     */
    private TextureChannelContent blueChannel;

    /**
     * Font pages
     */
    private SparseArray<String> pages;

    /**
     * Font chars
     */
    private SparseArray<BitmapChar> chars;

    /**
     * Kernings
     */
    private SparseArray<SparseArray<Integer>> kernings;

    public BitmapFont() {
        pages = new SparseArray<>();
        chars = new SparseArray<>();
        kernings = new SparseArray<>();
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

    /**
     * Inserts a texture page for this font
     * @param id int value, page index
     * @param value String value, texture name
     */
    public void insertPage(int id, String value) {
        pages.put(id, value);
    }

    /**
     * Gets a texture name given a page id
     * @param id int value
     * @return String value, texture name
     */
    public String getPage(int id) {
        return pages.get(id);
    }

    /**
     * Return number of pages of this font
     * @return int value
     */
    public int getPagesNumber() {
        return pages.size();
    }

    /**
     * Gets distance in pixels between 2 lines
     * @return int value
     */
    public int getLineHeight() {
        return lineHeight;
    }

    /**
     * Sets distance in pixels between 2 lines
     * @param lineHeight int value
     */
    public void setLineHeight(int lineHeight) {
        this.lineHeight = lineHeight;
    }

    /**
     * Gets the number of pixels from the absolute top of the line to the base of the characters
     * @return int value
     */
    public int getBase() {
        return base;
    }

    /**
     * Sets the number of pixels from the absolute top of the line to the base of the characters
     * @param base int value
     */
    public void setBase(int base) {
        this.base = base;
    }

    /**
     * Gets texture width, in pixels
     * @return int value
     */
    public int getScaleW() {
        return scaleW;
    }

    /**
     * Sets texture width, in pixels
     * @param scaleW int value
     */
    public void setScaleW(int scaleW) {
        this.scaleW = scaleW;
    }

    /**
     * Gets texture height, in pixels
     * @return int value
     */
    public int getScaleH() {
        return scaleH;
    }

    /**
     * Sets texture height, in pixels
     * @param scaleH int value
     */
    public void setScaleH(int scaleH) {
        this.scaleH = scaleH;
    }

    /**
     * Gets true if the monochrome characters have been packed into each of the texture channels
     * @return boolean value
     */
    public boolean isPacked() {
        return packed;
    }

    /**
     * Sets true if the monochrome characters have been packed into each of the texture channels.
     * @param packed boolean value
     */
    public void setPacked(boolean packed) {
        this.packed = packed;
    }

    /**
     * Gets the content type for the alpha channel of textures
     * @return TextureChannelContent value
     */
    public TextureChannelContent getAlphaChannel() {
        return alphaChannel;
    }

    /**
     * Sets the content type for the alpha channel of textures
     * @param alphaChannel TextureChannelContent value
     */
    public void setAlphaChannel(TextureChannelContent alphaChannel) {
        this.alphaChannel = alphaChannel;
    }

    /**
     * Gets the content type for the red channel of textures
     * @return TextureChannelContent value
     */
    public TextureChannelContent getRedChannel() {
        return redChannel;
    }

    /**
     * Sets the content type for the red channel of textures
     * @param redChannel TextureChannelContent value
     */
    public void setRedChannel(TextureChannelContent redChannel) {
        this.redChannel = redChannel;
    }

    /**
     * Gets the content type for the green channel of textures
     * @return TextureChannelContent value
     */
    public TextureChannelContent getGreenChannel() {
        return greenChannel;
    }

    /**
     * Sets the content type for the green channel of textures
     * @param greenChannel TextureChannelContent value
     */
    public void setGreenChannel(TextureChannelContent greenChannel) {
        this.greenChannel = greenChannel;
    }

    /**
     * Gets the content type for the blue channel of textures
     * @return TextureChannelContent value
     */
    public TextureChannelContent getBlueChannel() {
        return blueChannel;
    }

    /**
     * Sets the content type for the blue channel of textures
     * @param blueChannel TextureChannelContent value
     */
    public void setBlueChannel(TextureChannelContent blueChannel) {
        this.blueChannel = blueChannel;
    }

    /**
     * Inserts into this font a Bitmap character
     * @param bitmapChar bitmap char
     */
    public void insertChar(BitmapChar bitmapChar) {
        chars.put(bitmapChar.getId(), bitmapChar);
    }

    /**
     * Gets a bitmap char for this font, if string is exactly 1 char long, throws IllegalArgumentException otherwise
     * @param letter String value containing one char
     * @return BitmapChar value
     */
    public BitmapChar getChar(String letter) {
        if(letter.length() != 1) {
            throw new IllegalArgumentException("You can only pass a single character");
        }
        return getChar(letter.charAt(0));
    }

    /**
     * Gets a bitmap char for a given char
     * @param letter char value
     * @return BitmapChar value
     */
    public BitmapChar getChar(char letter) {
        return getChar((int)letter);
    }

    /**
     * Gets a bitmap char by id, or char int value
     * @param letter char int value
     * @return BitmapChar value
     */
    public BitmapChar getChar(int letter) {
        return chars.get(letter);
    }

    /**
     * Inserts a new kerning (x adjustment for second char) for a given pair
     * @param first BitmapChar char id
     * @param second BitmapChar char id
     * @param amount int value
     */
    public void insertKerning(BitmapChar first, BitmapChar second, int amount) {
        insertKerning(first.getId(), second.getId(), amount);
    }

    /**
     * Inserts a new kerning (x adjustment for second char) for a given pair
     * @param first int char id
     * @param second int char id
     * @param amount int value
     */
    public void insertKerning(int first, int second, int amount) {
        SparseArray<Integer> firstKernings = kernings.get(first);

        if(firstKernings == null) {
            SparseArray<Integer> newSparseArray = new SparseArray<>();
            kernings.put(first, newSparseArray);
            firstKernings = newSparseArray;
        }

        firstKernings.put(second, amount);
    }

    /**
     * Gets a kerning amount given a pair of chars
     * @param first BitmapChar value
     * @param second BitmapChar value
     * @return int amount
     */
    public int getKerning(BitmapChar first, BitmapChar second) {
        return getKerning(first.getId(), second.getId());
    }

    /**
     * Gets a kerning amount given a pair of chars
     * @param first int value
     * @param second int value
     * @return int amount
     */
    public int getKerning(int first, int second) {
        try {
            return kernings.get(first).get(second);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Cannot find a kerning with this pair: " + first + " " + second);
        }
    }
}
