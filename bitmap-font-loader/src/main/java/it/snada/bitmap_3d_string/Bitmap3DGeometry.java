package it.snada.bitmap_3d_string;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * Singleton class to get geometry vertexBuffer and indexBuffer of a 1x1 quad, used in this 3d font rendering method
 */
public class Bitmap3DGeometry {
    private static Bitmap3DGeometry instance = null;

    protected FloatBuffer vertexBuffer;
    protected ShortBuffer indexBuffer;

    protected Bitmap3DGeometry() {
        //Nothing to do here
    }

    /**
     * Gets the single instance of this geometry
     * @return the Bitmap3DGeometry instance
     */
    public static Bitmap3DGeometry getInstance() {
        if(instance == null) {
            instance = new Bitmap3DGeometry();

            float[] vertices = {
                0.0f, 0.0f, 0.0f, // top left
                0.0f, -1.0f, 0.0f, // bottom left
                1.0f, -1.0f, 0.0f, // bottom right
                1.0f, 0.0f, 0.0f // top right
            };
            short[] indices = {
                0, 1, 2, 0, 2, 3
            };

            ByteBuffer bb = ByteBuffer.allocateDirect(vertices.length * 4);
            bb.order(ByteOrder.nativeOrder());
            instance.vertexBuffer = bb.asFloatBuffer();
            instance.vertexBuffer.put(vertices);
            instance.vertexBuffer.position(0);

            ByteBuffer dlb = ByteBuffer.allocateDirect(indices.length * 2);
            dlb.order(ByteOrder.nativeOrder());
            instance.indexBuffer = dlb.asShortBuffer();
            instance.indexBuffer.put(indices);
            instance.indexBuffer.position(0);
        }
        return instance;
    }

    /**
     * Gets the vertex buffer of this geometry
     * @return a FloatBuffer containing vertex data
     */
    public FloatBuffer getVertexBuffer() {
        return this.vertexBuffer;
    }

    /**
     * Gets the index buffer of this geometry
     * @return a ShortBuffer containing indices data
     */
    public ShortBuffer getIndexBuffer() {
        return this.indexBuffer;
    }
}
