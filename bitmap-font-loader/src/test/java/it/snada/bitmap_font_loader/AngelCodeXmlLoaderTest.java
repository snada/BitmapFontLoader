package it.snada.bitmap_font_loader;

import android.util.SparseArray;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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

    SparseArray<String> pagesMock;

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

        BitmapFont font = AngelCodeXmlLoader.load(stream);
        assertEquals("face", font.getFace());
    }

    @Test
    public void testSize() throws Exception {
        PowerMockito.when(parser, "getName").thenReturn("info");

        BitmapFont font = AngelCodeXmlLoader.load(stream);
        assertEquals(1, font.getSize());
    }

    @Test
    public void testBold() throws Exception {
        PowerMockito.when(parser, "getName").thenReturn("info");

        BitmapFont font = AngelCodeXmlLoader.load(stream);
        assertEquals(false, font.isBold());
    }

    @Test
    public void testItalic() throws Exception {
        PowerMockito.when(parser, "getName").thenReturn("info");

        BitmapFont font = AngelCodeXmlLoader.load(stream);
        assertEquals(true, font.isItalic());
    }

    @Test
    public void testUnicode() throws Exception {
        PowerMockito.when(parser, "getName").thenReturn("info");

        BitmapFont font = AngelCodeXmlLoader.load(stream);
        assertEquals(false, font.isUnicode());
    }

    @Test
    public void testCharset() throws Exception {
        PowerMockito.when(parser, "getName").thenReturn("info");

        BitmapFont font = AngelCodeXmlLoader.load(stream);
        assertEquals("charset", font.getCharset());
    }

    @Test
    public void testHeightPercentage() throws Exception {
        PowerMockito.when(parser, "getName").thenReturn("info");

        BitmapFont font = AngelCodeXmlLoader.load(stream);
        assertEquals(2, font.getHeightPercentage());
    }

    @Test
    public void testSmooth() throws Exception {
        PowerMockito.when(parser, "getName").thenReturn("info");

        BitmapFont font = AngelCodeXmlLoader.load(stream);
        assertEquals(true, font.isSmooth());
    }

    @Test
    public void testSuperSampling() throws Exception {
        PowerMockito.when(parser, "getName").thenReturn("info");

        BitmapFont font = AngelCodeXmlLoader.load(stream);
        assertEquals(3, font.getSuperSampling());
    }

    @Test
    public void testPaddingUp() throws Exception {
        PowerMockito.when(parser, "getName").thenReturn("info");

        BitmapFont font = AngelCodeXmlLoader.load(stream);
        assertEquals(4, font.getPaddingUp());
    }

    @Test
    public void testPaddingRight() throws Exception {
        PowerMockito.when(parser, "getName").thenReturn("info");

        BitmapFont font = AngelCodeXmlLoader.load(stream);
        assertEquals(5, font.getPaddingRight());
    }

    @Test
    public void testPaddingDown() throws Exception {
        PowerMockito.when(parser, "getName").thenReturn("info");

        BitmapFont font = AngelCodeXmlLoader.load(stream);
        assertEquals(6, font.getPaddingDown());
    }

    @Test
    public void testPaddingLeft() throws Exception {
        PowerMockito.when(parser, "getName").thenReturn("info");

        BitmapFont font = AngelCodeXmlLoader.load(stream);
        assertEquals(7, font.getPaddingLeft());
    }

    @Test
    public void testHorizontalSpacing() throws Exception {
        PowerMockito.when(parser, "getName").thenReturn("info");

        BitmapFont font = AngelCodeXmlLoader.load(stream);
        assertEquals(8, font.getHorizontalSpacing());
    }

    @Test
    public void testVerticalSpacing() throws Exception {
        PowerMockito.when(parser, "getName").thenReturn("info");

        BitmapFont font = AngelCodeXmlLoader.load(stream);
        assertEquals(9, font.getVerticalSpacing());
    }

    @Test
    public void testOutline() throws Exception {
        PowerMockito.when(parser, "getName").thenReturn("info");

        BitmapFont font = AngelCodeXmlLoader.load(stream);
        assertEquals(10, font.getOutline());
    }

    @Test
    public void testLineHeight() throws Exception {
        PowerMockito.when(parser, "getName").thenReturn("common");

        BitmapFont font = AngelCodeXmlLoader.load(stream);
        assertEquals(11, font.getLineHeight());
    }

    @Test
    public void testBase() throws Exception {
        PowerMockito.when(parser, "getName").thenReturn("common");

        BitmapFont font = AngelCodeXmlLoader.load(stream);
        assertEquals(12, font.getBase());
    }

    @Test
    public void testScaleW() throws Exception {
        PowerMockito.when(parser, "getName").thenReturn("common");

        BitmapFont font = AngelCodeXmlLoader.load(stream);
        assertEquals(13, font.getScaleW());
    }

    @Test
    public void testScaleH() throws Exception {
        PowerMockito.when(parser, "getName").thenReturn("common");

        BitmapFont font = AngelCodeXmlLoader.load(stream);
        assertEquals(14, font.getScaleH());
    }

    @Test
    public void testPacked() throws Exception {
        PowerMockito.when(parser, "getName").thenReturn("common");

        BitmapFont font = AngelCodeXmlLoader.load(stream);
        assertEquals(false, font.isPacked());
    }

    @Test
    public void testAlphaChannel() throws Exception {
        PowerMockito.when(parser, "getName").thenReturn("common");

        BitmapFont font = AngelCodeXmlLoader.load(stream);
        assertEquals(TextureChannelContent.GLYPH_DATA, font.getAlphaChannel());
    }

    @Test
    public void testRedChannel() throws Exception {
        PowerMockito.when(parser, "getName").thenReturn("common");

        BitmapFont font = AngelCodeXmlLoader.load(stream);
        assertEquals(TextureChannelContent.OUTLINE, font.getRedChannel());
    }

    @Test
    public void testGreenChannel() throws Exception {
        PowerMockito.when(parser, "getName").thenReturn("common");

        BitmapFont font = AngelCodeXmlLoader.load(stream);
        assertEquals(TextureChannelContent.GLYPH_AND_OUTLINE, font.getGreenChannel());
    }

    @Test
    public void testBlueChannel() throws Exception {
        PowerMockito.when(parser, "getName").thenReturn("common");

        BitmapFont font = AngelCodeXmlLoader.load(stream);
        assertEquals(TextureChannelContent.ZERO, font.getBlueChannel());
    }
}
