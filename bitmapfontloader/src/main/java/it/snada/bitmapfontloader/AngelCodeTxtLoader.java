package it.snada.bitmapfontloader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Class containing a single static method to load AngelCode BMF fonts in text format
 */
public class AngelCodeTxtLoader {
    private AngelCodeTxtLoader() {}

    /**
     * Loads bitmap font information into a BitmapFont object
     * @param font Target BitmapFont object that will be loaded with font info. If informations are already present, they will be overridden.
     * @param stream InputStream containing font into AngelCode BMF text format
     * @return the same BitmapFont object
     * @throws IOException if a problem reading the stream occurs
     */
    public static BitmapFont load(BitmapFont font, InputStream stream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String line = reader.readLine();

        while(line != null) {
            String[] splitted = line.split(" ");
            switch(splitted[0]) {
                case "info":
                    for(int counter = 1; counter < splitted.length; counter++) {
                        String[] command = splitted[counter].split("=");
                        switch(command[0]) {
                            case "face":
                                font.setFace(cleanString(command[1]));
                                break;
                            case "size":
                                font.setSize(Integer.valueOf(command[1]));
                                break;
                            case "bold":
                                font.setBold(command[1].equals("1"));
                                break;
                            case "italic":
                                font.setItalic(command[1].equals("1"));
                                break;
                            case "charset":
                                font.setCharset(cleanString(command[1]));
                                break;
                            case "unicode":
                                font.setUnicode(command[1].equals("1"));
                                break;
                            case "stretchH":
                                font.setHeightPercentage(Integer.parseInt(command[1]));
                                break;
                            case "smooth":
                                font.setSmooth(command[1].equals("1"));
                                break;
                            case "aa":
                                font.setSuperSampling(Integer.parseInt(command[1]));
                                break;
                            case "padding":
                                String[] pads = command[1].split(",");
                                font.setPaddingUp(Integer.parseInt(pads[0]));
                                font.setPaddingRight(Integer.parseInt(pads[1]));
                                font.setPaddingDown(Integer.parseInt(pads[2]));
                                font.setPaddingLeft(Integer.parseInt(pads[3]));
                                break;
                            case "spacing":
                                String[] spacings = command[1].split(",");
                                font.setHorizontalSpacing(Integer.parseInt(spacings[0]));
                                font.setVerticalSpacing(Integer.parseInt(spacings[1]));
                                break;
                            case "outline":
                                font.setOutline(Integer.parseInt(command[1]));
                                break;
                        }
                    }
                    break;
                case "common":
                    for(int counter = 1; counter < splitted.length; counter++) {
                        String[] command = splitted[counter].split("=");
                        switch (command[0]) {
                            case "lineHeight":
                                font.setLineHeight(Integer.parseInt(command[1]));
                                break;
                            case "base":
                                font.setBase(Integer.parseInt(command[1]));
                                break;
                            case "scaleW":
                                font.setScaleW(Integer.parseInt(command[1]));
                                break;
                            case "scaleH":
                                font.setScaleH(Integer.parseInt(command[1]));
                                break;
                            case "packed":
                                font.setPacked(command[1].equals("1"));
                                break;
                            case "alphaChnl":
                                font.setAlphaChannel(TextureChannelContent.valueOf(Integer.parseInt(command[1])));
                                break;
                            case "redChnl":
                                font.setRedChannel(TextureChannelContent.valueOf(Integer.parseInt(command[1])));
                                break;
                            case "greenChnl":
                                font.setGreenChannel(TextureChannelContent.valueOf(Integer.parseInt(command[1])));
                                break;
                            case "blueChnl":
                                font.setBlueChannel(TextureChannelContent.valueOf(Integer.parseInt(command[1])));
                                break;
                        }
                    }
                    break;
                case "page":
                    int pageId = 0;
                    String file = "";
                    for(int counter = 1; counter < splitted.length; counter++) {
                        String[] command = splitted[counter].split("=");
                        switch (command[0]) {
                            case "id":
                                pageId = Integer.parseInt(command[1]);
                                break;
                            case "file":
                                file = cleanString(command[1]);
                                break;
                        }
                    }
                    font.insertPage(pageId, file);
                    break;
                case "char":
                    int charId = 0, x = 0, y = 0, width = 0, height = 0, xOffset = 0, yOffset = 0, xAdvance = 0, page = 0;
                    GlyphChannel channel = GlyphChannel.ALL;
                    for(int counter = 1; counter < splitted.length; counter++) {
                        String[] command = splitted[counter].split("=");
                        switch (command[0]) {
                            case "id":
                                charId = Integer.parseInt(command[1]);
                                break;
                            case "x":
                                x = Integer.parseInt(command[1]);
                                break;
                            case "y":
                                y = Integer.parseInt(command[1]);
                                break;
                            case "width":
                                width = Integer.parseInt(command[1]);
                                break;
                            case "height":
                                height = Integer.parseInt(command[1]);
                                break;
                            case "xoffset":
                                xOffset = Integer.parseInt(command[1]);
                                break;
                            case "yoffset":
                                yOffset = Integer.parseInt(command[1]);
                                break;
                            case "xadvance":
                                xAdvance = Integer.parseInt(command[1]);
                                break;
                            case "page":
                                page = Integer.parseInt(command[1]);
                                break;
                            case "chnl":
                                channel = GlyphChannel.valueOf(Integer.parseInt(command[1]));
                                break;
                        }
                    }
                    font.insertChar(new BitmapChar(charId, x, y, width, height, xOffset, yOffset, xAdvance, page, channel));
                    break;
                case "kerning":
                    int first = 0, second = 0, amount= 0;
                    for(int counter = 1; counter < splitted.length; counter++) {
                        String[] command = splitted[counter].split("=");
                        switch (command[0]) {
                            case "first":
                                first = Integer.parseInt(command[1]);
                                break;
                            case "second":
                                second = Integer.parseInt(command[1]);
                                break;
                            case "amount":
                                amount = Integer.parseInt(command[1]);
                                break;
                        }
                        font.insertKerning(first, second, amount);
                    }
                    break;
            }
            line = reader.readLine();
        }

        return font;
    }

    /**
     * Gets rid of quotes wen reading a string value
     * @param value raw string from textfile
     * @return string without first and last element: without quotes
     */
    private static String cleanString(String value) {
        return value.substring(1, value.length() - 1);
    }
}
