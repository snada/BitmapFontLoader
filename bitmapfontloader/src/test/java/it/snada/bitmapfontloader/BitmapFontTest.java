package it.snada.bitmapfontloader;

import android.util.SparseArray;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

import org.mockito.internal.util.reflection.Whitebox;

import it.snada.bitmapfontloader.BitmapChar;
import it.snada.bitmapfontloader.BitmapFont;
import it.snada.bitmapfontloader.TextureChannelContent;

import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
public class BitmapFontTest {
    BitmapChar chr;

    BitmapChar chr2;

    BitmapFont font;

    SparseArray<String> pagesMock;

    SparseArray<BitmapChar> charsMock;

    SparseArray<BitmapChar> firstKernings;

    SparseArray<SparseArray<BitmapChar>> kerningsMock;

    @Before
    public void setUp() throws Exception {
        font = new BitmapFont();

        chr = PowerMockito.mock(BitmapChar.class);
        PowerMockito.when(chr, "getId").thenReturn(97);

        chr2 = PowerMockito.mock(BitmapChar.class);
        PowerMockito.when(chr2, "getId").thenReturn(98);

        pagesMock = PowerMockito.mock(SparseArray.class);
        PowerMockito.doNothing().when(pagesMock, "put", 0, "value");
        PowerMockito.when(pagesMock, "get", 0).thenReturn("value");
        PowerMockito.when(pagesMock, "get", 100).thenReturn(null);
        PowerMockito.when(pagesMock, "size").thenReturn(2);

        charsMock = PowerMockito.mock(SparseArray.class);
        PowerMockito.doNothing().when(charsMock, "put", 97, chr);
        PowerMockito.when(charsMock, "get", 97).thenReturn(chr);

        firstKernings = PowerMockito.mock(SparseArray.class);
        PowerMockito.when(firstKernings, "get", 98).thenReturn(2);

        kerningsMock = PowerMockito.mock(SparseArray.class);
        PowerMockito.when(kerningsMock, "get", 97).thenReturn(firstKernings);

        Whitebox.setInternalState(font, "pages", pagesMock);
        Whitebox.setInternalState(font, "chars", charsMock);
        Whitebox.setInternalState(font, "kernings", kerningsMock);
    }

    @Test
    public void testFace() throws Exception {
        font.setFace("face");
        assertEquals("face", font.getFace());
    }

    @Test
    public void testSize() throws Exception {
        font.setSize(1);
        assertEquals(1, font.getSize());
    }

    @Test
    public void testBold() throws Exception {
        font.setBold(false);
        assertEquals(false, font.isBold());
    }

    @Test
    public void testItalic() throws Exception {
        font.setItalic(false);
        assertEquals(false, font.isItalic());
    }

    @Test
    public void testUnicode() throws Exception {
        font.setUnicode(false);
        assertEquals(false, font.isUnicode());
    }

    @Test
    public void testCharset() throws Exception {
        font.setCharset("charset");
        assertEquals("charset", font.getCharset());
    }

    @Test
    public void testHeightPercentage() throws Exception {
        font.setHeightPercentage(2);
        assertEquals(2, font.getHeightPercentage());
    }

    @Test
    public void testSmooth() throws Exception {
        font.setSmooth(true);
        assertEquals(true, font.isSmooth());
    }

    @Test
    public void testSuperSampling() throws Exception {
        font.setSuperSampling(3);
        assertEquals(3, font.getSuperSampling());
    }

    @Test
    public void testOutline() throws Exception {
        font.setOutline(4);
        assertEquals(4, font.getOutline());
    }

    @Test
    public void testPaddingUp() throws Exception {
        font.setPaddingUp(5);
        assertEquals(5, font.getPaddingUp());
    }

    @Test
    public void testPaddingRight() throws Exception {
        font.setPaddingRight(6);
        assertEquals(6, font.getPaddingRight());
    }

    @Test
    public void testPaddingDown() throws Exception {
        font.setPaddingDown(7);
        assertEquals(7, font.getPaddingDown());
    }

    @Test
    public void testPaddingLeft() throws Exception {
        font.setPaddingLeft(8);
        assertEquals(8, font.getPaddingLeft());
    }

    @Test
    public void testHorizontalSpacing() throws Exception {
        font.setHorizontalSpacing(9);
        assertEquals(9, font.getHorizontalSpacing());
    }

    @Test
    public void testVerticalSpacing() throws Exception {
        font.setVerticalSpacing(10);
        assertEquals(10, font.getVerticalSpacing());
    }

    @Test
    public void testLineHeight() throws Exception {
        font.setLineHeight(11);
        assertEquals(11, font.getLineHeight());
    }

    @Test
    public void testBase() throws Exception {
        font.setBase(12);
        assertEquals(12, font.getBase());
    }

    @Test
    public void testScaleW() throws Exception {
        font.setScaleW(13);
        assertEquals(13, font.getScaleW());
    }

    @Test
    public void testScaleH() throws Exception {
        font.setScaleH(14);
        assertEquals(14, font.getScaleH());
    }

    @Test
    public void testPacked() throws Exception {
        font.setPacked(true);
        assertEquals(true, font.isPacked());
    }

    @Test
    public void testGetPage() throws Exception {
        font.insertPage(0, "value");
        assertEquals("value", font.getPage(0));
    }

    @Test
    public void tesGetNotExistingPage() throws Exception {
        font.insertPage(0, "value");
        assertEquals(null, font.getPage(100));
    }

    @Test
    public void testGetPagesNumber() throws Exception {
        assertEquals(2, font.getPagesNumber());
    }

    @Test
    public void testAlphaChannel() throws Exception {
        font.setAlphaChannel(TextureChannelContent.GLYPH_AND_OUTLINE);
        assertEquals(TextureChannelContent.GLYPH_AND_OUTLINE, font.getAlphaChannel());
    }

    @Test
    public void testRedChannel() throws Exception {
        font.setRedChannel(TextureChannelContent.GLYPH_AND_OUTLINE);
        assertEquals(TextureChannelContent.GLYPH_AND_OUTLINE, font.getRedChannel());
    }

    @Test
    public void testGreenChannel() throws Exception {
        font.setGreenChannel(TextureChannelContent.GLYPH_AND_OUTLINE);
        assertEquals(TextureChannelContent.GLYPH_AND_OUTLINE, font.getGreenChannel());
    }

    @Test
    public void testBlueChannel() throws Exception {
        font.setBlueChannel(TextureChannelContent.GLYPH_AND_OUTLINE);
        assertEquals(TextureChannelContent.GLYPH_AND_OUTLINE, font.getBlueChannel());
    }

    @Test
    public void testInsertChar() throws Exception {
        font.insertChar(chr);
        assertEquals(chr, font.getChar(("a")));
    }

    @Test
    public void testGetChar() throws Exception {
        font.getChar("a");
        assertEquals(chr, font.getChar("a"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetCharWithTooLongString() throws Exception {
        font.getChar("aa");
    }

    @Test
    public void testGetKerning() throws Exception {
        assertSame(2, font.getKerning(chr, chr2));
    }
}