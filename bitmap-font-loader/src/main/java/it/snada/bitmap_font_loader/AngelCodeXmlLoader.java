package it.snada.bitmap_font_loader;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AngelCodeXmlLoader {
    private AngelCodeXmlLoader() {

    }

    public static BitmapFont load(InputStream stream) throws IOException, XmlPullParserException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = factory.newPullParser();

        parser.setInput(reader);
        parser.nextTag();

        BitmapFont font = new BitmapFont();

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
                        font.setUnicode(parser.getAttributeValue(null, "smooth").equals("1"));
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
                }
            }
            eventType = parser.next();
        }

        return font;
    }
}
