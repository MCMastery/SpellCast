package com.spellcastrpg.main.objects.spells;

import com.spellcastrpg.main.items.Item;
import com.spellcastrpg.main.items.ItemObject;
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

    public static RGBAColor getColor(Set<ItemObject> modifiers) {
        List<RGBAColor> colors = new ArrayList<>();
        for (ItemObject modifier : modifiers)
            colors.add(Renderer.getAverageColor(modifier.getImage()));
        return RGBAColor.average(colors);
    }
    // solid if it should be powdered spell
    // liquid if it should be in a bowl or whatever
    public static Item.State getSpellState(Set<ItemObject> modifiers) {
        for (ItemObject modifier : modifiers)
            if (modifier.getState() == Item.State.LIQUID)
                return Item.State.LIQUID;
        return Item.State.SOLID;
    }
}
