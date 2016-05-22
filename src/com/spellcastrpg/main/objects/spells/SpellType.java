package com.spellcastrpg.main.objects.spells;

import com.spellcastrpg.main.items.Item;
import com.spellcastrpg.main.items.ItemObject;
import com.spellcastrpg.main.items.ItemType;
import com.spellcastrpg.main.items.ingredients.Ingredient;
import com.spellcastrpg.main.rendering.RGBAColor;
import com.spellcastrpg.main.rendering.Renderer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public enum SpellType implements Spell {
    WIND(0.5, null) {
        @Override
        public ItemObject getItem() {
            return null;
        }
        @Override
        public SpellObject getSpell() {
            return new WindSpell();
        }
    };

    private final double usesPerSecond;
    private final ItemType base;

    SpellType(double usesPerSecond, ItemType base) {
        this.usesPerSecond = usesPerSecond;
        this.base = base;
    }

    public abstract ItemObject getItem();
    public abstract SpellObject getSpell();

    @Override
    public void summon(Set<Ingredient> modifiers) {
        getSpell().summon(modifiers);
    }

    public double getUsesPerSecond() {
        return this.usesPerSecond;
    }
    public Item getBase() {
        return this.base;
    }

    public static SpellType fromBase(ItemType base) {
        for (SpellType type : values())
            if (type.getBase() == base)
                return type;
        return null;
    }
}
