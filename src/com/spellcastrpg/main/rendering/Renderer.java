package com.spellcastrpg.main.rendering;

import com.spellcastrpg.main.geometry.*;
import com.spellcastrpg.main.geometry.Rectangle;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

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
        this.g2d.fillRect((int) Math.round(rect.getX()), (int) Math.round(rect.getY()),
                (int) Math.round(rect.getWidth()), (int) Math.round(rect.getHeight()));
    }
    public void drawRect(Rectangle rect, double thickness, RGBAColor color) {
        Stroke oldStroke = this.g2d.getStroke();
        this.g2d.setStroke(new BasicStroke(Double.valueOf(thickness).floatValue()));
        this.g2d.setColor(color.toColor());
        this.g2d.drawRect((int) Math.round(rect.getX()), (int) Math.round(rect.getY()), (int) Math.round(rect.getWidth()), (int) Math.round(rect.getHeight()));
        this.g2d.setStroke(oldStroke);
    }
    public void fillRoundedRect(Rectangle rect, double arcWidth, double arcHeight, RGBAColor color) {
        this.g2d.setColor(color.toColor());
        this.g2d.fillRoundRect((int) Math.round(rect.getX()), (int) Math.round(rect.getY()),
                (int) Math.round(rect.getWidth()), (int) Math.round(rect.getHeight()),
                (int) Math.round(arcWidth), (int) Math.round(arcHeight));
    }
    public void drawRoundedRect(Rectangle rect, double arcWidth, double arcHeight, double thickness, RGBAColor color) {
        Stroke oldStroke = this.g2d.getStroke();
        this.g2d.setStroke(new BasicStroke(Double.valueOf(thickness).floatValue()));
        this.g2d.setColor(color.toColor());
        this.g2d.drawRoundRect((int) Math.round(rect.getX()), (int) Math.round(rect.getY()),
                (int) Math.round(rect.getWidth()), (int) Math.round(rect.getHeight()),
                (int) Math.round(arcWidth), (int) Math.round(arcHeight));
        this.g2d.setStroke(oldStroke);
    }


    public void fillEllipse(Rectangle bounds, RGBAColor color) {
        this.g2d.setColor(color.toColor());
        this.g2d.fillOval((int) Math.round(bounds.getX()), (int) Math.round(bounds.getY()),
                (int) Math.round(bounds.getWidth()), (int) Math.round(bounds.getHeight()));
    }
    public void drawEllipse(Rectangle bounds, double thickness, RGBAColor color) {
        Stroke oldStroke = this.g2d.getStroke();
        this.g2d.setStroke(new BasicStroke(Double.valueOf(thickness).floatValue()));
        this.g2d.setColor(color.toColor());
        this.g2d.drawOval((int) Math.round(bounds.getX()), (int) Math.round(bounds.getY()), (int) Math.round(bounds.getWidth()), (int) Math.round(bounds.getHeight()));
        this.g2d.setStroke(oldStroke);
    }



    public Rectangle getTextBounds(String text, Font font) {
        Rectangle2D rect = this.g2d.getFontMetrics(font).getStringBounds(text, this.g2d);
        return new Rectangle(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
    }
    public Rectangle getTextBounds(String text, Rectangle bounds, double lineSpacing, Font font) {
        List<String> lines = wordWrap(text, font, bounds);
        double x = bounds.getX(), y = bounds.getY();
        for (String line : lines) {
            Rectangle lineBounds = getTextBounds(line, font);
            y += lineBounds.getHeight() + lineSpacing;
        }
        return new Rectangle(bounds.getPosition(), bounds.getWidth(), y - bounds.getY() - lineSpacing);
    }


    public void drawText(String text, Font font, Vector2d position, RGBAColor color) {
        this.g2d.setColor(color.toColor());
        this.g2d.setFont(font);
        FontMetrics metric = this.g2d.getFontMetrics(font);
        this.g2d.drawString(text, (int) Math.round(position.getX()), (int) Math.round(position.getY()) + metric.getAscent() - metric.getDescent() - metric.getLeading());
    }
    public void drawTextCentered(String text, Font font, Rectangle bounds, RGBAColor color) {
        this.g2d.setColor(color.toColor());
        this.g2d.setFont(font);
        FontMetrics fm = this.g2d.getFontMetrics();
        double x = (bounds.getWidth() - fm.stringWidth(text)) / 2.0 + bounds.getX();
        double y = (fm.getAscent() + (bounds.getHeight() - (fm.getAscent() + fm.getDescent())) / 2.0) + bounds.getY();
        this.g2d.drawString(text, (int) Math.round(x), (int) Math.round(y));
    }


    // returns the bounds the text used up
    //todo this returns a little extra height for some reason (not a lot)
    public Rectangle drawTextWordWrap(String text, Font font, Rectangle bounds, double lineSpacing, RGBAColor color) {
        List<String> lines = wordWrap(text, font, bounds);
        double x = bounds.getX(), y = bounds.getY();
        for (String line : lines) {
            drawText(line, font, new Vector2d(x, y), color);
            Rectangle lineBounds = getTextBounds(line, font);
            y += lineBounds.getHeight() + lineSpacing;
        }
        return new Rectangle(bounds.getPosition(), bounds.getWidth(), y - bounds.getY() - lineSpacing);
    }
    // returns the bounds the text used up
    //todo this returns a little extra height for some reason (not a lot)
    public Rectangle drawTextWordWrapCentered(String text, Font font, Rectangle bounds, double lineSpacing, RGBAColor color) {
        List<String> lines = wordWrap(text, font, bounds);
        double x = bounds.getX(), y = bounds.getY();
        for (String line : lines) {
            Rectangle lineBounds = getTextBounds(line, font);
            drawTextCentered(line, font, new Rectangle(new Vector2d(x, y), bounds.getWidth(), lineBounds.getHeight()), color);
            y += lineBounds.getHeight() + lineSpacing;
        }
        return new Rectangle(bounds.getPosition(), bounds.getWidth(), y - bounds.getY() - lineSpacing);
    }


    public List<String> wordWrap(String text, Font font, Rectangle bounds) {
        // split by spaces & keep spaces at start of each word
        String[] words = text.split("(?<= )");
        List<String> lines = new ArrayList<>();
        double lineSpaceLeft = bounds.getWidth();
        String line = "";
        for (String word : words) {
            double width = getTextBounds(word, font).getWidth();
            if (width > lineSpaceLeft) {
                lines.add(line);
                // add word to next line
                lineSpaceLeft = bounds.getWidth();
                line = word;
            } else
                line += word;
            lineSpaceLeft -= width;
        }
        if (!line.isEmpty())
            lines.add(line);
        return lines;
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
