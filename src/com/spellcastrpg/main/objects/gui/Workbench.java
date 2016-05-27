package com.spellcastrpg.main.objects.gui;

import com.spellcastrpg.main.*;
import com.spellcastrpg.main.geometry.Rectangle;
import com.spellcastrpg.main.geometry.Vector2d;
import com.spellcastrpg.main.items.ItemObject;
import com.spellcastrpg.main.items.ItemType;
import com.spellcastrpg.main.objects.spells.SpellType;
import com.spellcastrpg.main.objects.spells.SpellUtils;
import com.spellcastrpg.main.rendering.ImageUtils;
import com.spellcastrpg.main.rendering.RGBAColor;

import java.util.HashSet;
import java.util.Set;

public class Workbench extends GUIContainer {
    private ItemType base;
    private Set<ItemType> modifiers;
    private RGBAColor color;
    private GUIImage potionImage;

    public Workbench() {
        this.color = null;
        this.base = null;
        this.modifiers = new HashSet<>();
        this.potionImage = new GUIImage(128, 128);
        addChild(this.potionImage);
        updateBounds();
    }

    public GUIImage getPotionImage() {
        return this.potionImage;
    }
    public void updatePotionImage() {
        this.potionImage.setImage(SpellUtils.colorPowderSpell(this.color));
        this.potionImage.setImage(ImageUtils.setSize(this.potionImage.getImage(), 128, 128));
    }

    public RGBAColor getColor() {
        return this.color;
    }
    public ItemType getBase() {
        return this.base;
    }
    // returns whether or not the given ingredient can be used as a base ingredient
    public boolean setBase(ItemType base) {
        this.base = base;
        SpellType spellType = SpellType.fromBase(base);
        if (spellType == null) {
            this.base = null;
            return false;
        }
        updateColor();
        return true;
    }
    public Set<ItemType> getModifiers() {
        return this.modifiers;
    }
    // if there hasn't been a base ingredient added yet, or the modifier is not an ingredient, will return false
    public boolean addModifier(ItemType modifier) {
        if (this.base == null || !modifier.isIngredient())
            return false;
        this.modifiers.add(modifier);
        updateColor();
        return true;
    }

    public void updateColor() {
        if (this.base != null) {
            this.color = SpellUtils.getColor(this.base, this.modifiers);
            updatePotionImage();
        }
    }
    public void updateBounds() {
        setBounds(new Rectangle(400, 400).setCenter(SpellCast.INSTANCE.getWindowCenter()));
        this.potionImage.setCenter(getCenter());
    }

    @Override
    public void keyDown(Key key) {
        if (key == Key.E)
            SpellCast.INSTANCE.getPlayer().closeGUI();
    }
    @Override
    public void mouseDown(int button, Vector2d position) {
        if (getBounds().contains(position)) {
            ItemObject item = SpellCast.INSTANCE.getPlayer().getSelectedItem();
            if (item == null)
                return;
            if (button == Input.MOUSE_LEFT)
                addModifier(item.getType());
            else if (button == Input.MOUSE_RIGHT)
                setBase(item.getType());
        }
    }

    @Override
    public void update() {
        updateBounds();
    }
}
