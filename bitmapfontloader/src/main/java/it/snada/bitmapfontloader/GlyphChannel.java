package it.snada.bitmapfontloader;

/**
 * Texture channel in which char image can be found
 */
public enum GlyphChannel {
    BLUE,
    GREEN,
    RED,
    ALPHA,
    ALL;

    /**
     * Gets a GlyphChannel enum value from an integer number
     * @param value int value
     * @return GlyphChannel enum value, or null
     */
    public static GlyphChannel valueOf(int value) {
        if(value == 1) {
            return BLUE;
        } else if(value == 2) {
            return GREEN;
        } else if(value == 4) {
            return RED;
        } else if(value == 8) {
            return ALPHA;
        } else if(value == 15) {
            return ALL;
        } else {
            return null;
        }
    }
}
