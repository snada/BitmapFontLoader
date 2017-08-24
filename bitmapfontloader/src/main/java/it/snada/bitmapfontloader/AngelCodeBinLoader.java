package it.snada.bitmapfontloader;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Class containing a single static method to load AngelCode BMF fonts in binary format, version 3 of the file, from BMF version 1.10 onwards
 */
public class AngelCodeBinLoader {
    private AngelCodeBinLoader() {}

    /**
     * Loads bitmap font information into a BitmapFont object from a version 3 binary file exported by AngelCode BMF version 1.10 onwards
     * @param font Target BitmapFont object that will be loaded with font info. If informations are already present, they will be overridden.
     * @param stream InputStream containing font into AngelCode BMF version 3 binary format file
     * @return the same BitmapFont object
     * @throws IOException if a problem reading the stream occurs
     */
    public static BitmapFont load(BitmapFont font, InputStream stream) throws IOException {
        String header = readString(stream, 3);
        if(!header.equals("BMF")) {
            throw new IllegalArgumentException("This is not a BMF font file!");
        }

        int version = stream.read();
        if(version != 3) {
            throw new IllegalArgumentException("This loader at the moment only supports version 3 font files");
        }

        int blockType = stream.read();
        int blockSize = readInt(stream);

        short pages = 0;

        while(blockType > 0) {
            switch(blockType) {
                case 1:
                    //INFO BLOCK
                    font.setSize(readShort(stream));
                    int bitFieldByte = stream.read();
                    font.setBold(isSet((byte) bitFieldByte, 4));
                    font.setItalic(isSet((byte) bitFieldByte, 5));
                    font.setUnicode(isSet((byte) bitFieldByte, 6));
                    font.setSmooth(isSet((byte) bitFieldByte, 7));
                    font.setCharset(String.valueOf(stream.read())); //not clear, is string in other formats, uint in binary file format
                    font.setHeightPercentage(readShort(stream));
                    font.setSuperSampling(stream.read());
                    font.setPaddingUp(stream.read());
                    font.setPaddingRight(stream.read());
                    font.setPaddingDown(stream.read());
                    font.setPaddingLeft(stream.read());
                    font.setHorizontalSpacing(stream.read());
                    font.setVerticalSpacing(stream.read());
                    font.setOutline(stream.read());
                    font.setFace(readString(stream, blockSize - 14));
                    break;
                case 2:
                    //COMMON BLOCK
                    font.setLineHeight(readShort(stream));
                    font.setBase(readShort(stream));
                    font.setScaleW(readShort(stream));
                    font.setScaleH(readShort(stream));
                    pages = readShort(stream);
                    font.setPacked(isSet((byte)stream.read(), 0));
                    font.setAlphaChannel(TextureChannelContent.valueOf(stream.read()));
                    font.setRedChannel(TextureChannelContent.valueOf(stream.read()));
                    font.setGreenChannel(TextureChannelContent.valueOf(stream.read()));
                    font.setBlueChannel(TextureChannelContent.valueOf(stream.read()));
                    break;
                case 3:
                    //PAGES BLOCK
                    for(int counter = 0; counter < pages; counter++) {
                        String read;
                        String accumulator = "";
                        while((int)(read = readString(stream, 1)).charAt(0) != 0) {
                            accumulator += read;
                        }
                        font.insertPage(counter, accumulator);
                    }
                    break;
                case 4:
                    //CHARS BLOCK
                    for(int counter = 0; counter < blockSize; counter += 20) {
                        font.insertChar(
                            new BitmapChar(
                                readInt(stream),
                                readShort(stream),
                                readShort(stream),
                                readShort(stream),
                                readShort(stream),
                                readShort(stream),
                                readShort(stream),
                                readShort(stream),
                                stream.read(),
                                GlyphChannel.valueOf(stream.read())
                            )
                        );
                    }
                    break;
                case 5:
                    //KERNINGS BLOCK
                    for(int counter = 0; counter < blockSize; counter += 10) {
                        font.insertKerning(
                            readInt(stream),
                            readInt(stream),
                            readShort(stream)
                        );
                    }
                    break;
            }
            blockType = stream.read();
            blockSize = readInt(stream);
        }

        return font;
    }

    /**
     * Helper method to check wheter a bit in a byte value is set to 1 or not
     * @param value byte representing a bit mask
     * @param bit position of the bit to check in the byte
     * @return true if the provided "bit" position in the "value" byte is set to 1, false otherwise
     */
    private static boolean isSet(byte value, int bit) {
        int b = (int)value & 0xff;
        b >>= bit;
        b &= 0x01;
        return b != 0;
    }

    /**
     * Helper method to read a String of fixed length from a binary file
     * @param s InputStream to read from
     * @param length length of the String to read
     * @return a String object read from the provided stream
     * @throws IOException if an error occurs reading bytes from the stream
     */
    private static String readString(InputStream s, int length) throws IOException {
        byte[] bytes = new byte[length];
        s.read(bytes);
        return new String(bytes);
    }

    /**
     * Helper method to read a short number (2 bytes, little endian) from a binary file
     * @param s InputStream to read from
     * @return a short number
     * @throws IOException if an error occurs reading bytes from the stream
     */
    private static short readShort(InputStream s) throws IOException {
        byte[] bytes = new byte[2];
        s.read(bytes);
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        return buffer.getShort();
    }

    /**
     * Helper method to read an integer number (4 bytes, little endian) from a binary file
     * @param s InputStream to read from
     * @return an integer number
     * @throws IOException if an error occurs reading bytes from the stream
     */
    private static int readInt(InputStream s) throws IOException {
        byte[] bytes = new byte[4];
        s.read(bytes);
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        return buffer.getInt();
    }
}
