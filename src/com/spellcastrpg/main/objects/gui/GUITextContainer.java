package com.spellcastrpg.main.objects.gui;

import com.spellcastrpg.main.SpellCast;
import com.spellcastrpg.main.geometry.Rectangle;
import com.spellcastrpg.main.rendering.Renderer;

public class GUITextContainer extends GUIContainer {
    private GUIText text;
    // should this container's size match the text's used size
    private boolean constrainWidth, constrainHeight;

    public GUITextContainer() {
        this.text = new GUIText();
        this.constrainWidth = this.constrainHeight = false;
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
            Rectangle textBounds = SpellCast.INSTANCE.getWindow().getCanvas().getLastRenderer().getTextBounds(this.text.getText(), this.text.getFont());
            textBounds = textBounds.setPosition(getPosition());
            // add padding
            textBounds = textBounds.expand(getPadding() * 2, getPadding() * 2);
            // offset position because of padding
            textBounds = textBounds.translate(getPadding(), getPadding());
            if (this.constrainWidth)
                setBounds(super.getBounds().setWidth(textBounds.getWidth()));
            if (this.constrainHeight)
                setBounds(super.getBounds().setHeight(textBounds.getHeight()));
        }
        return super.getBounds();
    }

    @Override
    public void render(Renderer r) {
        super.render(r);
        Rectangle textBounds = getAvailableBounds();
        this.text.setBounds(textBounds);
        this.text.render(r);
    }
}
