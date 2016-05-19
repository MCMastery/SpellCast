package com.spellcastrpg.main.objects;

import com.spellcastrpg.main.Input;
import com.spellcastrpg.main.SpellCast;
import com.spellcastrpg.main.geometry.Rectangle;
import com.spellcastrpg.main.geometry.Vector2d;
import com.spellcastrpg.main.items.ItemObject;
import com.spellcastrpg.main.rendering.RGBAColor;
import com.spellcastrpg.main.rendering.Renderer;

import java.util.HashMap;
import java.util.Map;

public class Inventory extends GameObject {
    private Map<Integer, ItemObject> contents;
    private int selection, size;
    private RGBAColor background, border, selectionColor;
    private double radius, borderWeight;

    public Inventory() {
        this.contents = new HashMap<>();
        this.selection = 0;
        this.size = 10;
        this.background = new RGBAColor(0.75, 0.75, 0.75, 0.75);
        this.border = new RGBAColor(0, 0, 0, 0.75);
        this.selectionColor = new RGBAColor(0, 1, 0, 0.25);
        this.borderWeight = 1;
        this.radius = 10;

        updateBounds();
        setFollowCamera(true);
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


    public RGBAColor getBackground() {
        return this.background;
    }
    public void setBackground(RGBAColor background) {
        this.background = background;
    }
    public double getRadius() {
        return this.radius;
    }
    public void setRadius(double radius) {
        this.radius = radius;
    }

    public RGBAColor getBorder() {
        return this.border;
    }
    public void setBorder(RGBAColor border) {
        this.border = border;
    }
    public double getBorderWeight() {
        return this.borderWeight;
    }
    public void setBorderWeight(double borderWeight) {
        this.borderWeight = borderWeight;
    }

    public RGBAColor getSelectionColor() {
        return this.selectionColor;
    }
    public void setSelectionColor(RGBAColor selectionColor) {
        this.selectionColor = selectionColor;
    }


    public void updateBounds() {
        Rectangle bounds = new Rectangle(0, 0, this.size * 64, 64)
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
        super.update();
    }

    @Override
    public void mouseScroll(double amount) {
        setSelection(this.selection + (int) Math.round(amount));
    }

    @Override
    public void render(Renderer r) {
        r.fillRoundedRect(getBounds(), this.radius, this.radius, this.background);
        Vector2d selectionPos = getBounds().getPosition().add(this.selection * 64, 0);
        for (int slot = 0; slot < this.size; slot++) {
            ItemObject item = getItem(slot);
            if (item == null || item.getType().getImage() == null)
                continue;
            r.drawRoundedImage(item.getType().getImage(), this.radius, this.radius, new Vector2d(getBounds().getX() + slot * 64, getBounds().getY()));
        }
        r.fillRoundedRect(new Rectangle(selectionPos, 64, 64), this.radius, this.radius, this.selectionColor);
        r.drawRoundedRect(getBounds(), this.radius, this.radius, this.borderWeight, this.border);
    }
}
