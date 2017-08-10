package it.snada.bitmap_font_loader;

import android.util.SparseArray;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;

import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(XmlPullParserFactory.class)
public class AngelCodeXmlLoaderTest {
    InputStream stream;

    XmlPullParser parser;

    XmlPullParserFactory factory;

    @Before
    public void setUp() throws Exception {
        stream = PowerMockito.mock(InputStream.class);

        parser = PowerMockito.mock(XmlPullParser.class);
        PowerMockito.when(parser, "getEventType").thenReturn(XmlPullParser.START_TAG);
        PowerMockito.when(parser, "next").thenReturn(XmlPullParser.END_DOCUMENT);
        PowerMockito.when(parser, "getAttributeValue", null, "face").thenReturn("face");
        PowerMockito.when(parser, "getAttributeValue", null, "size").thenReturn("1");
        PowerMockito.when(parser, "getAttributeValue", null, "bold").thenReturn("0");
        PowerMockito.when(parser, "getAttributeValue", null, "italic").thenReturn("1");
        PowerMockito.when(parser, "getAttributeValue", null, "unicode").thenReturn("0");
        PowerMockito.when(parser, "getAttributeValue", null, "charset").thenReturn("charset");
        PowerMockito.when(parser, "getAttributeValue", null, "stretchH").thenReturn("2");
        PowerMockito.when(parser, "getAttributeValue", null, "smooth").thenReturn("1");
        PowerMockito.when(parser, "getAttributeValue", null, "aa").thenReturn("3");
        PowerMockito.when(parser, "getAttributeValue", null, "padding").thenReturn("4,5,6,7");
        PowerMockito.when(parser, "getAttributeValue", null, "spacing").thenReturn("8,9");
        PowerMockito.when(parser, "getAttributeValue", null, "outline").thenReturn("10");

        PowerMockito.when(parser, "getAttributeValue", null, "lineHeight").thenReturn("11");
        PowerMockito.when(parser, "getAttributeValue", null, "base").thenReturn("12");
        PowerMockito.when(parser, "getAttributeValue", null, "scaleW").thenReturn("13");
        PowerMockito.when(parser, "getAttributeValue", null, "scaleH").thenReturn("14");
        PowerMockito.when(parser, "getAttributeValue", null, "packed").thenReturn("0");
        PowerMockito.when(parser, "getAttributeValue", null, "alphaChnl").thenReturn("0");
        PowerMockito.when(parser, "getAttributeValue", null, "redChnl").thenReturn("1");
        PowerMockito.when(parser, "getAttributeValue", null, "greenChnl").thenReturn("2");
        PowerMockito.when(parser, "getAttributeValue", null, "blueChnl").thenReturn("3");

        factory = PowerMockito.mock(XmlPullParserFactory.class);

        PowerMockito.when(factory, "newPullParser").thenReturn(parser);

        PowerMockito.mockStatic(XmlPullParserFactory.class);
        PowerMockito.doAnswer(new Answer<XmlPullParserFactory>() {
            @Override
            public XmlPullParserFactory answer(InvocationOnMock invocation) throws Throwable {
                return factory;
            }
        }).when(XmlPullParserFactory.class, "newInstance");
    }

    @Test
    public void testFace() throws Exception {
        PowerMockito.when(parser, "getName").thenReturn("info");

        BitmapFont font = new BitmapFont();
        AngelCodeXmlLoader.load(font, stream);
        assertEquals("face", font.getFace());
    }

    @Test
    public void testSize() throws Exception {
        PowerMockito.when(parser, "getName").thenReturn("info");

        BitmapFont font = new BitmapFont();
        AngelCodeXmlLoader.load(font, stream);
        assertEquals(1, font.getSize());
    }

    @Test
    public void testBold() throws Exception {
        PowerMockito.when(parser, "getName").thenReturn("info");

        BitmapFont font = new BitmapFont();
        AngelCodeXmlLoader.load(font, stream);
        assertEquals(false, font.isBold());
    }

    @Test
    public void testItalic() throws Exception {
        PowerMockito.when(parser, "getName").thenReturn("info");

        BitmapFont font = new BitmapFont();
        AngelCodeXmlLoader.load(font, stream);
        assertEquals(true, font.isItalic());
    }

    @Test
    public void testUnicode() throws Exception {
        PowerMockito.when(parser, "getName").thenReturn("info");

        BitmapFont font = new BitmapFont();
        AngelCodeXmlLoader.load(font, stream);
        assertEquals(false, font.isUnicode());
    }

    @Test
    public void testCharset() throws Exception {
        PowerMockito.when(parser, "getName").thenReturn("info");

        BitmapFont font = new BitmapFont();
        AngelCodeXmlLoader.load(font, stream);
        assertEquals("charset", font.getCharset());
    }

    @Test
    public void testHeightPercentage() throws Exception {
        PowerMockito.when(parser, "getName").thenReturn("info");

        BitmapFont font = new BitmapFont();
        AngelCodeXmlLoader.load(font, stream);
        assertEquals(2, font.getHeightPercentage());
    }

    @Test
    public void testSmooth() throws Exception {
        PowerMockito.when(parser, "getName").thenReturn("info");

        BitmapFont font = new BitmapFont();
        AngelCodeXmlLoader.load(font, stream);
        assertEquals(true, font.isSmooth());
    }

    @Test
    public void testSuperSampling() throws Exception {
        PowerMockito.when(parser, "getName").thenReturn("info");

        BitmapFont font = new BitmapFont();
        AngelCodeXmlLoader.load(font, stream);
        assertEquals(3, font.getSuperSampling());
    }

    @Test
    public void testPaddingUp() throws Exception {
        PowerMockito.when(parser, "getName").thenReturn("info");

        BitmapFont font = new BitmapFont();
        AngelCodeXmlLoader.load(font, stream);
        assertEquals(4, font.getPaddingUp());
    }

    @Test
    public void testPaddingRight() throws Exception {
        PowerMockito.when(parser, "getName").thenReturn("info");

        BitmapFont font = new BitmapFont();
        AngelCodeXmlLoader.load(font, stream);
        assertEquals(5, font.getPaddingRight());
    }

    @Test
    public void testPaddingDown() throws Exception {
        PowerMockito.when(parser, "getName").thenReturn("info");

        BitmapFont font = new BitmapFont();
        AngelCodeXmlLoader.load(font, stream);
        assertEquals(6, font.getPaddingDown());
    }

    @Test
    public void testPaddingLeft() throws Exception {
        PowerMockito.when(parser, "getName").thenReturn("info");

        BitmapFont font = new BitmapFont();
        AngelCodeXmlLoader.load(font, stream);
        assertEquals(7, font.getPaddingLeft());
    }

    @Test
    public void testHorizontalSpacing() throws Exception {
        PowerMockito.when(parser, "getName").thenReturn("info");

        BitmapFont font = new BitmapFont();
        AngelCodeXmlLoader.load(font, stream);
        assertEquals(8, font.getHorizontalSpacing());
    }

    @Test
    public void testVerticalSpacing() throws Exception {
        PowerMockito.when(parser, "getName").thenReturn("info");

        BitmapFont font = new BitmapFont();
        AngelCodeXmlLoader.load(font, stream);
        assertEquals(9, font.getVerticalSpacing());
    }

    @Test
    public void testOutline() throws Exception {
        PowerMockito.when(parser, "getName").thenReturn("info");

        BitmapFont font = new BitmapFont();
        AngelCodeXmlLoader.load(font, stream);
        assertEquals(10, font.getOutline());
    }

    @Test
    public void testLineHeight() throws Exception {
        PowerMockito.when(parser, "getName").thenReturn("common");

        BitmapFont font = new BitmapFont();
        AngelCodeXmlLoader.load(font, stream);
        assertEquals(11, font.getLineHeight());
    }

    @Test
    public void testBase() throws Exception {
        PowerMockito.when(parser, "getName").thenReturn("common");

        BitmapFont font = new BitmapFont();
        AngelCodeXmlLoader.load(font, stream);
        assertEquals(12, font.getBase());
    }

    @Test
    public void testScaleW() throws Exception {
        PowerMockito.when(parser, "getName").thenReturn("common");

        BitmapFont font = new BitmapFont();
        AngelCodeXmlLoader.load(font, stream);
        assertEquals(13, font.getScaleW());
    }

    @Test
    public void testScaleH() throws Exception {
        PowerMockito.when(parser, "getName").thenReturn("common");

        BitmapFont font = new BitmapFont();
        AngelCodeXmlLoader.load(font, stream);
        assertEquals(14, font.getScaleH());
    }

    @Test
    public void testPacked() throws Exception {
        PowerMockito.when(parser, "getName").thenReturn("common");

        BitmapFont font = new BitmapFont();
        AngelCodeXmlLoader.load(font, stream);
        assertEquals(false, font.isPacked());
    }

    @Test
    public void testAlphaChannel() throws Exception {
        PowerMockito.when(parser, "getName").thenReturn("common");

        BitmapFont font = new BitmapFont();
        AngelCodeXmlLoader.load(font, stream);
        assertEquals(TextureChannelContent.GLYPH_DATA, font.getAlphaChannel());
    }

    @Test
    public void testRedChannel() throws Exception {
        PowerMockito.when(parser, "getName").thenReturn("common");

        BitmapFont font = new BitmapFont();
        AngelCodeXmlLoader.load(font, stream);
        assertEquals(TextureChannelContent.OUTLINE, font.getRedChannel());
    }

    @Test
    public void testGreenChannel() throws Exception {
        PowerMockito.when(parser, "getName").thenReturn("common");

        BitmapFont font = new BitmapFont();
        AngelCodeXmlLoader.load(font, stream);
        assertEquals(TextureChannelContent.GLYPH_AND_OUTLINE, font.getGreenChannel());
    }

    @Test
    public void testBlueChannel() throws Exception {
        PowerMockito.when(parser, "getName").thenReturn("common");

        BitmapFont font = new BitmapFont();
        AngelCodeXmlLoader.load(font, stream);
        assertEquals(TextureChannelContent.ZERO, font.getBlueChannel());
    }

    @Test
    public void testPage() throws Exception {
        PowerMockito.when(parser, "getName").thenReturn("page");
        BitmapFont font = new BitmapFont();

        SparseArray<String> pagesMock = PowerMockito.mock(SparseArray.class);

        final String[] inserted = new String[1];

        PowerMockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                inserted[0] = (String)invocation.getArguments()[1];
                return null;
            }
        }).when(pagesMock, "put", 0, "page0");

        PowerMockito.doAnswer(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                return inserted[0];
            }
        }).when(pagesMock, "get", 0);

        Whitebox.setInternalState(font, "pages", pagesMock);

        PowerMockito.when(parser, "getAttributeValue", null, "id").thenReturn("0");
        PowerMockito.when(parser, "getAttributeValue", null, "file").thenReturn("page0");

        AngelCodeXmlLoader.load(font, stream);

        assertEquals("page0", font.getPage(0));
    }

    @Test
    public void testKerning() throws Exception {
        int first = 0;
        int second = 1;
        int amount = 100;

        PowerMockito.when(parser, "getName").thenReturn("kerning");
        BitmapFont font = new BitmapFont();

        final SparseArray<Integer> firstKerningsMock = PowerMockito.mock(SparseArray.class);
        SparseArray<Integer> kerningsMock = PowerMockito.mock(SparseArray.class);

        final int[] inserted = new int[1];

        PowerMockito.doAnswer(new Answer<SparseArray<Integer>>() {
            @Override
            public SparseArray<Integer> answer(InvocationOnMock invocation) throws Throwable {
                return firstKerningsMock;
            }
        }).when(kerningsMock, "get", first);

        PowerMockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                inserted[0] = (int)invocation.getArguments()[1];
                return null;
            }
        }).when(firstKerningsMock, "put", second, amount);

        PowerMockito.doAnswer(new Answer<Integer>() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
                return inserted[0];
            }
        }).when(firstKerningsMock, "get", second);

        Whitebox.setInternalState(font, "kernings", kerningsMock);

        PowerMockito.when(parser, "getAttributeValue", null, "first").thenReturn(String.valueOf(first));
        PowerMockito.when(parser, "getAttributeValue", null, "second").thenReturn(String.valueOf(second));
        PowerMockito.when(parser, "getAttributeValue", null, "amount").thenReturn(String.valueOf(amount));

        AngelCodeXmlLoader.load(font, stream);

        assertEquals(amount, font.getKerning(first, second));
    }

    @Test
    public void testChar() throws Exception {
        BitmapFont font = new BitmapFont();

        PowerMockito.when(parser, "getName").thenReturn("char");
        PowerMockito.when(parser, "getAttributeValue", null, "id").thenReturn("1");
        PowerMockito.when(parser, "getAttributeValue", null, "x").thenReturn("2");
        PowerMockito.when(parser, "getAttributeValue", null, "y").thenReturn("3");
        PowerMockito.when(parser, "getAttributeValue", null, "width").thenReturn("4");
        PowerMockito.when(parser, "getAttributeValue", null, "height").thenReturn("5");
        PowerMockito.when(parser, "getAttributeValue", null, "xoffset").thenReturn("6");
        PowerMockito.when(parser, "getAttributeValue", null, "yoffset").thenReturn("7");
        PowerMockito.when(parser, "getAttributeValue", null, "xadvance").thenReturn("8");
        PowerMockito.when(parser, "getAttributeValue", null, "page").thenReturn("9");
        PowerMockito.when(parser, "getAttributeValue", null, "chnl").thenReturn("15");

        SparseArray<BitmapChar> charsMock = PowerMockito.mock(SparseArray.class);

        final BitmapChar[] returned = new BitmapChar[1];

        BitmapChar expected = new BitmapChar(1, 2, 3, 4, 5, 6, 7, 8, 9, GlyphChannel.ALL);

        PowerMockito.doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                returned[0] = (BitmapChar)invocation.getArguments()[1];
                return null;
            }
        }).when(charsMock, "put", Matchers.eq(1), Matchers.any(BitmapChar.class));

        PowerMockito.doAnswer(new Answer<BitmapChar>() {
            @Override
            public BitmapChar answer(InvocationOnMock invocation) throws Throwable {
                return returned[0];
            }
        }).when(charsMock, "get", 1);

        Whitebox.setInternalState(font, "chars", charsMock);

        AngelCodeXmlLoader.load(font, stream);

        assertEquals(expected.getId(), font.getChar(1).getId());
        assertEquals(expected.getX(), font.getChar(1).getX());
        assertEquals(expected.getY(), font.getChar(1).getY());
        assertEquals(expected.getWidth(), font.getChar(1).getWidth());
        assertEquals(expected.getHeight(), font.getChar(1).getHeight());
        assertEquals(expected.getXOffset(), font.getChar(1).getXOffset());
        assertEquals(expected.getYOffset(), font.getChar(1).getYOffset());
        assertEquals(expected.getXAdvance(), font.getChar(1).getXAdvance());
        assertEquals(expected.getPage(), font.getChar(1).getPage());
        assertEquals(expected.getChannel(), font.getChar(1).getChannel());
    }
}
