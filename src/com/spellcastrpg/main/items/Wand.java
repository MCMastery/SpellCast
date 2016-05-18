package com.spellcastrpg.main.items;

import com.spellcastrpg.main.objects.spells.SpellType;
import com.spellcastrpg.main.objects.timers.DelayTimer;

import java.util.Collections;
import java.util.Set;

public class Wand extends ItemObject {
    private final SpellType spell;
    private Set<ItemObject> modifiers;

    public Wand(SpellType spell, Set<ItemObject> modifiers) {
        super(ItemType.WAND);
        this.spell = spell;
        this.modifiers = Collections.unmodifiableSet(modifiers);
    }

    @Override
    public void use() {
        super.use();
        setCanUse(false);
        // 1.0 / since its uses PER SECOND, not seconds delay between uses
        new DelayTimer(1.0 / this.spell.getUsesPerSecond()) {
            @Override
            public void run() {
                setCanUse(true);
            }
        }.init();
        this.spell.summon(this.modifiers);
    }
}
