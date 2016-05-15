package com.spellcastrpg.main.rendering;

import com.spellcastrpg.main.geometry.*;
import com.spellcastrpg.main.geometry.Rectangle;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

/**
 * Created by laser_000 on 5/14/2016.
 */
public class Renderer {
    private final Graphics2D g2d;

    public Renderer(Graphics2D g2d) {
        this.g2d = g2d;
    }

    public Graphics2D getGraphics2d() {
        return this.g2d;
    }

    public void fillRect(Rectangle rect, RGBAColor color) {
        this.g2d.setColor(color.toColor());
        this.g2d.fillRect((int) Math.round(rect.getPosition().getX()), (int) Math.round(rect.getPosition().getY()),
                (int) Math.round(rect.getWidth()), (int) Math.round(rect.getHeight()));
    }
    public void drawRect(Rectangle rect, double thickness, RGBAColor color) {
        Stroke oldStroke = this.g2d.getStroke();
        this.g2d.setStroke(new BasicStroke(Double.valueOf(thickness).floatValue()));
        this.g2d.setColor(color.toColor());
        this.g2d.drawRect((int) Math.round(rect.getPosition().getX()), (int) Math.round(rect.getPosition().getY()),
                (int) Math.round(rect.getWidth()), (int) Math.round(rect.getHeight()));
        this.g2d.setStroke(oldStroke);
    }
    public void fillRoundedRect(Rectangle rect, double arcWidth, double arcHeight, RGBAColor color) {
        this.g2d.setColor(color.toColor());
        this.g2d.fillRoundRect((int) Math.round(rect.getPosition().getX()), (int) Math.round(rect.getPosition().getY()),
                (int) Math.round(rect.getWidth()), (int) Math.round(rect.getHeight()),
                (int) Math.round(arcWidth), (int) Math.round(arcHeight));
    }
    public void drawRoundedRect(Rectangle rect, double arcWidth, double arcHeight, double thickness, RGBAColor color) {
        Stroke oldStroke = this.g2d.getStroke();
        this.g2d.setStroke(new BasicStroke(Double.valueOf(thickness).floatValue()));
        this.g2d.setColor(color.toColor());
        this.g2d.drawRoundRect((int) Math.round(rect.getPosition().getX()), (int) Math.round(rect.getPosition().getY()),
                (int) Math.round(rect.getWidth()), (int) Math.round(rect.getHeight()),
                (int) Math.round(arcWidth), (int) Math.round(arcHeight));
        this.g2d.setStroke(oldStroke);
    }

    public void drawText(String text, Font font, Vector2d position, RGBAColor color) {
        this.g2d.setColor(color.toColor());
        this.g2d.setFont(font);
        FontMetrics metric = this.g2d.getFontMetrics(font);
        this.g2d.drawString(text, (int) Math.round(position.getX()),
                (int) Math.round(position.getY()) + metric.getAscent() - metric.getDescent() - metric.getLeading());
    }
    public void drawTextCentered(String text, Font font, Rectangle bounds, RGBAColor color) {
        this.g2d.setColor(color.toColor());
        this.g2d.setFont(font);
        FontMetrics fm = this.g2d.getFontMetrics();
        double x = (bounds.getWidth() - fm.stringWidth(text)) / 2.0 + bounds.getPosition().getX();
        double y = (fm.getAscent() + (bounds.getHeight() - (fm.getAscent() + fm.getDescent())) / 2.0) + bounds.getPosition().getY();
        this.g2d.drawString(text, (int) Math.round(x), (int) Math.round(y));
    }

    public void drawImage(BufferedImage image, Vector2d position) {
        this.g2d.drawImage(image, null, (int) Math.round(position.getX()), (int) Math.round(position.getY()));
    }
    public void drawRoundedImage(BufferedImage image, double arcWidth, double arcHeight, Vector2d position) {
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = output.createGraphics();
        // This is what we want, but it only does hard-clipping, i.e. aliasing
        // g2.setClip(new RoundRectangle2D ...)

        // so instead fake soft-clipping by first drawing the desired clip shape
        // in fully opaque white with antialiasing enabled...
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fill(new RoundRectangle2D.Float(0, 0, w, h, (int) Math.round(arcWidth), (int) Math.round(arcHeight)));
        g2.setComposite(AlphaComposite.SrcIn);
        g2.drawImage(image, null, 0, 0);
        this.g2d.drawImage(output, (int) Math.round(position.getX()), (int) Math.round(position.getY()), null);
    }
}
