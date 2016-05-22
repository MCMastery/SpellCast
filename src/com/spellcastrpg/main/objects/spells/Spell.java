package com.spellcastrpg.main.objects.spells;

import com.spellcastrpg.main.items.ItemType;

import java.util.Set;

public interface Spell {
    void summon(Set<ItemType> modifiers);
}
