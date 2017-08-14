package it.snada.bitmapfontloader;

import org.junit.Test;

import it.snada.bitmapfontloader.TextureChannelContent;

import static org.junit.Assert.*;

public class TextureChannelContentTest {
    @Test
    public void testValueOfGlyphData() throws Exception {
        assertEquals(TextureChannelContent.GLYPH_DATA, TextureChannelContent.valueOf(0));
    }

    @Test
    public void testValueOfOutline() throws Exception {
        assertEquals(TextureChannelContent.OUTLINE, TextureChannelContent.valueOf(1));
    }

    @Test
    public void testValueOfGlyphAndOutline() throws Exception {
        assertEquals(TextureChannelContent.GLYPH_AND_OUTLINE, TextureChannelContent.valueOf(2));
    }

    @Test
    public void testValueOfZero() throws Exception {
        assertEquals(TextureChannelContent.ZERO, TextureChannelContent.valueOf(3));
    }

    @Test
    public void testValueOfOne() throws Exception {
        assertEquals(TextureChannelContent.ONE, TextureChannelContent.valueOf(4));
    }

    @Test
    public void testValueOfOutOfRange() throws Exception {
        assertEquals(null, TextureChannelContent.valueOf(5));
    }
}
