package com.spellcastrpg.main.gui;

import com.spellcastrpg.main.geometry.Rectangle;
import com.spellcastrpg.main.rendering.Renderer;

public class GUITextContainer extends GUIContainer {
    public static final double DEFAULT_PADDING = 10;

    private GUIText text;
    private double padding;

    public GUITextContainer() {
        this.text = new GUIText();
        this.padding = DEFAULT_PADDING;
    }

    public GUIText getText() {
        return this.text;
    }
    public void setText(GUIText text) {
        this.text = text;
    }
    public String getTextValue() {
        return this.text.getText();
    }
    public void setTextValue(String text) {
        this.text.setText(text);
    }
    public double getPadding() {
        return this.padding;
    }
    public void setPadding(double padding) {
        this.padding = padding;
    }

    public Rectangle getTextBounds() {
        return getBounds().expand(-this.padding * 2, -this.padding * 2);
    }

    @Override
    public void render(Renderer r) {
        super.render(r);
        Rectangle textBounds = getTextBounds();
        this.text.setBounds(textBounds);
        this.text.render(r);
    }
}
