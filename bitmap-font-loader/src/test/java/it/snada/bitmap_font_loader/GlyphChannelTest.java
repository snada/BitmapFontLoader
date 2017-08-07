package it.snada.bitmap_font_loader;

import org.junit.Test;

import static org.junit.Assert.*;

public class GlyphChannelTest {
    @Test
    public void testValueOfBlue() throws Exception {
        assertEquals(GlyphChannel.BLUE, GlyphChannel.valueOf(1));
    }

    @Test
    public void testValueOfGreen() throws Exception {
        assertEquals(GlyphChannel.GREEN, GlyphChannel.valueOf(2));
    }


    @Test
    public void testValueOfRed() throws Exception {
        assertEquals(GlyphChannel.RED, GlyphChannel.valueOf(4));
    }

    @Test
    public void testValueOfAlpha() throws Exception {
        assertEquals(GlyphChannel.ALPHA, GlyphChannel.valueOf(8));
    }

    @Test
    public void testValueOfAll() throws Exception {
        assertEquals(GlyphChannel.ALL, GlyphChannel.valueOf(15));
    }

    @Test
    public void testValueOfOutOfRange() throws Exception {
        assertEquals(null, GlyphChannel.valueOf(16));
    }
}
