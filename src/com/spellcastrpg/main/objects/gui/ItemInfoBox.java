package com.spellcastrpg.main.objects.gui;

import com.spellcastrpg.main.items.ItemObject;
import com.spellcastrpg.main.rendering.RGBAColor;
import com.spellcastrpg.main.rendering.Renderer;


public class ItemInfoBox extends GUITextContainer {
    private ItemObject item;

    public ItemInfoBox(ItemObject item) {
        this.item = item;
        constrainWidth(true);
        constrainHeight(true);
        updateText();
    }

    public ItemObject getItem() {
        return this.item;
    }
    public void setItem(ItemObject item) {
        this.item = item;
        updateText();
    }

    public void updateText() {
        GUIText text = new GUIText(this.item.getName(), Renderer.MAIN_FONT.deriveFont(26f), RGBAColor.BLACK);
        text.setAlignment(GUIText.Alignment.CENTER);
        setText(text);
    }
}
