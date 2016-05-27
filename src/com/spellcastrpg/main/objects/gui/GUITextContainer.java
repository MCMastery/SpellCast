package com.spellcastrpg.main.objects.gui;

import com.spellcastrpg.main.geometry.Rectangle;
import com.spellcastrpg.main.rendering.Renderer;

public class GUITextContainer extends GUIContainer {
    public enum HAlignment {
        LEFT, CENTER, RIGHT
    }

    private GUIText text;
    private HAlignment alignment;
    private boolean wordWrap;
    private double lineSpacing;
    // should this container's size match the text's used size
    private boolean constrainWidth, constrainHeight;

    public GUITextContainer() {
        this.text = new GUIText();
        this.constrainWidth = this.constrainHeight = false;
        this.wordWrap = true;
        this.alignment = HAlignment.CENTER;
        this.lineSpacing = 7.5;
    }

    public GUIText getText() {
        return this.text;
    }
    public void setText(GUIText text) {
        this.text = text;
        updateBounds();
    }
    public String getTextValue() {
        return this.text.getText();
    }
    public void setTextValue(String text) {
        this.text.setText(text);
        updateBounds();
    }
    public HAlignment getAlignment() {
        return this.alignment;
    }
    public void setAlignment(HAlignment alignment) {
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
    public boolean constrainWidth() {
        return this.constrainWidth;
    }
    public void constrainWidth(boolean constrainWidth) {
        this.constrainWidth = constrainWidth;
    }
    public boolean constrainHeight() {
        return this.constrainHeight;
    }
    public void constrainHeight(boolean constrainHeight) {
        this.constrainHeight = constrainHeight;
    }

    public void updateBounds() {
        getBounds();
    }
    @Override
    public Rectangle getBounds() {
        // constrain the bounds around the text
        if (this.constrainWidth || this.constrainHeight) {
            Rectangle textBounds = this.text.getBounds();
            if (this.constrainWidth)
                setBounds(super.getBounds().setWidth(textBounds.getWidth() + getPadding() * 2));
            if (this.constrainHeight)
                setBounds(super.getBounds().setHeight(textBounds.getHeight() + getPadding() * 2));
        }
        return super.getBounds();
    }

    @Override
    public void render(Renderer r) {
        super.render(r);
        Rectangle textBounds = getAvailableBounds();
        this.text.setBounds(textBounds);
        if (this.wordWrap)
            r.drawTextWordWrap(this.text.getText(), this.text.getFont(), textBounds, this.alignment, this.lineSpacing, this.text.getColor());
        else
            r.drawText(this.text.getText(), this.text.getFont(), textBounds, this.alignment, this.text.getColor());
    }
}
