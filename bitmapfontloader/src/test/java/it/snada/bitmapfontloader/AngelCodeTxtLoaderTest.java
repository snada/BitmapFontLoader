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
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.junit.Assert.assertEquals;

@RunWith(PowerMockRunner.class)
@PrepareForTest({InputStreamReader.class, BufferedReader.class})
public class AngelCodeTxtLoaderTest {
    InputStream streamMock;

    BufferedReader bufferedReaderMock;

    InputStreamReader inputStreamReaderMock;

    @Before
    public void setUp() throws Exception {
        bufferedReaderMock = PowerMockito.mock(BufferedReader.class);
        inputStreamReaderMock = PowerMockito.mock(InputStreamReader.class);

        PowerMockito.whenNew(InputStreamReader.class).withArguments(streamMock).thenReturn(inputStreamReaderMock);
        PowerMockito.whenNew(BufferedReader.class).withAnyArguments().thenReturn(bufferedReaderMock);
    }

    @Test
    public void testFace() throws Exception {
        streamMock = new ByteArrayInputStream("info face=\"Arial\" size=64 bold=0 italic=0 charset=\"\" unicode=1 stretchH=100 smooth=1 aa=1 padding=0,0,0,0 spacing=1,1 outline=0".getBytes());

        BitmapFont font = new BitmapFont();
        AngelCodeTxtLoader.load(font, streamMock);
        assertEquals("Arial", font.getFace());
    }

    @Test
    public void testSize() throws Exception {
        streamMock = new ByteArrayInputStream("info face=\"Arial\" size=64 bold=0 italic=0 charset=\"\" unicode=1 stretchH=100 smooth=1 aa=1 padding=0,0,0,0 spacing=1,1 outline=0".getBytes());

        BitmapFont font = new BitmapFont();
        AngelCodeTxtLoader.load(font, streamMock);
        assertEquals(64, font.getSize());
    }

    @Test
    public void testBold() throws Exception {
        streamMock = new ByteArrayInputStream("info face=\"Arial\" size=64 bold=0 italic=0 charset=\"\" unicode=1 stretchH=100 smooth=1 aa=1 padding=0,0,0,0 spacing=1,1 outline=0".getBytes());

        BitmapFont font = new BitmapFont();
        AngelCodeTxtLoader.load(font, streamMock);
        assertEquals(false, font.isBold());
    }

    @Test
    public void testItalic() throws Exception {
        streamMock = new ByteArrayInputStream("info face=\"Arial\" size=64 bold=0 italic=1 charset=\"charset\" unicode=1 stretchH=100 smooth=1 aa=1 padding=0,0,0,0 spacing=1,1 outline=0".getBytes());

        BitmapFont font = new BitmapFont();
        AngelCodeTxtLoader.load(font, streamMock);
        assertEquals(true, font.isItalic());
    }

    @Test
    public void testCharset() throws Exception {
        streamMock = new ByteArrayInputStream("info face=\"Arial\" size=64 bold=0 italic=1 charset=\"charset\" unicode=1 stretchH=100 smooth=1 aa=1 padding=0,0,0,0 spacing=1,1 outline=0".getBytes());

        BitmapFont font = new BitmapFont();
        AngelCodeTxtLoader.load(font, streamMock);
        assertEquals("charset", font.getCharset());
    }

    @Test
    public void testUnicode() throws Exception {
        streamMock = new ByteArrayInputStream("info face=\"Arial\" size=64 bold=0 italic=1 charset=\"charset\" unicode=1 stretchH=100 smooth=1 aa=1 padding=0,0,0,0 spacing=1,1 outline=0".getBytes());

        BitmapFont font = new BitmapFont();
        AngelCodeTxtLoader.load(font, streamMock);
        assertEquals(true, font.isUnicode());
    }

    @Test
    public void testHeightPercentage() throws Exception {
        streamMock = new ByteArrayInputStream("info face=\"Arial\" size=64 bold=0 italic=1 charset=\"charset\" unicode=1 stretchH=100 smooth=1 aa=1 padding=0,0,0,0 spacing=1,1 outline=0".getBytes());

        BitmapFont font = new BitmapFont();
        AngelCodeTxtLoader.load(font, streamMock);
        assertEquals(100, font.getHeightPercentage());
    }

    @Test
    public void testSmooth() throws Exception {
        streamMock = new ByteArrayInputStream("info face=\"Arial\" size=64 bold=0 italic=1 charset=\"charset\" unicode=1 stretchH=100 smooth=1 aa=1 padding=0,0,0,0 spacing=1,1 outline=0".getBytes());

        BitmapFont font = new BitmapFont();
        AngelCodeTxtLoader.load(font, streamMock);
        assertEquals(true, font.isSmooth());
    }

    @Test
    public void testSuperSampling() throws Exception {
        streamMock = new ByteArrayInputStream("info face=\"Arial\" size=64 bold=0 italic=1 charset=\"charset\" unicode=1 stretchH=100 smooth=1 aa=1 padding=0,0,0,0 spacing=1,1 outline=0".getBytes());

        BitmapFont font = new BitmapFont();
        AngelCodeTxtLoader.load(font, streamMock);
        assertEquals(1, font.getSuperSampling());
    }

    @Test
    public void testPaddingUp() throws Exception {
        streamMock = new ByteArrayInputStream("info face=\"Arial\" size=64 bold=0 italic=1 charset=\"charset\" unicode=1 stretchH=100 smooth=1 aa=1 padding=1,2,3,4 spacing=1,1 outline=0".getBytes());

        BitmapFont font = new BitmapFont();
        AngelCodeTxtLoader.load(font, streamMock);
        assertEquals(1, font.getPaddingUp());
    }

    @Test
    public void testPaddingRight() throws Exception {
        streamMock = new ByteArrayInputStream("info face=\"Arial\" size=64 bold=0 italic=1 charset=\"charset\" unicode=1 stretchH=100 smooth=1 aa=1 padding=1,2,3,4 spacing=1,1 outline=0".getBytes());

        BitmapFont font = new BitmapFont();
        AngelCodeTxtLoader.load(font, streamMock);
        assertEquals(2, font.getPaddingRight());
    }

    @Test
    public void testPaddingDown() throws Exception {
        streamMock = new ByteArrayInputStream("info face=\"Arial\" size=64 bold=0 italic=1 charset=\"charset\" unicode=1 stretchH=100 smooth=1 aa=1 padding=1,2,3,4 spacing=1,1 outline=0".getBytes());

        BitmapFont font = new BitmapFont();
        AngelCodeTxtLoader.load(font, streamMock);
        assertEquals(3, font.getPaddingDown());
    }

    @Test
    public void testPaddingLeft() throws Exception {
        streamMock = new ByteArrayInputStream("info face=\"Arial\" size=64 bold=0 italic=1 charset=\"charset\" unicode=1 stretchH=100 smooth=1 aa=1 padding=1,2,3,4 spacing=1,1 outline=0".getBytes());

        BitmapFont font = new BitmapFont();
        AngelCodeTxtLoader.load(font, streamMock);
        assertEquals(4, font.getPaddingLeft());
    }

    @Test
    public void testHorizontalSpacing() throws Exception {
        streamMock = new ByteArrayInputStream("info face=\"Arial\" size=64 bold=0 italic=1 charset=\"charset\" unicode=1 stretchH=100 smooth=1 aa=1 padding=1,2,3,4 spacing=5,6 outline=0".getBytes());

        BitmapFont font = new BitmapFont();
        AngelCodeTxtLoader.load(font, streamMock);
        assertEquals(5, font.getHorizontalSpacing());
    }

    @Test
    public void testVerticalSpacing() throws Exception {
        streamMock = new ByteArrayInputStream("info face=\"Arial\" size=64 bold=0 italic=1 charset=\"charset\" unicode=1 stretchH=100 smooth=1 aa=1 padding=1,2,3,4 spacing=5,6 outline=0".getBytes());

        BitmapFont font = new BitmapFont();
        AngelCodeTxtLoader.load(font, streamMock);
        assertEquals(6, font.getVerticalSpacing());
    }

    @Test
    public void testOutline() throws Exception {
        streamMock = new ByteArrayInputStream("info face=\"Arial\" size=64 bold=0 italic=1 charset=\"charset\" unicode=1 stretchH=100 smooth=1 aa=1 padding=1,2,3,4 spacing=5,6 outline=7".getBytes());

        BitmapFont font = new BitmapFont();
        AngelCodeTxtLoader.load(font, streamMock);
        assertEquals(7, font.getOutline());
    }

    @Test
    public void testLineHeight() throws Exception {
        streamMock = new ByteArrayInputStream("common lineHeight=63 base=51 scaleW=512 scaleH=512 pages=1 packed=0 alphaChnl=1 redChnl=0 greenChnl=0 blueChnl=0".getBytes());

        BitmapFont font = new BitmapFont();
        AngelCodeTxtLoader.load(font, streamMock);
        assertEquals(63, font.getLineHeight());
    }

    @Test
    public void testBase() throws Exception {
        streamMock = new ByteArrayInputStream("common lineHeight=63 base=51 scaleW=512 scaleH=512 pages=1 packed=0 alphaChnl=1 redChnl=0 greenChnl=0 blueChnl=0".getBytes());

        BitmapFont font = new BitmapFont();
        AngelCodeTxtLoader.load(font, streamMock);
        assertEquals(51, font.getBase());
    }

    @Test
    public void testScaleW() throws Exception {
        streamMock = new ByteArrayInputStream("common lineHeight=63 base=51 scaleW=512 scaleH=512 pages=1 packed=0 alphaChnl=1 redChnl=0 greenChnl=0 blueChnl=0".getBytes());

        BitmapFont font = new BitmapFont();
        AngelCodeTxtLoader.load(font, streamMock);
        assertEquals(512, font.getScaleW());
    }

    @Test
    public void testScaleH() throws Exception {
        streamMock = new ByteArrayInputStream("common lineHeight=63 base=51 scaleW=512 scaleH=513 pages=1 packed=0 alphaChnl=1 redChnl=0 greenChnl=0 blueChnl=0".getBytes());

        BitmapFont font = new BitmapFont();
        AngelCodeTxtLoader.load(font, streamMock);
        assertEquals(513, font.getScaleH());
    }

    @Test
    public void testPacked() throws Exception {
        streamMock = new ByteArrayInputStream("common lineHeight=63 base=51 scaleW=512 scaleH=513 pages=1 packed=0 alphaChnl=1 redChnl=0 greenChnl=0 blueChnl=0".getBytes());

        BitmapFont font = new BitmapFont();
        AngelCodeTxtLoader.load(font, streamMock);
        assertEquals(false, font.isPacked());
    }

    @Test
    public void testAlphaChannel() throws Exception {
        streamMock = new ByteArrayInputStream("common lineHeight=63 base=51 scaleW=512 scaleH=513 pages=1 packed=0 alphaChnl=0 redChnl=0 greenChnl=0 blueChnl=0".getBytes());

        BitmapFont font = new BitmapFont();
        AngelCodeTxtLoader.load(font, streamMock);
        assertEquals(TextureChannelContent.GLYPH_DATA, font.getAlphaChannel());
    }

    @Test
    public void testRedChannel() throws Exception {
        streamMock = new ByteArrayInputStream("common lineHeight=63 base=51 scaleW=512 scaleH=513 pages=1 packed=0 alphaChnl=0 redChnl=1 greenChnl=0 blueChnl=0".getBytes());

        BitmapFont font = new BitmapFont();
        AngelCodeTxtLoader.load(font, streamMock);
        assertEquals(TextureChannelContent.OUTLINE, font.getRedChannel());
    }

    @Test
    public void testGreenChannel() throws Exception {
        streamMock = new ByteArrayInputStream("common lineHeight=63 base=51 scaleW=512 scaleH=513 pages=1 packed=0 alphaChnl=0 redChnl=0 greenChnl=2 blueChnl=0".getBytes());

        BitmapFont font = new BitmapFont();
        AngelCodeTxtLoader.load(font, streamMock);
        assertEquals(TextureChannelContent.GLYPH_AND_OUTLINE, font.getGreenChannel());
    }

    @Test
    public void testBlueChannel() throws Exception {
        streamMock = new ByteArrayInputStream("common lineHeight=63 base=51 scaleW=512 scaleH=513 pages=1 packed=0 alphaChnl=0 redChnl=0 greenChnl=0 blueChnl=3".getBytes());

        BitmapFont font = new BitmapFont();
        AngelCodeTxtLoader.load(font, streamMock);
        assertEquals(TextureChannelContent.ZERO, font.getBlueChannel());
    }

    @Test
    public void testPage() throws Exception {
        streamMock = new ByteArrayInputStream("page id=0 file=\"page0\"".getBytes());

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

        AngelCodeTxtLoader.load(font, streamMock);

        assertEquals("page0", font.getPage(0));
    }

    @Test
    public void testKerning() throws Exception {
        streamMock = new ByteArrayInputStream("kerning first=0  second=1  amount=100  ".getBytes());
        int first = 0;
        int second = 1;
        int amount = 100;

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

        AngelCodeTxtLoader.load(font, streamMock);

        assertEquals(amount, font.getKerning(first, second));
    }

    @Test
    public void testChar() throws Exception {
        streamMock = new ByteArrayInputStream("char id=1   x=2   y=3     width=4     height=5     xoffset=6    yoffset=7    xadvance=8    page=9  chnl=15".getBytes());

        BitmapFont font = new BitmapFont();

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

        AngelCodeTxtLoader.load(font, streamMock);

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
