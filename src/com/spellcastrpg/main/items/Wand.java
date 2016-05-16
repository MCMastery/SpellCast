package com.spellcastrpg.main.items;

import com.spellcastrpg.main.objects.spells.SpellType;

import java.util.Collections;
import java.util.Set;

public class Wand extends ItemObject {
    private final SpellType spell;
    private Set<Item> modifiers;

    public Wand(SpellType spell, Set<Item> modifiers) {
        super(ItemType.WAND);
        this.spell = spell;
        this.modifiers = Collections.unmodifiableSet(modifiers);
    }

    @Override
    public void use() {
        super.use();
        setCanUse(false);
        new Thread(() -> {
            try {
                Thread.sleep((long) (1000 / this.spell.getUsesPerSecond()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            setCanUse(true);
        }).start();
        this.spell.summon(this.modifiers);
    }
}
