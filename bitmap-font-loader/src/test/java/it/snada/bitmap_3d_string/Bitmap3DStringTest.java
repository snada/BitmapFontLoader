package it.snada.bitmap_3d_string;

import android.opengl.Matrix;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;

import java.util.ArrayList;
import java.util.List;

import it.snada.bitmap_font_loader.BitmapChar;
import it.snada.bitmap_font_loader.BitmapFont;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Matrix.class)
public class Bitmap3DStringTest {
    String string = "a";

    BitmapFont font;

    BitmapChar chr;

    @Before
    public void setUp() throws Exception {
        chr = PowerMockito.mock(BitmapChar.class);
        PowerMockito.when(chr, "getId").thenReturn((int)string.charAt(0));
        PowerMockito.when(chr, "getXOffset").thenReturn(0);
        PowerMockito.when(chr, "getYOffset").thenReturn(0);
        PowerMockito.when(chr, "getXAdvance").thenReturn(9);

        font = PowerMockito.mock(BitmapFont.class);
        PowerMockito.when(font, "getChar", string.charAt(0)).thenReturn(chr);
        PowerMockito.when(font, "getLineHeight").thenReturn(10);

        PowerMockito.mockStatic(Matrix.class);

        PowerMockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                float[] matrix = (float[])args[0];
                matrix[0] = 1.0f;
                matrix[1] = 0.0f;
                matrix[2] = 0.0f;
                matrix[3] = 0.0f;
                matrix[4] = 0.0f;
                matrix[5] = 1.0f;
                matrix[6] = 0.0f;
                matrix[7] = 0.0f;
                matrix[8] = 0.0f;
                matrix[9] = 0.0f;
                matrix[10] = 1.0f;
                matrix[11] = 0.0f;
                matrix[12] = 0.0f;
                matrix[13] = 0.0f;
                matrix[14] = 0.0f;
                matrix[15] = 1.0f;
                return null;
            }
        }).when(Matrix.class, "setIdentityM", new float[16], 0);
    }

    @After
    public void tearDown() throws Exception {
        PowerMockito.doCallRealMethod().when(Matrix.class);
    }

    @Test
    public void testGetTranslationMatrix() throws Exception {
        Bitmap3DString o = new Bitmap3DString(font, string);
        o.setPositionX(2.0f);

        PowerMockito.doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                float[] matrix = (float[])invocation.getArguments()[0];

                for(int counter = 0; counter < matrix.length; counter++) {
                    matrix[counter] = 2.0f;
                }
                return null;
            }
        }).when(Matrix.class, "translateM", o.translationMatrix, 0, o.getPositionX(), o.getPositionY(), o.getPositionZ());

        float[] expected = { 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f };
        assertArrayEquals(expected, o.getTranslationMatrix(), 0);
    }

    @Test
    public void testGetRotationMatrix() throws Exception {
        Bitmap3DString o = new Bitmap3DString(font, string);
        o.setRotationX(0.0f);
        o.setRotationY(1.0f);
        o.setRotationZ(2.0f);

        PowerMockito.doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                float[] matrix = (float[])args[0];
                matrix[0] = 0.0f;
                matrix[1] = 0.0f;
                matrix[2] = 0.0f;
                matrix[3] = 0.0f;
                return null;
            }
        }).when(Matrix.class, "rotateM", o.rotationMatrix, 0, o.getRotationX(), 1.0f, 0.0f, 0.0f);

        PowerMockito.doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                float[] matrix = (float[])args[0];
                matrix[4] = 1.0f;
                matrix[5] = 1.0f;
                matrix[6] = 1.0f;
                matrix[7] = 1.0f;
                return null;
            }
        }).when(Matrix.class, "rotateM", o.rotationMatrix, 0, o.getRotationY(), 0.0f, 1.0f, 0.0f);

        PowerMockito.doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                float[] matrix = (float[])args[0];
                matrix[8] = 2.0f;
                matrix[9] = 2.0f;
                matrix[10] = 2.0f;
                matrix[11] = 2.0f;
                matrix[12] = 2.0f;
                matrix[13] = 2.0f;
                matrix[14] = 2.0f;
                matrix[15] = 2.0f;
                return null;
            }
        }).when(Matrix.class, "rotateM", o.rotationMatrix, 0, o.getRotationZ(), 0.0f, 0.0f, 1.0f);

        float[] expected = { 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f };
        assertArrayEquals(expected, o.getRotationMatrix(), 0);
    }

    @Test
    public void testGetScaleMatrix() throws Exception {
        Bitmap3DString o = new Bitmap3DString(font, string);

        PowerMockito.doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                float[] matrix = (float[])args[0];
                for(int counter = 0; counter < matrix.length; counter++) {
                    matrix[counter] = 2.0f;
                }
                return null;
            }
        }).when(Matrix.class, "scaleM", o.scaleMatrix, 0, o.getScaleX(), o.getScaleY(), o.getScaleZ());

        float[] expected = { 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f };
        assertArrayEquals(expected, o.getScaleMatrix(), 0);
    }

    @Test
    public void testGetModelMatrix() throws Exception {
        Bitmap3DString o = new Bitmap3DString(font, string);

        PowerMockito.doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                float[] matrix = (float[])invocation.getArguments()[0];
                for(int counter = 0; counter < matrix.length; counter++) {
                    matrix[counter] = 1.0f;
                }
                return null;
            }
        }).when(Matrix.class, "translateM", o.translationMatrix, 0, o.getPositionX(), o.getPositionY(), o.getPositionZ());

        PowerMockito.doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                float[] matrix = (float[])invocation.getArguments()[0];
                for(int counter = 0; counter < matrix.length; counter++) {
                    matrix[counter] = 2.0f;
                }
                return null;
            }
        }).when(Matrix.class, "rotateM", o.rotationMatrix, 0, o.getRotationX(), 1.0f, 0.0f, 0.0f);

        PowerMockito.doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                float[] matrix = (float[])invocation.getArguments()[0];
                for(int counter = 0; counter < matrix.length; counter++) {
                    matrix[counter] = 3.0f;
                }
                return null;
            }
        }).when(Matrix.class, "rotateM", o.rotationMatrix, 0, o.getRotationY(), 0.0f, 1.0f, 0.0f);

        PowerMockito.doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                float[] matrix = (float[])invocation.getArguments()[0];
                for(int counter = 0; counter < matrix.length; counter++) {
                    matrix[counter] = 4.0f;
                }
                return null;
            }
        }).when(Matrix.class, "rotateM", o.rotationMatrix, 0, o.getRotationZ(), 0.0f, 0.0f, 1.0f);

        PowerMockito.doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                float[] matrix = (float[])invocation.getArguments()[0];
                for(int counter = 0; counter < matrix.length; counter++) {
                    matrix[counter] = 5.0f;
                }
                return null;
            }
        }).when(Matrix.class, "scaleM", o.scaleMatrix, 0, o.getScaleX(), o.getScaleY(), o.getScaleZ());

        float[] id = { 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f };

        float[] tmp = new float[16];
        PowerMockito.doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                float[] matrix = (float[])invocation.getArguments()[0];
                for(int counter = 0; counter < matrix.length; counter++) {
                    matrix[counter] = 6.0f;
                }
                return null;
            }
        }).when(Matrix.class, "multiplyMM", tmp, 0, o.scaleMatrix, 0, id, 0);

        float[] tmp1 = new float[16];
        PowerMockito.doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                float[] matrix = (float[])invocation.getArguments()[0];
                for(int counter = 0; counter < matrix.length; counter++) {
                    matrix[counter] = 7.0f;
                }
                return null;
            }
        }).when(Matrix.class, "multiplyMM", tmp1, 0, o.rotationMatrix, 0, tmp, 0);

        float[] model = new float[16];
        PowerMockito.doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                float[] matrix = (float[])invocation.getArguments()[0];
                for(int counter = 0; counter < matrix.length; counter++) {
                    matrix[counter] = (float)counter;
                }
                return null;
            }
        }).when(Matrix.class, "multiplyMM", model, 0, o.translationMatrix, 0, tmp1, 0);

        float[] expected = { 0.0f, 1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f, 7.0f, 8.0f, 9.0f, 10.0f, 11.0f, 12.0f, 13.0f, 14.0f, 15.0f };
        assertArrayEquals(expected, o.getModelMatrix(), 0);
    }

    @Test
    public void testSetCentered() throws Exception {
        Bitmap3DString o = new Bitmap3DString(font, string);
        o.setCentered(true);

        assertTrue(o.getCentered());
    }

    @Test
    public void testGetCentered() throws Exception {
        Bitmap3DString o = new Bitmap3DString(font, string);
        assertFalse(o.getCentered());
    }

    @Test
    public void testGetBitmapFont() throws Exception {
        Bitmap3DString o = new Bitmap3DString(font, string);
        assertSame(font, o.getBitmapFont());
    }

    @Test
    public void testGetText() throws Exception {
        Bitmap3DString o = new Bitmap3DString(font, string);
        assertSame(string, o.getText());
    }

    @Test
    public void testSetText() throws Exception {
        Bitmap3DString o = new Bitmap3DString(font, string);
        o.setText("aa");
        assertEquals("aa", o.getText());
    }

    @Test
    public void testGet3DChars() throws Exception {
        Bitmap3DString o = new Bitmap3DString(font, string);

        List<Bitmap3DChar> expected = new ArrayList<>(string.length());
        expected.add(new Bitmap3DChar(o, chr, 0));

        assertEquals(expected.get(0).bitmapChar.getId(), o.get3dChars().get(0).bitmapChar.getId());
    }

    @Test
    public void testGetWidth() throws Exception {
        Bitmap3DString o = new Bitmap3DString(font, string);
        assertEquals(9, o.getWidth(), 0);
    }

    @Test
    public void testSetXScaleByPreferredWidth() throws Exception {
        Bitmap3DString o = new Bitmap3DString(font, string);
        o.setXScaleByPreferredWidth(4.5f);
        assertEquals(0.5f, o.getScaleX(), 0);
    }

    @Test
    public void testSetYScaleByPreferredWidth() throws Exception {
        Bitmap3DString o = new Bitmap3DString(font, string);
        o.setYScaleByPreferredHeight(5.0f);
        assertEquals(0.5f, o.getScaleY(), 0);
    }
}
