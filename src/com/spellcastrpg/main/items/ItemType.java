package com.spellcastrpg.main.items;


import com.spellcastrpg.main.SpellCast;

import java.awt.image.BufferedImage;

public enum ItemType implements Item {
    // uses per second and image will be affected by the spell type
    SPELL("Spell", 0, null, false, null),
    GLOWSTONE("Glowstone", 0, Item.State.SOLID, true, SpellCast.loadImage("glowstone.png")),
    CAPERHORN_LEAF("Caperhorn Leaf", 0, Item.State.SOLID, true, SpellCast.loadImage("caperhorn leaf.png")),
    GANCKLE_TREE_NUT("Ganckle Tree Nut", 0, Item.State.SOLID, true, SpellCast.loadImage("ganckle tree nut.png")),
    MANAGOT_ROOT("Managot Root", 0, Item.State.SOLID, true, SpellCast.loadImage("managot root.png")),
    GOBER_FISH_EYE("Gober Fish Eye", 0, Item.State.SOLID, true, SpellCast.loadImage("gober fish eye.png")),
    TOME("Tome", 0, Item.State.SOLID, false, SpellCast.loadImage("tome.png")),
    PHOENIX_FEATHER("Phoenix Feather", 0, Item.State.SOLID, true, SpellCast.loadImage("phoenix feather.png")),
    QUATASIUM_JADE("Quatasium Jade", 0, Item.State.SOLID, true, SpellCast.loadImage("quatasium jade.png")),
    ROSEWOOD_EMBERS("Rosewood Embers", 0, Item.State.SOLID, true, SpellCast.loadImage("rosewood embers.png"));

    private final String name;
    private final double usesPerSecond;
    private final Item.State state;
    private final boolean isIngredient;
    private final BufferedImage image;

    ItemType(String name, double usesPerSecond, Item.State state, boolean isIngredient, BufferedImage image) {
        this.name = name;
        this.usesPerSecond = usesPerSecond;
        this.state = state;
        this.image = image;
        this.isIngredient = isIngredient;
    }

    @Override
    public String getName() {
        return this.name;
    }
    @Override
    public double getUsesPerSecond() {
        return this.usesPerSecond;
    }
    @Override
    public Item.State getState() {
        return this.state;
    }
    @Override
    public BufferedImage getImage() {
        return this.image;
    }
    public boolean isIngredient() {
        return this.isIngredient;
    }

    // we dont need this method.
    @Override
    public void use() {}
}
