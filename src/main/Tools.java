/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

/**
 *
 * @author goepfert
 */
public class Tools {

    public static int argb(int A, int R, int G, int B) {
        byte[] colorByteArr = {(byte) A, (byte) R, (byte) G, (byte) B};
        return byteArrToInt(colorByteArr);
    }

    public static int byteArrToInt(byte[] colorByteArr) {
        return (colorByteArr[0] << 24) + ((colorByteArr[1] & 0xFF) << 16) + ((colorByteArr[2] & 0xFF) << 8) + (colorByteArr[3] & 0xFF);
    }

    public static BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
        BufferedImage resizedImage = new BufferedImage(width, height, originalImage.getType());
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();
        return resizedImage;
    }

    public static String toBinaryString(byte n) {
        StringBuilder sb = new StringBuilder("00000000");
        for (int bit = 0; bit < 8; bit++) {
            if (((n >> bit) & 1) > 0) {
                sb.setCharAt(7 - bit, '1');
            }
        }
        return sb.toString();
    }

    public static short stringToByte(String s) {

        short b = 0;

        for (int i = 0; i < 8; i++) {
            char c = s.charAt(i);
            System.out.println("char " + i + ": " + c);
            if ("1".equals(c + "")) {
                b |= (1 << i);
            } else {
                b &= ~(1 << i);
            }
        }

        return b;
    }

    static Color[] shiftMatrix(Color[] colors, int nX, int nY) {
        
        Color[] shiftedColors = new Color[nX * nY];
        
        int mIdx = 0;
        int mIdx_mod = 0;
        for (int xIdx = 0; xIdx < nX; xIdx++) {
            for (int yIdx = 0; yIdx < nY; yIdx++) {

                mIdx = (yIdx * nX) + xIdx;

                if (xIdx >= nX / 2) {
                    mIdx_mod = mIdx - nX / 2;
                } else {
                    mIdx_mod = mIdx + nX / 2;
                }

                if (yIdx % 2 == 1) {
                    shiftedColors[mIdx] = colors[mIdx_mod];
                } else {
                    shiftedColors[mIdx] = colors[mIdx];
                }

            }
        }

        return shiftedColors;
    }
    
    static Image getImageFromArray(int[] pixels, int width, int height) {
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            WritableRaster raster = (WritableRaster) image.getData();
            raster.setPixels(0,0,width,height,pixels);
            return image;
        }

}
