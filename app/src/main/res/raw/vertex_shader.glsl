uniform mat4 model;
uniform mat4 view;
uniform mat4 projection;

attribute vec3 vPosition;
attribute vec4 vColor;
//attribute vec3 vNormal;
attribute vec2 vUv;

varying vec4 v_Color;
varying vec2 v_Uv;

void main()
{
    gl_Position = projection * view * model * vec4(vPosition, 1.0);
    v_Color = vColor;
    v_Uv = vUv;
}