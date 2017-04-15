precision mediump float;

uniform sampler2D texture;

varying vec4 v_Color;
varying vec2 v_Uv;

void main() {
    vec2 flipped_texcoord = vec2(v_Uv.x, 1.0 - v_Uv.y);
    gl_FragColor = v_Color + texture2D(texture, flipped_texcoord);
}
