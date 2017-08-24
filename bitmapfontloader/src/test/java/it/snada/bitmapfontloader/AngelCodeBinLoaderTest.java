package it.snada.bitmapfontloader;

import android.util.SparseArray;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(PowerMockRunner.class)
public class AngelCodeBinLoaderTest {
    BitmapFont font;

    InputStream streamMock;

    @Before
    public void setUp() throws Exception {
        byte[] buffer = {
            66, //B
            77, //M
            70, //F
            3,  //Version
            1,           //Block type info
            17, 0, 0, 0, //Block type size
            1, 0,        //Font size
            -96,         //Bitmask - 10100000 italic & smooth true, unicode and bold false
            2,           //Charset
            3, 0,        //Height percentage
            4,           //Supersampling
            5, 6, 7, 8,  //Padding up, right, down, left
            9, 10,       //Horizontal and vertical spacing
            11,          //Outline
            65, 66, 67,  //Font face, "ABC"

            2,           //Block type common
            15, 0, 0, 0, //Block type size
            12, 0,       //Line height
            13, 0,       //Base
            14, 0,       //ScaleW
            15, 0,       //ScaleH
            2, 0,        //Pages number
            1,           //Bitmask - 00000001, packed is set to true
            0, 1, 2, 3,  //Alpha, Red, Green and Blue Channels

            3,             //Block type pages
            8, 0, 0, 0,    //Block type size
            80, 71, 48, 0, //Page 0, "PG0"
            80, 71, 49, 0, //Page 1, "PG1"

            4,             //Block type chars
            20, 0, 0, 0,   //Block type size
            16, 0, 0, 0,   //Char id
            17, 0,         //Char x
            18, 0,         //Char y
            19, 0,         //Char width
            20, 0,         //Char height
            21, 0,         //Char xOffset
            22, 0,         //Char yOffset
            23, 0,         //Char xAdvance
            0,             //Char page
            15,            //Char channel

            5,           //Block type kernings
            10, 0, 0, 0, //Block type size
            24, 0, 0, 0, //First
            25, 0, 0, 0, //Second
            26, 0        //Amount
        };

        streamMock = new ByteArrayInputStream(buffer);

        font = new BitmapFont();

        SparseArray<String> pagesMock = PowerMockito.mock(SparseArray.class);

        final String[] insertedPages = new String[2];

        PowerMockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                insertedPages[0] = (String)invocation.getArguments()[1];
                return null;
            }
        }).when(pagesMock, "put", 0, "PG0");

        PowerMockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                insertedPages[1] = (String)invocation.getArguments()[1];
                return null;
            }
        }).when(pagesMock, "put", 1, "PG1");

        PowerMockito.doAnswer(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                return insertedPages[0];
            }
        }).when(pagesMock, "get", 0);


        PowerMockito.doAnswer(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                return insertedPages[1];
            }
        }).when(pagesMock, "get", 1);

        Whitebox.setInternalState(font, "pages", pagesMock);

        SparseArray<BitmapChar> charsMock = PowerMockito.mock(SparseArray.class);

        final BitmapChar[] insertedChars = new BitmapChar[1];

        PowerMockito.doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                insertedChars[0] = (BitmapChar)invocation.getArguments()[1];
                return null;
            }
        }).when(charsMock, "put", Matchers.eq(16), Matchers.any(BitmapChar.class));

        PowerMockito.doAnswer(new Answer<BitmapChar>() {
            @Override
            public BitmapChar answer(InvocationOnMock invocation) throws Throwable {
                return insertedChars[0];
            }
        }).when(charsMock, "get", 16);

        Whitebox.setInternalState(font, "chars", charsMock);

        int first = 24;
        int second = 25;
        int amount = 26;

        final SparseArray<Integer> firstKerningsMock = PowerMockito.mock(SparseArray.class);
        SparseArray<Integer> kerningsMock = PowerMockito.mock(SparseArray.class);

        final int[] insertedKernings = new int[1];

        PowerMockito.doAnswer(new Answer<SparseArray<Integer>>() {
            @Override
            public SparseArray<Integer> answer(InvocationOnMock invocation) throws Throwable {
                return firstKerningsMock;
            }
        }).when(kerningsMock, "get", first);

        PowerMockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                insertedKernings[0] = (int)invocation.getArguments()[1];
                return null;
            }
        }).when(firstKerningsMock, "put", second, amount);

        PowerMockito.doAnswer(new Answer<Integer>() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
                return insertedKernings[0];
            }
        }).when(firstKerningsMock, "get", second);

        Whitebox.setInternalState(font, "kernings", kerningsMock);
    }

    @Test
    public void testSize() throws Exception {
        AngelCodeBinLoader.load(font, streamMock);
        assertEquals(1,  font.getSize());
    }

    @Test
    public void testItalic() throws Exception {
        AngelCodeBinLoader.load(font, streamMock);
        assertTrue(font.isItalic());
    }

    @Test
    public void testSmooth() throws Exception {
        AngelCodeBinLoader.load(font, streamMock);
        assertTrue(font.isSmooth());
    }

    @Test
    public void testUnicode() throws Exception {
        AngelCodeBinLoader.load(font, streamMock);
        assertFalse(font.isUnicode());
    }

    @Test
    public void testBold() throws Exception {
        AngelCodeBinLoader.load(font, streamMock);
        assertFalse(font.isBold());
    }

    @Test
    public void testCharset() throws Exception {
        AngelCodeBinLoader.load(font, streamMock);
        assertEquals("2", font.getCharset());
    }

    @Test
    public void testHeightPercentage() throws Exception {
        AngelCodeBinLoader.load(font, streamMock);
        assertEquals(3, font.getHeightPercentage());
    }

    @Test
    public void testSuperSampling() throws Exception {
        AngelCodeBinLoader.load(font, streamMock);
        assertEquals(4, font.getSuperSampling());
    }

    @Test
    public void testPaddingUp() throws Exception {
        AngelCodeBinLoader.load(font, streamMock);
        assertEquals(5, font.getPaddingUp());
    }

    @Test
    public void testPaddingRight() throws Exception {
        AngelCodeBinLoader.load(font, streamMock);
        assertEquals(6, font.getPaddingRight());
    }

    @Test
    public void testPaddingDown() throws Exception {
        AngelCodeBinLoader.load(font, streamMock);
        assertEquals(7, font.getPaddingDown());
    }

    @Test
    public void testPaddingLeft() throws Exception {
        AngelCodeBinLoader.load(font, streamMock);
        assertEquals(8, font.getPaddingLeft());
    }

    @Test
    public void testHorizontalSpacing() throws Exception {
        AngelCodeBinLoader.load(font, streamMock);
        assertEquals(9, font.getHorizontalSpacing());
    }

    @Test
    public void testVerticalSpacing() throws Exception {
        AngelCodeBinLoader.load(font, streamMock);
        assertEquals(10, font.getVerticalSpacing());
    }

    @Test
    public void testOutline() throws Exception {
        AngelCodeBinLoader.load(font, streamMock);
        assertEquals(11, font.getOutline());
    }

    @Test
    public void testFace() throws Exception {
        AngelCodeBinLoader.load(font, streamMock);
        assertEquals("ABC", font.getFace());
    }

    @Test
    public void testLineHeight() throws Exception {
        AngelCodeBinLoader.load(font, streamMock);
        assertEquals(12, font.getLineHeight());
    }

    @Test
    public void testBase() throws Exception {
        AngelCodeBinLoader.load(font, streamMock);
        assertEquals(13, font.getBase());
    }

    @Test
    public void testScaleW() throws Exception {
        AngelCodeBinLoader.load(font, streamMock);
        assertEquals(14, font.getScaleW());
    }

    @Test
    public void testScaleH() throws Exception {
        AngelCodeBinLoader.load(font, streamMock);
        assertEquals(15, font.getScaleH());
    }

    @Test
    public void testPacked() throws Exception {
        AngelCodeBinLoader.load(font, streamMock);
        assertTrue(font.isPacked());
    }

    @Test
    public void testAlphaChannel() throws Exception {
        AngelCodeBinLoader.load(font, streamMock);
        assertEquals(TextureChannelContent.GLYPH_DATA, font.getAlphaChannel());
    }

    @Test
    public void testPage() throws Exception {
        AngelCodeBinLoader.load(font, streamMock);

        assertEquals("PG0", font.getPage(0));
        assertEquals("PG1", font.getPage(1));
    }

    @Test
    public void testChar() throws Exception {
        AngelCodeBinLoader.load(font, streamMock);

        assertEquals(16, font.getChar(16).getId());
        assertEquals(17, font.getChar(16).getX());
        assertEquals(18, font.getChar(16).getY());
        assertEquals(19, font.getChar(16).getWidth());
        assertEquals(20, font.getChar(16).getHeight());
        assertEquals(21, font.getChar(16).getXOffset());
        assertEquals(22, font.getChar(16).getYOffset());
        assertEquals(23, font.getChar(16).getXAdvance());
        assertEquals(0, font.getChar(16).getPage());
        assertEquals(GlyphChannel.ALL, font.getChar(16).getChannel());
    }

    @Test
    public void testKerning() throws Exception {
        AngelCodeBinLoader.load(font, streamMock);

        assertEquals(26, font.getKerning(24, 25));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowWhenNotBMF() throws Exception {
        byte[] buffer = {
            66,
            77,
            71
        };
        InputStream stream = new ByteArrayInputStream(buffer);
        AngelCodeBinLoader.load(font, stream);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowWhenNotVersion3() throws Exception {
        byte[] buffer = {
            66,
            77,
            71,
            2
        };
        InputStream stream = new ByteArrayInputStream(buffer);
        AngelCodeBinLoader.load(font, stream);
    }
}
