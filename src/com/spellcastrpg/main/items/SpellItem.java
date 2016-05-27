package com.spellcastrpg.main.items;

import com.spellcastrpg.main.SpellCast;
import com.spellcastrpg.main.objects.spells.SpellType;
import com.spellcastrpg.main.objects.spells.SpellUtils;
import com.spellcastrpg.main.rendering.RGBAColor;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SpellItem extends ItemObject {
    public static final BufferedImage POWDER_SPELL = SpellCast.loadImage("powder spell.png");
    public static final BufferedImage SPELL_BOTTLE = SpellCast.loadImage("spell bottle.png");
    public static final BufferedImage PASTE_SPELL = SpellCast.loadImage("paste spell.png");
    private final SpellType spellType;
    private final Set<ItemType> modifiers;

    public SpellItem(SpellType spellType) {
        this(spellType, new HashSet<>());
    }
    public SpellItem(SpellType spellType, ItemType... modifiers) {
        this(spellType, new HashSet<>(Arrays.asList(modifiers)));
    }
    public SpellItem(SpellType spellType, Set<ItemType> modifiers) {
        super(ItemType.SPELL);
        this.modifiers = Collections.unmodifiableSet(modifiers);
        this.spellType = spellType;
        setUsesPerSecond(spellType.getUsesPerSecond());

        RGBAColor color = SpellUtils.getColor(this.spellType.getBase(), this.modifiers);

        Item.State state = SpellUtils.getSpellState(this.modifiers);
        if (state == State.SOLID)
            setImage(SpellUtils.colorPowderSpell(color));
        else if (state == State.LIQUID)
            setImage(SPELL_BOTTLE);
        else if (state == State.PASTE)
            setImage(PASTE_SPELL);
    }

    public SpellType getSpellType() {
        return this.spellType;
    }
    public Set<ItemType> getModifiers() {
        return this.modifiers;
    }

    @Override
    public void use() {
        super.use();
        this.spellType.summon(this.modifiers);
    }
}
