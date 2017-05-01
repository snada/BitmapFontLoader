package it.snada.bitmap_font_loader;

/**
 * Texture channel in which char image can be found
 */
public enum GlyphChannelContent {
    BLUE,
    GREEN,
    RED,
    ALPHA,
    ALL;

    public static GlyphChannelContent valueOf(int value) {
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
