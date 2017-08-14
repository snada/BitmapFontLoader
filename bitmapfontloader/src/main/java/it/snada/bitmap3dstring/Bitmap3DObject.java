package it.snada.bitmap3dstring;

import android.opengl.Matrix;

/**
 * Common class for object in a 3d space, containing position, rotation and scale value
 */
public abstract class Bitmap3DObject {
    float[] position, rotation, scale, translationMatrix, rotationMatrix, scaleMatrix;

    /**
     * Returns a new 3D object positioned in the origin, with no rotation and scaling
     */
    public Bitmap3DObject() {
        this(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
    }

    /**
     * Returns a new 3D object with custom position, rotation and scale
     * @param xPosition X position in space
     * @param yPosition Y position in space
     * @param zPosition Z position in space
     * @param xRotation Rotation on X axis
     * @param yRotation Rotation on Y axis
     * @param zRotation Rotation on Z axis
     * @param xScale X axis scale
     * @param yScale Y axis scale
     * @param zScale Z axis scale
     */
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

        this.translationMatrix = new float[16];
        this.rotationMatrix = new float[16];
        this.scaleMatrix = new float[16];

        Matrix.setIdentityM(translationMatrix, 0);
        Matrix.setIdentityM(rotationMatrix, 0);
        Matrix.setIdentityM(scaleMatrix, 0);
    }

    /**
     * To be implemented in subclasses, gets a translation matrix
     * @return A float[16] array, containing translation transformation
     */
    public abstract float[] getTranslationMatrix();

    /**
     * To be implemented in subclasses, gets a rotation matrix
     * @return A float[16] array, containing rotation transformation
     */
    public abstract float[] getRotationMatrix();

    /**
     * To be implemented in subclasses, gets a scale matrix
     * @return A float[16] array, containing scale transformation
     */
    public abstract float[] getScaleMatrix();

    /**
     * To be implemented in subclasses, gets a model matrix to be used in rendering
     * @return A float[16] array, containing all transformations
     */
    public abstract float[] getModelMatrix();

    /**
     * Sets rotation on X axis
     * @param amount Rotation in degrees
     */
    public void setRotationX(float amount) {
        rotation[0] = amount;
    }

    /**
     * Sets rotation on Y axis
     * @param amount Rotation in degrees
     */
    public void setRotationY(float amount) {
        rotation[1] = amount;
    }

    /**
     * Sets rotation on Z axis
     * @param amount Rotation in degrees
     */
    public void setRotationZ(float amount) {
        rotation[2] = amount;
    }

    /**
     * Sets position on X axis
     * @param amount Position value
     */
    public void setPositionX(float amount) {
        position[0] = amount;
    }

    /**
     * Sets position on Y axis
     * @param amount Position value
     */
    public void setPositionY(float amount) {
        position[1] = amount;
    }

    /**
     * Sets position on Z axis
     * @param amount Position value
     */
    public void setPositionZ(float amount) {
        position[2] = amount;
    }

    /**
     * Sets scale on X axis
     * @param amount Scale value
     */
    public void setScaleX(float amount) {
        scale[0] = amount;
    }

    /**
     * Sets scale on Y axis
     * @param amount Scale value
     */
    public void setScaleY(float amount) {
        scale[1] = amount;
    }

    /**
     * Sets scale on Z axis
     * @param amount Scale value
     */
    public void setScaleZ(float amount) {
        scale[2] = amount;
    }

    /**
     * Gets current position on X axis
     * @return X axis float position
     */
    public float getPositionX() {
        return position[0];
    }

    /**
     * Gets current position on Y axis
     * @return Y axis float position
     */
    public float getPositionY() {
        return position[1];
    }

    /**
     * Gets current position on Z axis
     * @return Z axis float position
     */
    public float getPositionZ() {
        return position[2];
    }

    /**
     * Gets current rotation on X axis
     * @return X axis float rotation
     */
    public float getRotationX() {
        return rotation[0];
    }

    /**
     * Gets current rotation on Y axis
     * @return Y axis float rotation
     */
    public float getRotationY() {
        return rotation[1];
    }

    /**
     * Gets current rotation on Z axis
     * @return Z axis float rotation
     */
    public float getRotationZ() {
        return rotation[2];
    }

    /**
     * Gets current scale on X axis
     * @return X axis float rotation
     */
    public float getScaleX() {
        return scale[0];
    }

    /**
     * Gets current scale on Y axis
     * @return Y axis float rotation
     */
    public float getScaleY() {
        return scale[1];
    }

    /**
     * Gets current scale on Z axis
     * @return Z axis float rotation
     */
    public float getScaleZ() {
        return scale[2];
    }
}
