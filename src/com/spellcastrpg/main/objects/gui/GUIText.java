package com.spellcastrpg.main.objects.gui;

import com.spellcastrpg.main.SpellCast;
import com.spellcastrpg.main.geometry.Rectangle;
import com.spellcastrpg.main.rendering.RGBAColor;
import com.spellcastrpg.main.rendering.Renderer;

import java.awt.*;

public class GUIText extends GUIObject {
    private String text;
    private Font font;
    private RGBAColor color;

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
        updateBounds();
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

    public void updateBounds() {
        getBounds();
    }
    @Override
    public Rectangle getBounds() {
        // constrain the bounds around the text
        Rectangle textBounds = SpellCast.INSTANCE.getWindow().getCanvas().getLastRenderer().getTextBounds(this.text, this.font);
        textBounds = textBounds.setPosition(getPosition());
        setBounds(textBounds);
        return super.getBounds();
    }

    @Override
    public void render(Renderer r) {
        r.drawText(this.text, this.font, getPosition(), this.color);
    }
}
