package it.snada.bitmap_3d_string;

import android.opengl.Matrix;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Matrix.class)
public class Bitmap3DObjectTest {
    private class Bitmap3DObjectImplementation extends Bitmap3DObject {
        public Bitmap3DObjectImplementation() {
            super();
        }

        public Bitmap3DObjectImplementation(float xPos, float yPos, float zPos, float xRot, float yRot, float zRot, float xScale, float yScale, float zScale) {
            super(xPos, yPos, zPos, xRot, yRot, zRot, xScale, yScale, zScale);
        }

        // Stub, will be tested in derived classes
        public float[] getTranslationMatrix() {
            return new float[16];
        }

        // Stub, will be tested in derived classes
        public float[] getScaleMatrix() {
            return new float[16];
        }

        // Stub, will be tested in derived classes
        public float[] getRotationMatrix() {
            return new float[16];
        }

        // Stub, will be tested in derived classes
        public float[] getModelMatrix() {
            return new float[16];
        }
    }

    @Before
    public void setUp() throws Exception {
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

    @Test
    public void setRotationX() throws Exception {
        Bitmap3DObjectImplementation o = new Bitmap3DObjectImplementation();
        o.setRotationX(1.0f);
        assertEquals(1.0f, o.getRotationX(), 0);
    }

    @Test
    public void setRotationY() throws Exception {
        Bitmap3DObjectImplementation o = new Bitmap3DObjectImplementation();
        o.setRotationY(1.0f);
        assertEquals(1.0f, o.getRotationY(), 0);
    }

    @Test
    public void setRotationZ() throws Exception {
        Bitmap3DObjectImplementation o = new Bitmap3DObjectImplementation();
        o.setRotationZ(1.0f);
        assertEquals(1.0f, o.getRotationZ(), 0);
    }

    @Test
    public void setPositionX() throws Exception {
        Bitmap3DObjectImplementation o = new Bitmap3DObjectImplementation();
        o.setPositionX(1.0f);
        assertEquals(1.0f, o.getPositionX(), 0);
    }

    @Test
    public void setPositionY() throws Exception {
        Bitmap3DObjectImplementation o = new Bitmap3DObjectImplementation();
        o.setPositionY(1.0f);
        assertEquals(1.0f, o.getPositionY(), 0);
    }

    @Test
    public void setPositionZ() throws Exception {
        Bitmap3DObjectImplementation o = new Bitmap3DObjectImplementation();
        o.setPositionZ(1.0f);
        assertEquals(1.0f, o.getPositionZ(), 0);
    }

    @Test
    public void setScaleX() throws Exception {
        Bitmap3DObjectImplementation o = new Bitmap3DObjectImplementation();
        o.setScaleX(1.0f);
        assertEquals(1.0f, o.getScaleX(), 0);
    }

    @Test
    public void setScaleY() throws Exception {
        Bitmap3DObjectImplementation o = new Bitmap3DObjectImplementation();
        o.setScaleY(1.0f);
        assertEquals(1.0f, o.getScaleY(), 0);
    }

    @Test
    public void setScaleZ() throws Exception {
        Bitmap3DObjectImplementation o = new Bitmap3DObjectImplementation();
        o.setScaleZ(1.0f);
        assertEquals(1.0f, o.getScaleZ(), 0);
    }

    @Test
    public void getPositionX() throws Exception {
        Bitmap3DObjectImplementation o = new Bitmap3DObjectImplementation(1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        assertEquals(1.0f, o.getPositionX(), 0);
    }

    @Test
    public void getPositionY() throws Exception {
        Bitmap3DObjectImplementation o = new Bitmap3DObjectImplementation(0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        assertEquals(1.0f, o.getPositionY(), 0);
    }

    @Test
    public void getPositionZ() throws Exception {
        Bitmap3DObjectImplementation o = new Bitmap3DObjectImplementation(0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        assertEquals(1.0f, o.getPositionZ(), 0);
    }

    @Test
    public void getRotationX() throws Exception {
        Bitmap3DObjectImplementation o = new Bitmap3DObjectImplementation(0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        assertEquals(1.0f, o.getRotationX(), 0);
    }

    @Test
    public void getRotationY() throws Exception {
        Bitmap3DObjectImplementation o = new Bitmap3DObjectImplementation(0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        assertEquals(1.0f, o.getRotationY(), 0);
    }

    @Test
    public void getRotationZ() throws Exception {
        Bitmap3DObjectImplementation o = new Bitmap3DObjectImplementation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f);
        assertEquals(1.0f, o.getRotationZ(), 0);
    }

    @Test
    public void getScaleX() throws Exception {
        Bitmap3DObjectImplementation o = new Bitmap3DObjectImplementation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f);
        assertEquals(1.0f, o.getScaleX(), 0);
    }

    @Test
    public void getScaleY() throws Exception {
        Bitmap3DObjectImplementation o = new Bitmap3DObjectImplementation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);
        assertEquals(1.0f, o.getScaleY(), 0);
    }

    @Test
    public void getScaleZ() throws Exception {
        Bitmap3DObjectImplementation o = new Bitmap3DObjectImplementation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f);
        assertEquals(1.0f, o.getScaleZ(), 0);
    }
}
