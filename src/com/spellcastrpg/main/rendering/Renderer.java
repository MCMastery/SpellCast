package com.spellcastrpg.main.rendering;

import com.spellcastrpg.main.SpellCast;
import com.spellcastrpg.main.geometry.Rectangle;
import com.spellcastrpg.main.geometry.Vector2d;
import com.spellcastrpg.main.objects.gui.GUITextContainer;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by laser_000 on 5/14/2016.
 */
public class Renderer {
    public static Font MAIN_FONT = null;

    static {
        try {
            InputStream stream = SpellCast.getResourceStream("main font.ttf");
            Font font = Font.createFont(Font.TRUETYPE_FONT, stream);
            MAIN_FONT = font.deriveFont(Font.PLAIN, 16);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }



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




    // draws single line of text
    public void drawText(String text, Font font, Rectangle bounds, GUITextContainer.HAlignment horizAlign, GUITextContainer.VAlignment vertAlign, RGBAColor color) {
        this.g2d.setColor(color.toColor());
        this.g2d.setFont(font);
        FontMetrics fm = this.g2d.getFontMetrics();
        double x, y;

        switch (horizAlign) {
            case CENTER:
                x = (bounds.getWidth() - fm.stringWidth(text)) / 2.0 + bounds.getX();
                break;
            case RIGHT:
                x = (bounds.getWidth() - fm.stringWidth(text)) + bounds.getX();
                break;
            default:
                x = bounds.getX();
                break;
        }
        y = fm.getAscent() + (bounds.getHeight() - (fm.getAscent() + fm.getDescent())) / 2.0 + bounds.getY();
        switch (vertAlign) {
            case CENTER:
                y -= bounds.getY();
                y /= 2;
                y += bounds.getCenter().getY();
                break;
            case BOTTOM:
                y = bounds.getY2();
            default:
                break;
        }
        this.g2d.drawString(text, (int) Math.round(x), (int) Math.round(y));
    }

    // returns the bounds the text used up
    //todo this returns a little extra height for some reason (not a lot)
    //todo IMPLEMENT VERTICAL ALIGN
    public Rectangle drawTextWordWrap(String text, Font font, Rectangle bounds, GUITextContainer.HAlignment horizAlign, GUITextContainer.VAlignment vertAlign, double lineSpacing, RGBAColor color) {
        List<String> lines = wordWrap(text, font, bounds);
        double x = bounds.getX(), y = bounds.getY();
        for (String line : lines) {
            Rectangle lineBounds = getTextBounds(line, font);
            drawText(line, font, new Rectangle(new Vector2d(x, y), bounds.getWidth(), lineBounds.getHeight()), horizAlign, vertAlign, color);
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
    public void drawImageCentered(BufferedImage image, Vector2d center) {
        drawImage(image, center.subtract(image.getWidth() / 2.0, image.getHeight() / 2.0));
    }
    public void drawRoundedImage(BufferedImage image, double arcWidth, double arcHeight, Vector2d position) {
        this.g2d.drawImage(ImageUtils.round(image, arcWidth, arcHeight), (int) Math.round(position.getX()), (int) Math.round(position.getY()), null);
    }
    public void drawRoundedImageCentered(BufferedImage image, double arcWidth, double arcHeight, Vector2d center) {
        drawRoundedImage(image, arcWidth, arcHeight, center.subtract(image.getWidth() / 2.0, image.getHeight() / 2.0));
    }



    public void drawAnimationFrame(Animation animation, Vector2d position) {
        drawImage(animation.getCurrentFrame(), position);
    }
    public void drawRoundedAnimationFrame(Animation animation, double arcWidth, double arcHeight,  Vector2d position) {
        drawRoundedImage(animation.getCurrentFrame(), arcWidth, arcHeight, position);
    }



    public void fillPolygon(RGBAColor color, Vector2d... vertices) {
        this.g2d.setColor(color.toColor());
        Polygon polygon = new Polygon();
        for (Vector2d vertex : vertices)
            polygon.addPoint((int) Math.round(vertex.getX()), (int) Math.round(vertex.getY()));
        this.g2d.fillPolygon(polygon);
    }
    public void drawPolygon(RGBAColor color, double thickness, Vector2d... vertices) {
        Stroke oldStroke = this.g2d.getStroke();
        this.g2d.setStroke(new BasicStroke(Double.valueOf(thickness).floatValue()));
        this.g2d.setColor(color.toColor());
        Polygon polygon = new Polygon();
        for (Vector2d vertex : vertices)
            polygon.addPoint((int) Math.round(vertex.getX()), (int) Math.round(vertex.getY()));
        this.g2d.drawPolygon(polygon);
        this.g2d.setStroke(oldStroke);
    }
}
