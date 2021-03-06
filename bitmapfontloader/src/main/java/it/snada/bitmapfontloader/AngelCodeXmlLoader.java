package it.snada.bitmapfontloader;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Class containing a single static method to load AngelCode BMF fonts in xml format
 */
public class AngelCodeXmlLoader {
    private AngelCodeXmlLoader() {

    }

    /**
     * Loads bitmap font information into a BitmapFont object from an xml file exported by AngelCode BMF
     * @param font Target BitmapFont object that will be loaded with font info. If informations are already present, they will be overridden.
     * @param stream InputStream containing font into AngelCode BMF xml format
     * @return the same BitmapFont object
     * @throws IOException if a problem reading the stream occurs
     * @throws XmlPullParserException if a problem reading the xml syntax occur
     */
    public static BitmapFont load(BitmapFont font, InputStream stream) throws IOException, XmlPullParserException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = factory.newPullParser();

        parser.setInput(reader);
        parser.nextTag();

        int eventType = parser.getEventType();

        while(eventType != XmlPullParser.END_DOCUMENT) {
            if(eventType == XmlPullParser.START_TAG) {
                switch(parser.getName()) {
                    case "info":
                        font.setFace(parser.getAttributeValue(null, "face"));
                        font.setSize(Integer.parseInt(parser.getAttributeValue(null, "size")));
                        font.setBold(parser.getAttributeValue(null, "bold").equals("1"));
                        font.setItalic(parser.getAttributeValue(null, "italic").equals("1"));
                        font.setUnicode(parser.getAttributeValue(null, "unicode").equals("1"));
                        font.setCharset(parser.getAttributeValue(null, "charset"));
                        font.setHeightPercentage(Integer.parseInt(parser.getAttributeValue(null, "stretchH")));
                        font.setSmooth(parser.getAttributeValue(null, "smooth").equals("1"));
                        font.setSuperSampling(Integer.parseInt(parser.getAttributeValue(null, "aa")));

                        String[] pads = parser.getAttributeValue(null, "padding").split(",");
                        font.setPaddingUp(Integer.parseInt(pads[0]));
                        font.setPaddingRight(Integer.parseInt(pads[1]));
                        font.setPaddingDown(Integer.parseInt(pads[2]));
                        font.setPaddingLeft(Integer.parseInt(pads[3]));

                        String[] spacings = parser.getAttributeValue(null, "spacing").split(",");
                        font.setHorizontalSpacing(Integer.parseInt(spacings[0]));
                        font.setVerticalSpacing(Integer.parseInt(spacings[1]));

                        font.setOutline(Integer.parseInt(parser.getAttributeValue(null, "outline")));
                        break;
                    case "page":
                        font.insertPage(Integer.parseInt(parser.getAttributeValue(null, "id")), parser.getAttributeValue(null, "file"));
                        break;
                    case "common":
                        font.setLineHeight(Integer.parseInt(parser.getAttributeValue(null, "lineHeight")));
                        font.setBase(Integer.parseInt(parser.getAttributeValue(null, "base")));
                        font.setScaleW(Integer.parseInt(parser.getAttributeValue(null, "scaleW")));
                        font.setScaleH(Integer.parseInt(parser.getAttributeValue(null, "scaleH")));
                        font.setPacked(parser.getAttributeValue(null, "packed").equals("1"));
                        font.setAlphaChannel(TextureChannelContent.valueOf(Integer.parseInt(parser.getAttributeValue(null, "alphaChnl"))));
                        font.setRedChannel(TextureChannelContent.valueOf(Integer.parseInt(parser.getAttributeValue(null, "redChnl"))));
                        font.setGreenChannel(TextureChannelContent.valueOf(Integer.parseInt(parser.getAttributeValue(null, "greenChnl"))));
                        font.setBlueChannel(TextureChannelContent.valueOf(Integer.parseInt(parser.getAttributeValue(null, "blueChnl"))));
                        break;
                    case "char":
                        font.insertChar(new BitmapChar(
                            Integer.parseInt(parser.getAttributeValue(null, "id")),
                            Integer.parseInt(parser.getAttributeValue(null, "x")),
                            Integer.parseInt(parser.getAttributeValue(null, "y")),
                            Integer.parseInt(parser.getAttributeValue(null, "width")),
                            Integer.parseInt(parser.getAttributeValue(null, "height")),
                            Integer.parseInt(parser.getAttributeValue(null, "xoffset")),
                            Integer.parseInt(parser.getAttributeValue(null, "yoffset")),
                            Integer.parseInt(parser.getAttributeValue(null, "xadvance")),
                            Integer.parseInt(parser.getAttributeValue(null, "page")),
                            GlyphChannel.valueOf(
                                Integer.parseInt(parser.getAttributeValue(null, "chnl"))
                            )
                        ));
                        break;
                    case "kerning":
                        font.insertKerning(
                            Integer.parseInt(parser.getAttributeValue(null, "first")),
                            Integer.parseInt(parser.getAttributeValue(null, "second")),
                            Integer.parseInt(parser.getAttributeValue(null, "amount"))
                        );
                        break;
                }
            }
            eventType = parser.next();
        }

        return font;
    }
}
