package com.spellcastrpg.main.objects.spells;

import com.spellcastrpg.main.items.Item;

import java.util.Set;

public interface Spell {
    void summon(Set<Item> modifiers);
}
