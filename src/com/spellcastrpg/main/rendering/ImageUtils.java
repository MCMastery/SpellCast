package com.spellcastrpg.main.rendering;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

public class ImageUtils {
    private ImageUtils() {}

    public static RGBAColor getAverageColor(BufferedImage image) {
        RGBAColor average = RGBAColor.TRANSPARENT;
        if (image == null)
            return null;
        int pixels = 0;
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                RGBAColor pixel = RGBAColor.fromColor(new Color(image.getRGB(x, y), true));
                if (pixel.getA() == 0)
                    continue;
                average = average.add(pixel);
                pixels++;
            }
        }
        return average.divide(pixels);
    }


    public static BufferedImage round(BufferedImage image, double arcWidth, double arcHeight) {
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = output.createGraphics();
        // This is what we want, but it only does hard-clipping, i.e. aliasing
        // g2.setClip(new RoundRectangle2D ...)

        // so instead fake soft-clipping by first drawing the desired clip shape
        // in fully opaque white with antialiasing enabled...
        g2d.setComposite(AlphaComposite.Src);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.WHITE);
        g2d.fill(new RoundRectangle2D.Float(0, 0, w, h, (int) Math.round(arcWidth), (int) Math.round(arcHeight)));
        g2d.setComposite(AlphaComposite.SrcIn);
        g2d.drawImage(image, null, 0, 0);
        g2d.dispose();
        return output;
    }


    public static BufferedImage setSize(BufferedImage image, double width, double height) {
        BufferedImage output = new BufferedImage((int) Math.round(width), (int) Math.round(height), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = output.createGraphics();
        g2d.drawImage(image, 0, 0, (int) Math.round(width), (int) Math.round(height), null);
        g2d.dispose();
        return output;
    }
}
