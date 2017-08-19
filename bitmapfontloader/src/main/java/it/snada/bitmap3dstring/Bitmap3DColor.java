package it.snada.bitmap3dstring;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Small class to generate Buffers containing vertex color data for a quad used to render font glyphs
 * More than one can be generated if different colors can be generated for different strings or even glphs
 */
public class Bitmap3DColor {
    protected FloatBuffer colorBuffer;

    /**
     * Generates an object containing a float buffer with color data for 4 vertices, all the same color
     * @param red float value between 0 and 1
     * @param green float value between 0 and 1
     * @param blue float value between 0 and 1
     * @param alpha float value between 0 and 1
     */
    public Bitmap3DColor(float red, float green, float blue, float alpha) {
        this(red, green, blue, alpha, red, green, blue, alpha, red, green, blue, alpha, red, green, blue, alpha);
    }

    /**
     * Generates an object containing a float buffer with color data for 4 vertices, specifying different colors for each vertex
     * @param v1Red float value between 0 and 1
     * @param v1Green float value between 0 and 1
     * @param v1Blue float value between 0 and 1
     * @param v1Alpha float value between 0 and 1
     * @param v2Red float value between 0 and 1
     * @param v2Green float value between 0 and 1
     * @param v2Blue float value between 0 and 1
     * @param v2Alpha float value between 0 and 1
     * @param v3Red float value between 0 and 1
     * @param v3Green float value between 0 and 1
     * @param v3Blue float value between 0 and 1
     * @param v3Alpha float value between 0 and 1
     * @param v4Red float value between 0 and 1
     * @param v4Green float value between 0 and 1
     * @param v4Blue float value between 0 and 1
     * @param v4Alpha float value between 0 and 1
     */
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
            v1Red, v1Green, v1Blue, v1Alpha,
            v2Red, v2Green, v2Blue, v2Alpha,
            v3Red, v3Green, v3Blue, v3Alpha,
            v4Red, v4Green, v4Blue, v4Alpha
        };

        ByteBuffer clb = ByteBuffer.allocateDirect(colors.length * 4);
        clb.order(ByteOrder.nativeOrder());
        this.colorBuffer = clb.asFloatBuffer();
        this.colorBuffer.put(colors);
        this.colorBuffer.position(0);
    }

    /**
     * Returns the actual FloatBuffer containing color information
     * @return FloatBuffer object
     */
    public FloatBuffer getColorBuffer() {
        return this.colorBuffer;
    }
}
