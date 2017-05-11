precision mediump float;

uniform sampler2D texture;

varying vec4 v_Color;
varying vec2 v_Uv;

void main() {
    vec2 flippedTexcoord = vec2(v_Uv.x, v_Uv.y);
    vec4 fontColor = texture2D(texture, flippedTexcoord);
    if(fontColor.a <= 0.5) {
        discard;
    } else {
        gl_FragColor = v_Color * fontColor;
    }

}
