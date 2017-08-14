package it.snada.bitmapfontloader;

import org.junit.Before;
import org.junit.Test;

import it.snada.bitmapfontloader.BitmapChar;
import it.snada.bitmapfontloader.GlyphChannel;

import static org.junit.Assert.*;

public class BitmapCharTest {
    BitmapChar chr;

    @Before
    public void setUp() throws Exception {
        chr = new BitmapChar(1, 2, 3, 4, 5, 6, 7, 8, 9, GlyphChannel.ALL);
    }

    @Test
    public void testGetId() throws Exception {
        assertEquals(1, chr.getId());
    }

    @Test
    public void testSetId() throws Exception {
        chr.setId(100);
        assertEquals(100, chr.getId());
    }

    @Test
    public void testGetX() throws Exception {
        assertEquals(2, chr.getX());
    }

    @Test
    public void testSetX() throws Exception {
        chr.setX(200);
        assertEquals(200, chr.getX());
    }

    @Test
    public void testGetY() throws Exception {
        assertEquals(3, chr.getY());
    }

    @Test
    public void testSetY() throws Exception {
        chr.setY(300);
        assertEquals(300, chr.getY());
    }

    @Test
    public void testGetWidth() throws Exception {
        assertEquals(4, chr.getWidth());
    }

    @Test
    public void testSetWidth() throws Exception {
        chr.setWidth(400);
        assertEquals(400, chr.getWidth());
    }

    @Test
    public void testGetHeight() throws Exception {
        assertEquals(5, chr.getHeight());
    }

    @Test
    public void testSetHeight() throws Exception {
        chr.setHeight(500);
        assertEquals(500, chr.getHeight());
    }

    @Test
    public void testGetXOffset() throws Exception {
        assertEquals(6, chr.getXOffset());
    }

    @Test
    public void testSetXOffset() throws Exception {
        chr.setXOffset(600);
        assertEquals(600, chr.getXOffset());
    }

    @Test
    public void testGetYOffset() throws Exception {
        assertEquals(7, chr.getYOffset());
    }

    @Test
    public void testSetYOffset() throws Exception {
        chr.setYOffset(700);
        assertEquals(700, chr.getYOffset());
    }

    @Test
    public void testGetXAdvance() throws Exception {
        assertEquals(8, chr.getXAdvance());
    }

    @Test
    public void testSetXAdvance() throws Exception {
        chr.setXAdvance(800);
        assertEquals(800, chr.getXAdvance());
    }

    @Test
    public void testGetPage() throws Exception {
        assertEquals(9, chr.getPage());
    }

    @Test
    public void testSetPage() throws Exception {
        chr.setPage(900);
        assertEquals(900, chr.getPage());
    }

    @Test
    public void testGetChannel() throws Exception {
        assertEquals(GlyphChannel.ALL, chr.getChannel());
    }

    @Test
    public void testSetChannel() throws Exception {
        chr.setChannel(GlyphChannel.ALPHA);
        assertEquals(GlyphChannel.ALPHA, chr.getChannel());
    }
}
