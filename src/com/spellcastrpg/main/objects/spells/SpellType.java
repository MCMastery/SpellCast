package com.spellcastrpg.main.objects.spells;

import com.spellcastrpg.main.items.Item;
import com.spellcastrpg.main.items.ItemObject;

import java.util.Set;

public enum SpellType implements Spell {
    WIND(0.5, null) {
        @Override
        public ItemObject getItem() {
            return null;
        }
        @Override
        public void summon(Set<ItemObject> modifiers) {
            new WindSpell().summon(modifiers);
        }
    };

    private final double usesPerSecond;
    private final Item base;

    SpellType(double usesPerSecond, Item base) {
        this.usesPerSecond = usesPerSecond;
        this.base = base;
    }

    public abstract ItemObject getItem();
    public double getUsesPerSecond() {
        return this.usesPerSecond;
    }
    public Item getBase() {
        return this.base;
    }
}
