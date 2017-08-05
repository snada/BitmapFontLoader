package it.snada.bitmap_3d_string;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import static org.junit.Assert.assertEquals;

@RunWith(PowerMockRunner.class)
public class Bitmap3DColorTest {
    FloatBuffer colorBuffer;

    @Test
    public void testGetColorBuffer() throws Exception {
        float[] colors = {
            0.0f, 0.1f, 0.2f, 0.3f,
            0.4f, 0.5f, 0.6f, 0.7f,
            0.8f, 0.9f, 0.91f, 0.92f,
            0.93f, 0.94f, 0.95f, 0.96f
        };

        ByteBuffer clb = ByteBuffer.allocateDirect(colors.length * 4);
        clb.order(ByteOrder.nativeOrder());
        colorBuffer = clb.asFloatBuffer();
        colorBuffer.put(colors);
        colorBuffer.position(0);

        Bitmap3DColor o = new Bitmap3DColor(0.0f, 0.1f, 0.2f, 0.3f, 0.4f, 0.5f, 0.6f, 0.7f, 0.8f, 0.9f, 0.91f, 0.92f, 0.93f, 0.94f, 0.95f, 0.96f);

        assertEquals(colorBuffer, o.getColorBuffer());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowWhenRedIsLessThanZero() throws Exception {
        new Bitmap3DColor(-0.9f, 1.0f, 1.0f, 1.0f);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowWhenRedIsMoreThanOne() throws Exception {
        new Bitmap3DColor(1.1f, 1.0f, 1.0f, 1.0f);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowWhenGreenIsLessThanZero() throws Exception {
        new Bitmap3DColor(0.0f, -0.9f, 1.0f, 1.0f);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowWhenGreenIsMoreThanOne() throws Exception {
        new Bitmap3DColor(0.0f, 1.1f, 1.0f, 1.0f);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowWhenBlueIsLessThanZero() throws Exception {
        new Bitmap3DColor(0.0f, 0.0f, -0.9f, 1.0f);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowWhenBlueIsMoreThanOne() throws Exception {
        new Bitmap3DColor(0.0f, 1.0f, 1.1f, 1.0f);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowWhenAlphaIsLessThanZero() throws Exception {
        new Bitmap3DColor(0.0f, 0.0f, 0.0f, -0.9f);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowWhenAlphaIsMoreThanOne() throws Exception {
        new Bitmap3DColor(0.0f, 1.0f, 1.0f, 1.1f);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowWhenV1RedIsLessThanZero() throws Exception {
        new Bitmap3DColor(
            -1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowWhenV1RedIsMoreThanOne() throws Exception {
        new Bitmap3DColor(
            1.1f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowWhenV2RedIsLessThanZero() throws Exception {
        new Bitmap3DColor(
            1.0f, 1.0f, 1.0f, 1.0f,
            -1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowWhenV2RedIsMoreThanOne() throws Exception {
        new Bitmap3DColor(
            1.0f, 1.0f, 1.0f, 1.0f,
            1.1f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowWhenV3RedIsLessThanZero() throws Exception {
        new Bitmap3DColor(
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            -1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowWhenV3RedIsMoreThanOne() throws Exception {
        new Bitmap3DColor(
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.1f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowWhenV4RedIsLessThanZero() throws Exception {
        new Bitmap3DColor(
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            -1.0f, 1.0f, 1.0f, 1.0f
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowWhenV4RedIsMoreThanOne() throws Exception {
        new Bitmap3DColor(
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.1f, 1.0f, 1.0f, 1.0f
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowWhenV1GreenIsLessThanZero() throws Exception {
        new Bitmap3DColor(
            1.0f, -1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowWhenV1GreenIsMoreThanOne() throws Exception {
        new Bitmap3DColor(
            1.0f, 1.1f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowWhenV2GreenIsLessThanZero() throws Exception {
        new Bitmap3DColor(
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, -1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowWhenV2GreenIsMoreThanOne() throws Exception {
        new Bitmap3DColor(
            1.1f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.1f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowWhenV3GreenIsLessThanZero() throws Exception {
        new Bitmap3DColor(
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, -1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowWhenV3GreenIsMoreThanOne() throws Exception {
        new Bitmap3DColor(
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.1f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowWhenV4GreenIsLessThanZero() throws Exception {
        new Bitmap3DColor(
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, -1.0f, 1.0f, 1.0f
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowWhenV4GreenIsMoreThanOne() throws Exception {
        new Bitmap3DColor(
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.1f, 1.0f, 1.0f
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowWhenV1BlueIsLessThanZero() throws Exception {
        new Bitmap3DColor(
            1.0f, 1.0f, -1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowWhenV1BlueIsMoreThanOne() throws Exception {
        new Bitmap3DColor(
            1.0f, 1.0f, 1.1f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowWhenV2BlueIsLessThanZero() throws Exception {
        new Bitmap3DColor(
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, -1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowWhenV2BlueIsMoreThanOne() throws Exception {
        new Bitmap3DColor(
            1.1f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.1f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowWhenV3BlueIsLessThanZero() throws Exception {
        new Bitmap3DColor(
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, -1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowWhenV3BlueIsMoreThanOne() throws Exception {
        new Bitmap3DColor(
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.1f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowWhenV4BlueIsLessThanZero() throws Exception {
        new Bitmap3DColor(
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, -1.0f, 1.0f
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowWhenV4BlueIsMoreThanOne() throws Exception {
        new Bitmap3DColor(
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.1f, 1.1f, 1.0f
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowWhenV1AlphaIsLessThanZero() throws Exception {
        new Bitmap3DColor(
            1.0f, 1.0f, 1.0f, -1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowWhenV1AlphaIsMoreThanOne() throws Exception {
        new Bitmap3DColor(
            1.0f, 1.0f, 1.0f, 1.1f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowWhenV2AlphaIsLessThanZero() throws Exception {
        new Bitmap3DColor(
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, -1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowWhenV2AlphaIsMoreThanOne() throws Exception {
        new Bitmap3DColor(
            1.1f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.1f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowWhenV3AlphaIsLessThanZero() throws Exception {
        new Bitmap3DColor(
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, -1.0f,
            1.0f, 1.0f, 1.0f, 1.0f
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowWhenV3AlphaIsMoreThanOne() throws Exception {
        new Bitmap3DColor(
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.1f,
            1.0f, 1.0f, 1.0f, 1.0f
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowWhenV4AlphaIsLessThanZero() throws Exception {
        new Bitmap3DColor(
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, -1.0f
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowWhenV4AlphaIsMoreThanOne() throws Exception {
        new Bitmap3DColor(
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.1f, 1.0f, 1.1f
        );
    }
}
