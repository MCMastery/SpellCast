package com.spellcastrpg.main.objects.gui;

import com.spellcastrpg.main.geometry.Rectangle;
import com.spellcastrpg.main.rendering.Renderer;

public class GUITextContainer extends GUIContainer {
    public enum HAlignment {
        LEFT, CENTER, RIGHT
    }
    public enum VAlignment {
        TOP, CENTER, BOTTOM
    }

    private GUIText text;
    private HAlignment horizontalAlign;
    private VAlignment verticalAlign;
    private boolean wordWrap;
    private double lineSpacing;
    // should this container's size match the text's used size
    private boolean constrainWidth, constrainHeight;

    public GUITextContainer() {
        this.text = new GUIText();
        this.constrainWidth = this.constrainHeight = false;
        this.wordWrap = true;
        this.horizontalAlign = HAlignment.CENTER;
        this.verticalAlign = VAlignment.CENTER;
        this.lineSpacing = 7.5;
        setLayer(Integer.MAX_VALUE - 9);
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
    public HAlignment getHorizontalAlign() {
        return this.horizontalAlign;
    }
    public void setHorizontalAlign(HAlignment alignment) {
        this.horizontalAlign = alignment;
    }
    public VAlignment getVerticalAlign() {
        return this.verticalAlign;
    }
    public void setVerticalAlign(VAlignment alignment) {
        this.verticalAlign = alignment;
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
        updateBounds();
    }
    public boolean constrainHeight() {
        return this.constrainHeight;
    }
    public void constrainHeight(boolean constrainHeight) {
        this.constrainHeight = constrainHeight;
        updateBounds();
    }

    public void updateBounds() {
        // constrain the bounds around the text
        if (this.constrainWidth || this.constrainHeight) {
            Rectangle textBounds = this.text.getBounds();
            if (this.constrainWidth)
                setBounds(super.getBounds().setWidth(textBounds.getWidth() + getPadding() * 2));
            if (this.constrainHeight)
                setBounds(super.getBounds().setHeight(textBounds.getHeight() + getPadding() * 2));
        }
    }

    @Override
    public void render(Renderer r) {
        super.render(r);
        Rectangle textBounds = getAvailableBounds();
        this.text.setBounds(textBounds);
        if (this.wordWrap)
            r.drawTextWordWrap(this.text.getText(), this.text.getFont(), textBounds, this.horizontalAlign, this.verticalAlign, this.lineSpacing, this.text.getColor());
        else
            r.drawText(this.text.getText(), this.text.getFont(), textBounds, this.horizontalAlign, this.verticalAlign, this.text.getColor());
    }
}
