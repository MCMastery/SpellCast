package com.spellcastrpg.main.objects.gui;

import com.spellcastrpg.main.rendering.RGBAColor;
import com.spellcastrpg.main.rendering.Renderer;

import java.awt.*;

public class GUIText extends GUIObject {
    public enum Alignment {
        LEFT, CENTER
    }

    private String text;
    private Font font;
    private RGBAColor color;
    private Alignment alignment;
    private boolean wordWrap;
    private double lineSpacing;

    public GUIText() {
        this("");
    }
    public GUIText(String text) {
        this(text, Renderer.MAIN_FONT);
    }
    public GUIText(String text, Font font) {
        this(text, font, RGBAColor.BLACK);
    }
    public GUIText(String text, RGBAColor color) {
        this(text, Renderer.MAIN_FONT, color);
    }
    public GUIText(String text, Font font, RGBAColor color) {
        this.text = text;
        this.font = font;
        this.color = color;
        this.wordWrap = true;
        this.alignment = Alignment.LEFT;
        this.lineSpacing = 7.5;
    }

    public String getText() {
        return this.text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public Font getFont() {
        return this.font;
    }
    public void setFont(Font font) {
        this.font = font;
    }
    public RGBAColor getColor() {
        return this.color;
    }
    public void setColor(RGBAColor color) {
        this.color = color;
    }
    public Alignment getAlignment() {
        return this.alignment;
    }
    public void setAlignment(Alignment alignment) {
        this.alignment = alignment;
    }
    public boolean useWordWrap() {
        return this.wordWrap;
    }
    public void useWordWrap(boolean wordWrap) {
        this.wordWrap = wordWrap;
    }
    public double getLineSpacing() {
        return this.lineSpacing;
    }
    public void setLineSpacing(double lineSpacing) {
        this.lineSpacing = lineSpacing;
    }

    @Override
    public void render(Renderer r) {
        if (this.wordWrap) {
            if (this.alignment == Alignment.LEFT)
                r.drawTextWordWrap(this.text, this.font, getBounds(), this.lineSpacing, this.color);
            else if (this.alignment == Alignment.CENTER)
                r.drawTextWordWrapCentered(this.text, this.font, getBounds(), this.lineSpacing, this.color);
        } else {
            if (this.alignment == Alignment.LEFT)
                r.drawText(this.text, this.font, getPosition(), this.color);
            else if (this.alignment == Alignment.CENTER)
                r.drawTextCentered(this.text, this.font, getBounds(), this.color);
        }
    }
}
