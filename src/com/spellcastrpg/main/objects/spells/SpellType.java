package com.spellcastrpg.main.objects.spells;

import com.spellcastrpg.main.items.ItemType;

import java.util.Set;

public enum SpellType implements Spell {
    WIND(0.5, ItemType.CAPERHORN_LEAF) {
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

    public abstract SpellObject getSpell();

    @Override
    public void summon(Set<ItemType> modifiers) {
        getSpell().summon(modifiers);
    }

    public double getUsesPerSecond() {
        return this.usesPerSecond;
    }
    public ItemType getBase() {
        return this.base;
    }

    public static SpellType fromBase(ItemType base) {
        for (SpellType type : values())
            if (type.getBase() == base)
                return type;
        return null;
    }
}
