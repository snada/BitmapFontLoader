package it.snada.bitmap_3d_string;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import static org.junit.Assert.assertEquals;

@RunWith(PowerMockRunner.class)
public class Bitmap3DGeometryTest {
    FloatBuffer normalBuffer;
    FloatBuffer vertexBuffer;
    ShortBuffer indexBuffer;

    @Before
    public void setUp() throws Exception {
        float[] normals = {
            0.0f, 0.0f, 1.0f,
            0.0f, 0.0f, 1.0f,
            0.0f, 0.0f, 1.0f,
            0.0f, 0.0f, 1.0f
        };
        float[] vertices = {
            0.0f, 0.0f, 0.0f, // top left
            0.0f, -1.0f, 0.0f, // bottom left
            1.0f, -1.0f, 0.0f, // bottom right
            1.0f, 0.0f, 0.0f // top right
        };
        short[] indices = {
            0, 1, 2, 0, 2, 3
        };

        ByteBuffer nbb = ByteBuffer.allocateDirect(normals.length * 4);
        nbb.order(ByteOrder.nativeOrder());
        normalBuffer = nbb.asFloatBuffer();
        normalBuffer.put(normals);
        normalBuffer.position(0);

        ByteBuffer bb = ByteBuffer.allocateDirect(vertices.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);

        ByteBuffer dlb = ByteBuffer.allocateDirect(indices.length * 2);
        dlb.order(ByteOrder.nativeOrder());
        indexBuffer = dlb.asShortBuffer();
        indexBuffer.put(indices);
        indexBuffer.position(0);
    }

    @Test
    public void testGetVertexBuffer() throws Exception {
        Bitmap3DGeometry o = Bitmap3DGeometry.getInstance();
        assertEquals(vertexBuffer, o.getVertexBuffer());
    }

    @Test
    public void testGetIndexBuffer() throws Exception {
        Bitmap3DGeometry o = Bitmap3DGeometry.getInstance();
        assertEquals(indexBuffer, o.getIndexBuffer());
    }

    @Test
    public void testGetNormalBuffer() throws Exception {
        Bitmap3DGeometry o = Bitmap3DGeometry.getInstance();
        assertEquals(normalBuffer, o.getNormalBuffer());
    }
}
