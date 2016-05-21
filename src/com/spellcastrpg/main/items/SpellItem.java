package com.spellcastrpg.main.items;

import com.spellcastrpg.main.objects.spells.SpellType;
import com.spellcastrpg.main.objects.timers.DelayTimer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SpellItem extends ItemObject {
    private final SpellType spellType;
    private Set<ItemObject> modifiers;

    public SpellItem(SpellType spellType) {
        this(spellType, new HashSet<>());
    }
    public SpellItem(SpellType spellType, ItemObject... modifiers) {
        this(spellType, new HashSet<>(Arrays.asList(modifiers)));
    }
    public SpellItem(SpellType spellType, Set<ItemObject> modifiers) {
        super(ItemType.SPELL);
        this.modifiers = modifiers;
        this.spellType = spellType;
    }

    public SpellType getSpellType() {
        return this.spellType;
    }
    public Set<ItemObject> getModifiers() {
        return this.modifiers;
    }
    public void addModifier(ItemObject modifier) {
        this.modifiers.add(modifier);
    }
    public void removeModifier(ItemObject modifier) {
        this.modifiers.remove(modifier);
    }

    @Override
    public void use() {
        setCanUse(false);
        // use delay from ItemObject class
        // 1.0 / since its uses PER SECOND, not seconds delay between uses
        new DelayTimer(1.0 / this.spellType.getUsesPerSecond()) {
            @Override
            public void run() {
                setCanUse(true);
            }
        }.init();

        this.spellType.summon(this.modifiers);
    }
}
