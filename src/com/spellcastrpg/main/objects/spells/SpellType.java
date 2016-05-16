package com.spellcastrpg.main.objects.spells;

import com.spellcastrpg.main.items.Item;

import java.util.Set;

public enum SpellType implements Spell {
    WIND(0.5, null) {
        @Override
        public void summon(Set<Item> modifiers) {
            new WindSpell().summon(modifiers);
        }
    };

    private final double usesPerSecond;
    private final Item base;

    SpellType(double usesPerSecond, Item base) {
        this.usesPerSecond = usesPerSecond;
        this.base = base;
    }

    public double getUsesPerSecond() {
        return this.usesPerSecond;
    }
    public Item getBase() {
        return this.base;
    }
}
