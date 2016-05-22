package com.spellcastrpg.main.objects.gui;

import com.spellcastrpg.main.*;
import com.spellcastrpg.main.geometry.Rectangle;
import com.spellcastrpg.main.geometry.Vector2d;
import com.spellcastrpg.main.items.ItemObject;
import com.spellcastrpg.main.items.ItemType;
import com.spellcastrpg.main.objects.spells.SpellType;
import com.spellcastrpg.main.objects.spells.SpellUtils;
import com.spellcastrpg.main.rendering.RGBAColor;
import com.spellcastrpg.main.rendering.Renderer;

import java.util.HashSet;
import java.util.Set;

public class Workbench extends GUIContainer {
    private ItemType base;
    private Set<ItemType> modifiers;
    private RGBAColor color;

    public Workbench() {
        this.color = RGBAColor.TRANSPARENT;
        this.base = null;
        this.modifiers = new HashSet<>();
        setAnchor(new Anchor(IAnchor.HAnchor.CENTER, IAnchor.VAnchor.CENTER));
        setBounds(new Rectangle(SpellCast.INSTANCE.getWindowCenter(), 512, 512));
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
    // if there hasn't been a base ingredient added yet, will return false
    public boolean addModifier(ItemType modifier) {
        if (this.base == null)
            return false;
        this.modifiers.add(modifier);
        updateColor();
        return true;
    }

    public void updateColor() {
        if (this.base != null)
            this.color = SpellUtils.getColor(this.base, this.modifiers);
    }

    @Override
    public void keyDown(Key key) {
        if (key == Key.E) {
            destroy();
            SpellCast.INSTANCE.getPlayer().closeGUI();
        }
    }
    @Override
    public void mouseDown(int button, Vector2d position) {
        if (button == Input.MOUSE_RIGHT) {
            ItemObject modifier = SpellCast.INSTANCE.getPlayer().getSelectedItem();
            if (modifier == null)
                return;
            addModifier(modifier.getType());
        }
    }

    @Override
    public void render(Renderer r) {
        super.render(r);

    }
}
