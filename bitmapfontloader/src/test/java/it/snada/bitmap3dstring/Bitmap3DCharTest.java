package it.snada.bitmap3dstring;

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

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import it.snada.bitmapfontloader.BitmapChar;
import it.snada.bitmapfontloader.BitmapFont;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Matrix.class)
public class Bitmap3DCharTest {
    String string = "a";

    Bitmap3DString string3D;

    BitmapFont font;

    BitmapChar chr;

    @Before
    public void setUp() throws Exception {
        chr = PowerMockito.mock(BitmapChar.class);
        PowerMockito.when(chr, "getId").thenReturn((int)string.charAt(0));
        PowerMockito.when(chr, "getXOffset").thenReturn(-1);
        PowerMockito.when(chr, "getYOffset").thenReturn(-2);
        PowerMockito.when(chr, "getX").thenReturn(1);
        PowerMockito.when(chr, "getY").thenReturn(2);
        PowerMockito.when(chr, "getHeight").thenReturn(5);
        PowerMockito.when(chr, "getWidth").thenReturn(6);

        font = PowerMockito.mock(BitmapFont.class);
        PowerMockito.when(font, "getChar", string.charAt(0)).thenReturn(chr);
        PowerMockito.when(font, "getScaleW").thenReturn(3);
        PowerMockito.when(font, "getScaleH").thenReturn(4);


        float[] string3DModelMatrix = { -1.0f, 1.0f };
        string3D = PowerMockito.mock(Bitmap3DString.class);
        PowerMockito.when(string3D, "getBitmapFont").thenReturn(font);
        PowerMockito.when(string3D, "getPositionX").thenReturn(10.0f);
        PowerMockito.when(string3D, "getPositionY").thenReturn(11.0f);
        PowerMockito.when(string3D, "getPositionZ").thenReturn(12.0f);
        PowerMockito.when(string3D, "getModelMatrix").thenReturn(string3DModelMatrix);

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
    public void testXPositionOnCreation() throws Exception {
        Bitmap3DChar o = new Bitmap3DChar(string3D, chr, 2);

        assertEquals(2 - 1, o.getPositionX(), 0);
    }

    @Test
    public void testYPositionOnCreation() throws Exception {
        Bitmap3DChar o = new Bitmap3DChar(string3D, chr, 3);

        assertEquals(2, o.getPositionX(), 0);
    }

    @Test
    public void testGetUvBuffer() throws Exception {
        Bitmap3DChar o = new Bitmap3DChar(string3D, chr, 3);

        FloatBuffer expected;

        float[] uvs = {
            o.getTopLeftU(), o.getTopLeftV(),
            o.getBottomLeftU(), o.getBottomLeftV(),
            o.getBottomRightU(), o.getBottomRightV(),
            o.getTopRightU(), o.getTopRightV()
        };
        ByteBuffer ulb = ByteBuffer.allocateDirect(uvs.length * 4);
        ulb.order(ByteOrder.nativeOrder());
        expected = ulb.asFloatBuffer();
        expected.put(uvs);
        expected.position(0);

        assertEquals(expected, o.getUvBuffer());
    }

    @Test
    public void testGetTranslationMatrix() throws Exception {
        Bitmap3DChar o = new Bitmap3DChar(string3D, chr, 0);

        PowerMockito.doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                float[] matrix = (float[])invocation.getArguments()[0];

                for(int counter = 0; counter < matrix.length; counter++) {
                    matrix[counter] = 2.0f;
                }
                return null;
            }
        }).when(
            Matrix.class, "translateM", o.translationMatrix, 0,
            10.0f + o.getPositionX(),
            11.0f + o.getPositionY(),
            12.0f + o.getPositionZ()
        );

        float[] expected = { 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f };
        assertArrayEquals(expected, o.getTranslationMatrix(), 0);
    }

    @Test
    public void testGetRotationMatrix() throws Exception {
        Bitmap3DChar o = new Bitmap3DChar(string3D, chr, 0);
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
        Bitmap3DChar o = new Bitmap3DChar(string3D, chr, 0);

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
        }).when(
            Matrix.class, "scaleM", o.scaleMatrix, 0,
            (6 * o.getScaleX()),
            (5 * o.getScaleY()),
            o.getScaleZ()
        );

        float[] expected = { 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f };
        assertArrayEquals(expected, o.getScaleMatrix(), 0);
    }

    @Test
    public void testGetModelMatrix() throws Exception {
        Bitmap3DChar o = new Bitmap3DChar(string3D, chr, 0);

        PowerMockito.doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                float[] matrix = (float[])invocation.getArguments()[0];
                for(int counter = 0; counter < matrix.length; counter++) {
                    matrix[counter] = 1.0f;
                }
                return null;
            }
        }).when(
            Matrix.class, "translateM", o.translationMatrix, 0,
            10.0f + o.getPositionX(),
            11.0f + o.getPositionY(),
            12.0f + o.getPositionZ()
        );

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
        }).when(
            Matrix.class, "scaleM", o.scaleMatrix, 0,
            (6 * o.getScaleX()),
            (5 * o.getScaleY()),
            o.getScaleZ()
        );

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
        }).when(Matrix.class, "multiplyMM", tmp, 0, o.rotationMatrix, 0, o.scaleMatrix, 0);

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
        }).when(Matrix.class, "multiplyMM", tmp1, 0, o.translationMatrix, 0, tmp, 0);

        float[] model = new float[16];
        float[] string3DModelMatrix = {-1.0f, 1.0f};
        PowerMockito.doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                float[] matrix = (float[])invocation.getArguments()[0];
                for(int counter = 0; counter < matrix.length; counter++) {
                    matrix[counter] = (float)counter;
                }
                return null;
            }
        }).when(Matrix.class, "multiplyMM", model, 0, string3DModelMatrix, 0, tmp1, 0);

        float[] expected = { 0.0f, 1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f, 7.0f, 8.0f, 9.0f, 10.0f, 11.0f, 12.0f, 13.0f, 14.0f, 15.0f };
        assertArrayEquals(expected, o.getModelMatrix(), 0);
    }

    @Test
    public void testGetTopLeftU() throws Exception {
        Bitmap3DChar o = new Bitmap3DChar(string3D, chr, 0);
        assertEquals(0.33333334f, o.getTopLeftU(), 0);
    }

    @Test
    public void testGetTopLeftV() throws Exception {
        Bitmap3DChar o = new Bitmap3DChar(string3D, chr, 0);
        assertEquals(0.5f, o.getTopLeftV(), 0);
    }

    @Test
    public void testGetBottomLeftU() throws Exception {
        Bitmap3DChar o = new Bitmap3DChar(string3D, chr, 0);
        assertEquals(0.33333334f, o.getBottomLeftU(), 0);
    }

    @Test
    public void testGetBottomLeftV() throws Exception {
        Bitmap3DChar o = new Bitmap3DChar(string3D, chr, 0);
        assertEquals(1.75, o.getBottomLeftV(), 0);
    }

    @Test
    public void testGetTopRightU() throws Exception {
        Bitmap3DChar o = new Bitmap3DChar(string3D, chr, 0);
        assertEquals(2.3333333f, o.getTopRightU(), 0);
    }

    @Test
    public void testGetTopRightV() throws Exception {
        Bitmap3DChar o = new Bitmap3DChar(string3D, chr, 0);
        assertEquals(0.5f, o.getTopRightV(), 0);
    }

    @Test
    public void testGetBottomRightU() throws Exception {
        Bitmap3DChar o = new Bitmap3DChar(string3D, chr, 0);
        assertEquals(2.3333333f, o.getBottomRightU(), 0);
    }

    @Test
    public void testGetBottomRightV() throws Exception {
        Bitmap3DChar o = new Bitmap3DChar(string3D, chr, 0);
        assertEquals(1.75f, o.getBottomRightV(), 0);
    }

    @Test
    public void testGetBitmapChar() throws Exception {
        Bitmap3DChar o = new Bitmap3DChar(string3D, chr, 0);
        assertEquals(chr, o.getBitmapChar());
    }
}