package it.snada.bitmap_3d_string;

/**
 * Common class for object in a 3d space, containing position, rotation and scale value
 */
public abstract class Bitmap3DObject {
    float[] position, rotation, scale;

    public Bitmap3DObject() {
        this(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
    }

    public Bitmap3DObject(float xPosition, float yPosition, float zPosition, float xRotation, float yRotation, float zRotation, float xScale, float yScale, float zScale) {
        this.position = new float[3];
        this.position[0] = xPosition;
        this.position[1] = yPosition;
        this.position[2] = zPosition;

        this.rotation = new float[3];
        this.rotation[0] = xRotation;
        this.rotation[1] = yRotation;
        this.rotation[2] = zRotation;

        this.scale = new float[3];
        this.scale[0] = xScale;
        this.scale[1] = yScale;
        this.scale[2] = zScale;
    }

    public abstract float[] getModelMatrix();

    public void setRotationX(float amount) {
        rotation[0] = amount;
    }

    public void setRotationY(float amount) {
        rotation[1] = amount;
    }

    public void setRotationZ(float amount) {
        rotation[2] = amount;
    }

    public void setPositionX(float amount) {
        position[0] = amount;
    }

    public void setPositionY(float amount) {
        position[1] = amount;
    }

    public void setPositionZ(float amount) {
        position[2] = amount;
    }

    public void setScaleX(float amount) {
        scale[0] = amount;
    }

    public void setScaleY(float amount) {
        scale[1] = amount;
    }

    public void setScaleZ(float amount) {
        scale[2] = amount;
    }

    public float getPositionX() {
        return position[0];
    }

    public float getPositionY() {
        return position[1];
    }

    public float getPositionZ() {
        return position[2];
    }

    public float getRotationX() {
        return rotation[0];
    }

    public float getRotationY() {
        return rotation[1];
    }

    public float getRotationZ() {
        return rotation[2];
    }

    public float getScaleX() {
        return scale[0];
    }

    public float getScaleY() {
        return scale[1];
    }

    public float getScaleZ() {
        return scale[2];
    }
}
