package it.snada.bitmapfontloader;

public class BitmapChar {
    /**
     * Char id
     */
    private int id;

    /**
     * Char left position in texture file
     */
    private int x;

    /**
     * Char top position in texture file
     */
    private int y;

    /**
     * Char width in texture file
     */
    private int width;

    /**
     * Char height in texture file
      */
    private int height;

    /**
     * How much the current x position should be offset when copying the image from the texture to the screen
     */
    private int xOffset;

    /**
     * How much the current y position should be offset when copying the image from the texture to the screen
     */
    private int yOffset;

    /**
     * How much the current position should be advanced after drawing the char
     */
    private int xAdvance;

    /**
     * Texture page containing this char
     */
    private int page;

    /**
     * Texture channel in which char image is found
     */
    private GlyphChannel channel;

    /**
     * Creates a BitmapChar, mapping font information on a single glyph
     * @param id char value as int
     * @param x The left position of the character image in the texture
     * @param y The top position of the character image in the texture
     * @param width The width of the character image in the texture
     * @param height The height of the character image in the texture.
     * @param xOffset How much the current position should be offset when copying the image from the texture to the screen
     * @param yOffset How much the current position should be offset when copying the image from the texture to the screen
     * @param xAdvance How much the current position should be advanced after drawing the character
     * @param page The texture page id where the character image is found
     * @param channel The texture channel where the character image is found
     */
    public BitmapChar(int id, int x, int y, int width, int height, int xOffset, int yOffset, int xAdvance, int page, GlyphChannel channel) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.xAdvance = xAdvance;
        this.page = page;
        this.channel = channel;
    }

    /**
     * Gets the id, or char value
     * @return int value
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id, or char value
     * @param id int value
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets X, the left position of the character image in the texture
     * @return int value
     */
    public int getX() {
        return x;
    }

    /**
     * Sets X, the left position of the character image in the texture
     * @param x int value
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets Y, the top position of the character image in the texture
     * @return int value
     */
    public int getY() {
        return y;
    }

    /**
     * Sets Y, The top position of the character image in the texture
     * @param y int value
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Gets the width of the character image in the texture.
     * @return int value
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets the width of the character image in the texture
     * @param width int value
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Gets the height of the character image in the texture.
     * @return int value
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the height of the character image in the texture.
     * @param height int value
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Gets X offset, or how much the X position should be offset when copying the image from the texture
     * @return int value
     */
    public int getXOffset() {
        return xOffset;
    }

    /**
     * Sets X offset, or how much the X position should be offset when copying the image from the texture
     * @param xOffset int value
     */
    public void setXOffset(int xOffset) {
        this.xOffset = xOffset;
    }

    /**
     * Gets Y offset, or how much the Y position should be offset when copying the image from the texture
     * @return int value
     */
    public int getYOffset() {
        return yOffset;
    }

    /**
     * Sets Y offset, or how much the Y position should be offset when copying the image from the texture
     * @param yOffset int value
     */
    public void setYOffset(int yOffset) {
        this.yOffset = yOffset;
    }

    /**
     * Gets X advance, or how much X value should be incremented after this char is rendered
     * @return int value
     */
    public int getXAdvance() {
        return xAdvance;
    }

    /**
     * Sets X advance, or how much X value should be incremented after this char is rendered
     * @param xAdvance int value
     */
    public void setXAdvance(int xAdvance) {
        this.xAdvance = xAdvance;
    }

    /**
     * Gets texture page id
     * @return int value
     */
    public int getPage() {
        return page;
    }

    /**
     * Sets texture page id
     * @param page int value
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * Gets which channel contains glyph data
     * @return GlyphChannel value
     */
    public GlyphChannel getChannel() {
        return channel;
    }

    /**
     * Sets which channel contains glyph data
     * @param channel GlyphChannel value
     */
    public void setChannel(GlyphChannel channel) {
        this.channel = channel;
    }
}
