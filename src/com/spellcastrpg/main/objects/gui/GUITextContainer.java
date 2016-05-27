package com.spellcastrpg.main.objects.gui;

import com.spellcastrpg.main.SpellCast;
import com.spellcastrpg.main.geometry.Rectangle;
import com.spellcastrpg.main.rendering.Renderer;

public class GUITextContainer extends GUIContainer {
    private GUIText text;
    // should this container's size match the text's used size
    private boolean constrain;

    public GUITextContainer() {
        this.text = new GUIText();
        this.constrain = false;
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
    }
    public boolean isConstrained() {
        return this.constrain;
    }
    public void setConstrain(boolean constrain) {
        if (!this.constrain && constrain)
            updateBounds();
        this.constrain = constrain;
    }

    public Rectangle getTextBounds() {
        return getBounds().expand(-getPadding() * 2, -getPadding() * 2);
    }
    public void updateBounds() {
        // constrain the bounds around the text
        if (this.constrain) {
            Rectangle textBounds = SpellCast.INSTANCE.getWindow().getCanvas().getLastRenderer().getTextBounds(this.text.getText(), this.text.getFont());
            textBounds = textBounds.setPosition(getPosition());
            // add padding
            textBounds = textBounds.expand(getPadding() * 2, getPadding() * 2);
            // offset position because of padding
            textBounds = textBounds.translate(getPadding(), getPadding());
            setBounds(textBounds);
        }
    }

    @Override
    public void render(Renderer r) {
        super.render(r);
        System.out.println();
        Rectangle textBounds = getTextBounds();
        this.text.setBounds(textBounds);
        this.text.render(r);
    }
}
