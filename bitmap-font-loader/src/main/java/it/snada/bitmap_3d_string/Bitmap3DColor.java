package it.snada.bitmap_3d_string;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class Bitmap3DColor {
    protected FloatBuffer colorBuffer;

    public Bitmap3DColor(float red, float green, float blue, float alpha) {
        this(red, green, blue, alpha, red, green, blue, alpha, red, green, blue, alpha, red, green, blue, alpha);
    }

    public Bitmap3DColor(
        float v1Red, float v1Green, float v1Blue, float v1Alpha,
        float v2Red, float v2Green, float v2Blue, float v2Alpha,
        float v3Red, float v3Green, float v3Blue, float v3Alpha,
        float v4Red, float v4Green, float v4Blue, float v4Alpha
    ) {
        if(
            v1Red < 0 || v1Red > 1 || v2Red < 0 || v2Red > 1 || v3Red < 0 || v3Red > 1 || v4Red < 0 || v4Red > 1 ||
            v1Green < 0 || v1Green > 1 || v2Green < 0 || v2Green > 1 || v3Green < 0 || v3Green > 1 || v4Green < 0 || v4Green > 1 ||
            v1Blue < 0 || v1Blue > 1 || v2Blue < 0 || v2Blue > 1 || v3Blue < 0 || v3Blue > 1 || v4Blue < 0 || v4Blue > 1 ||
            v1Alpha < 0 || v1Alpha > 1 || v2Alpha < 0 || v2Alpha> 1 || v3Alpha < 0 || v3Alpha > 1 || v4Alpha < 0 || v4Alpha > 1
        ) {
            throw new IllegalArgumentException("Values should be between 0 and 1");
        }

        float[] colors = {
            v1Red, v1Blue, v1Green, v1Alpha,
            v2Red, v2Blue, v2Green, v2Alpha,
            v3Red, v3Blue, v3Green, v3Alpha,
            v4Red, v4Blue, v4Green, v4Alpha
        };

        ByteBuffer clb = ByteBuffer.allocateDirect(colors.length * 4);
        clb.order(ByteOrder.nativeOrder());
        this.colorBuffer = clb.asFloatBuffer();
        this.colorBuffer.put(colors);
        this.colorBuffer.position(0);
    }

    public FloatBuffer getColorBuffer() {
        return this.colorBuffer;
    }
}
