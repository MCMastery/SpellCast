package com.spellcastrpg.main.items;

import com.spellcastrpg.main.SpellCast;
import com.spellcastrpg.main.items.ingredients.Ingredient;
import com.spellcastrpg.main.objects.spells.SpellType;
import com.spellcastrpg.main.objects.spells.SpellUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SpellItem extends ItemObject {
    private final SpellType spellType;
    private final Set<Ingredient> modifiers;

    public SpellItem(SpellType spellType) {
        this(spellType, new HashSet<>());
    }
    public SpellItem(SpellType spellType, Ingredient... modifiers) {
        this(spellType, new HashSet<>(Arrays.asList(modifiers)));
    }
    public SpellItem(SpellType spellType, Set<Ingredient> modifiers) {
        super(ItemType.SPELL);
        this.modifiers = Collections.unmodifiableSet(modifiers);
        this.spellType = spellType;
        setUsesPerSecond(spellType.getUsesPerSecond());

        Item.State state = SpellUtils.getSpellState(this.modifiers);
        if (state == State.SOLID)
            setImage(SpellCast.loadImage("powder spell.png"));
        else if (state == State.LIQUID)
            setImage(SpellCast.loadImage("spell bottle.png"));
        else if (state == State.PASTE)
            setImage(SpellCast.loadImage("paste spell.png"));
    }

    public SpellType getSpellType() {
        return this.spellType;
    }
    public Set<Ingredient> getModifiers() {
        return this.modifiers;
    }

    @Override
    public void use() {
        super.use();
        this.spellType.summon(this.modifiers);
    }
}
