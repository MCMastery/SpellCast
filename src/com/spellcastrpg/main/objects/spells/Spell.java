package com.spellcastrpg.main.objects.spells;

import com.spellcastrpg.main.items.ItemObject;

import java.util.Set;

public interface Spell {
    void summon(Set<ItemObject> modifiers);
}
