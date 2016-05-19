package com.spellcastrpg.main.objects.spells;

import com.spellcastrpg.main.items.ItemObject;
import com.spellcastrpg.main.items.ItemType;
import com.spellcastrpg.main.objects.LivingObject;
import com.spellcastrpg.main.rendering.Renderer;

import java.util.Set;

public abstract class SpellObject extends LivingObject implements Spell {
    private Set<ItemObject> modifiers;

    public SpellObject() {
        this.modifiers = null;
    }

    public Set<ItemObject> getModifiers() {
        return this.modifiers;
    }
    public boolean containsModifierType(ItemType type) {
        for (ItemObject item : this.modifiers)
            if (item.getType() == type)
                return true;
        return false;
    }

    @Override
    public void summon(Set<ItemObject> modifiers) {
        this.modifiers = modifiers;
        init();
    }

    // don't draw "health" - spells don't have health
    @Override
    public void render(Renderer r) {

    }
}
