package com.spellcastrpg.main.objects.gui;

import com.spellcastrpg.main.Input;
import com.spellcastrpg.main.SpellCast;
import com.spellcastrpg.main.geometry.Rectangle;
import com.spellcastrpg.main.geometry.Vector2d;
import com.spellcastrpg.main.items.ItemObject;
import com.spellcastrpg.main.rendering.RGBAColor;
import com.spellcastrpg.main.rendering.Renderer;

import java.util.HashMap;
import java.util.Map;

public class Inventory extends GUIContainer {
    private Map<Integer, ItemObject> contents;
    private int selection, size;
    private RGBAColor  selectionColor;

    public Inventory() {
        this.contents = new HashMap<>();
        this.selection = 0;
        this.size = 10;
        this.selectionColor = new RGBAColor(0, 1, 0, 0.25);
        updateBounds();
    }

    public Map<Integer, ItemObject> getContents() {
        return this.contents;
    }
    public ItemObject getItem(int slot) {
        if (slot < 0 || slot >= this.size)
            return null;
        return this.contents.get(slot);
    }
    public boolean addItem(ItemObject item) {
        for (int slot = 0; slot < this.size; slot++) {
            if (this.contents.get(slot) == null) {
                this.contents.put(slot, item);
                return true;
            }
        }
        return false;
    }
    public ItemObject removeItem(int slot) {
        return this.contents.remove(slot);
    }
    public boolean setItem(int slot, ItemObject item) {
        if (slot < 0 || slot >= this.size)
            return false;
        removeItem(slot);
        this.contents.put(slot, item);
        return true;
    }

    public int getSize() {
        return this.size;
    }
    public void setSize(int size) {
        this.size = size;
    }

    public int getSelection() {
        return this.selection;
    }
    public void setSelection(int selection) {
        while (selection < 0)
            selection = this.size + selection;
        while (selection >= this.size)
            selection = Math.abs(this.size - selection);
        this.selection = selection;
    }
    public ItemObject getSelectedItem() {
        return getItem(this.selection);
    }
    

    public RGBAColor getSelectionColor() {
        return this.selectionColor;
    }
    public void setSelectionColor(RGBAColor selectionColor) {
        this.selectionColor = selectionColor;
    }


    public void updateBounds() {
        Rectangle bounds = new Rectangle(0, 0, this.size * ItemObject.SIZE, ItemObject.SIZE)
                .setCenter(SpellCast.INSTANCE.getWindowCenter())
                .setY(0);
        setBounds(bounds);
    }


    @Override
    public void update() {
        updateBounds();
        ItemObject selectedItem = getSelectedItem();
        if (selectedItem != null && selectedItem.canUse() && Input.INSTANCE.mouseDown(Input.MOUSE_LEFT))
            selectedItem.use();
    }

    @Override
    public void mouseScroll(double amount) {
        setSelection(this.selection + (int) Math.round(amount));
    }

    @Override
    public void render(Renderer r) {
        // don't render the GUIContainer - we want the border to be rendered on top.
        r.fillRoundedRect(getBounds(), getRadius(), getRadius(), getBackground());
        Vector2d selectionPos = getBounds().getPosition().add(this.selection * ItemObject.SIZE, 0);
        for (int slot = 0; slot < this.size; slot++) {
            ItemObject item = getItem(slot);
            if (item == null || item.getType().getImage() == null)
                continue;
            r.drawRoundedImage(item.getType().getImage(), getRadius(), getRadius(), new Vector2d(getBounds().getX() + slot * ItemObject.SIZE, getBounds().getY()));
        }
        r.fillRoundedRect(new Rectangle(selectionPos, ItemObject.SIZE, ItemObject.SIZE), getRadius(), getRadius(), this.selectionColor);
        r.drawRoundedRect(getBounds(), getRadius(), getRadius(), getBorderWeight(), getBorder());
    }
}
