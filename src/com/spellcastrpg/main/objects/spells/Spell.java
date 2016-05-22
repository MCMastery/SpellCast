package com.spellcastrpg.main.objects.spells;

import com.spellcastrpg.main.items.ingredients.Ingredient;

import java.util.Set;

public interface Spell {
    void summon(Set<Ingredient> modifiers);
}
