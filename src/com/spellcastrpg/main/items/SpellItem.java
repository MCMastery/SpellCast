package com.spellcastrpg.main.items;

import com.spellcastrpg.main.SpellCast;
import com.spellcastrpg.main.objects.spells.SpellType;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SpellItem extends ItemObject {
    private final SpellType spellType;
    private final Set<ItemObject> modifiers;

    public SpellItem(SpellType spellType) {
        this(spellType, new HashSet<>());
    }
    public SpellItem(SpellType spellType, ItemObject... modifiers) {
        this(spellType, new HashSet<>(Arrays.asList(modifiers)));
    }
    public SpellItem(SpellType spellType, Set<ItemObject> modifiers) {
        super(ItemType.SPELL);
        this.modifiers = Collections.unmodifiableSet(modifiers);
        this.spellType = spellType;
        setUsesPerSecond(spellType.getUsesPerSecond());

        Item.State state = SpellType.getSpellState(this.modifiers);
        if (state == State.SOLID)
            setImage(SpellCast.loadImage("powder spell.png"));
        else if (state == State.LIQUID)
            setImage(SpellCast.loadImage("potion bottle.png"));
    }

    public SpellType getSpellType() {
        return this.spellType;
    }
    public Set<ItemObject> getModifiers() {
        return this.modifiers;
    }

    @Override
    public void use() {
        super.use();
        this.spellType.summon(this.modifiers);
    }
}
