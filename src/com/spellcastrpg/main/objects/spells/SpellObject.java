package com.spellcastrpg.main.objects.spells;

import com.spellcastrpg.main.items.ItemType;
import com.spellcastrpg.main.objects.LivingObject;
import com.spellcastrpg.main.rendering.Renderer;

import java.util.Set;

public class SpellObject extends LivingObject implements Spell {
    private Set<ItemType> modifiers;

    public SpellObject() {
        this.modifiers = null;
    }

    public Set<ItemType> getModifiers() {
        return this.modifiers;
    }
    public boolean containsModifierType(ItemType type) {
        for (ItemType item : this.modifiers)
            if (item == type)
                return true;
        return false;
    }

    @Override
    public void summon(Set<ItemType> modifiers) {
        this.modifiers = modifiers;
        init();
    }

    // don't draw "health" - spells don't have health
    @Override
    public void render(Renderer r) {

    }
}
