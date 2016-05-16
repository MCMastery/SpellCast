package com.spellcastrpg.main.objects.spells;

import com.spellcastrpg.main.items.Item;
import com.spellcastrpg.main.objects.LivingObject;
import com.spellcastrpg.main.rendering.Renderer;

import java.util.Set;

public abstract class SpellObject extends LivingObject implements Spell {
    private Set<Item> modifiers;

    public SpellObject() {
        this.modifiers = null;
    }

    public Set<Item> getModifiers() {
        return this.modifiers;
    }


    public void summon(Set<Item> modifiers) {
        this.modifiers = modifiers;
        init();
    }

    // don't draw "health" - spells don't have health
    @Override
    public void render(Renderer r) {

    }
}
