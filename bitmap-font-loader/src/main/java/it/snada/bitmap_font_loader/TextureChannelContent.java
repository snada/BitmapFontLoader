package it.snada.bitmap_font_loader;

/**
 * Enum
 */
public enum TextureChannelContent {
    GLYPH_DATA,
    OUTLINE,
    GLYPH_AND_OUTLINE,
    ZERO,
    ONE;

    /**
     * Gets a TextureChannelContent enum value from an integer number
     * @param value int value
     * @return TextureChannelContent enum value, or null
     */
    public static TextureChannelContent valueOf(int value) {
        if(value == 0) {
            return GLYPH_DATA;
        } else if(value == 1) {
            return OUTLINE;
        } else if(value == 2) {
            return GLYPH_AND_OUTLINE;
        } else if(value == 3) {
            return ZERO;
        } else if(value == 4) {
            return ONE;
        } else {
            return null;
        }
    }
}
