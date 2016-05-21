package com.spellcastrpg.main.items;


import com.spellcastrpg.main.SpellCast;

import java.awt.image.BufferedImage;

public enum ItemType implements Item {
    // uses per second and image will be affected by the spell type
    SPELL("Spell", 0, null, null),
    GLOWSTONE("Glowstone", 0, Item.State.SOLID, SpellCast.loadImage("glowstone.png")),
    CAPERHORN_LEAF("Caperhorn Leaf", 0, Item.State.SOLID, SpellCast.loadImage("caperhorn leaf.png")),
    GANCKLE_TREE_NUT("Ganckle Tree Nut", 0, Item.State.SOLID, SpellCast.loadImage("ganckle tree nut.png")),
    MANAGOT_ROOT("Managot Root", 0, Item.State.SOLID, SpellCast.loadImage("managot root.png")),
    GOBER_FISH_EYE("Gober Fish Eye", 0, Item.State.SOLID, SpellCast.loadImage("gober fish eye.png")),
    TOME("Tome", 0, Item.State.SOLID, SpellCast.loadImage("tome.png")),
    PHOENIX_FEATHER("Phoenix Feather", 0, Item.State.SOLID, SpellCast.loadImage("phoenix feather.png")),
    QUATASIUM_JADE("Quatasium Jade", 0, Item.State.SOLID, SpellCast.loadImage("quatasium jade.png")),
    ROSEWOOD_EMBERS("Rosewood Embers", 0, Item.State.SOLID, SpellCast.loadImage("rosewood embers.png"));

    private final String name;
    private final double usesPerSecond;
    private final Item.State state;
    private final BufferedImage image;

    ItemType(String name, double usesPerSecond, Item.State state, BufferedImage image) {
        this.name = name;
        this.usesPerSecond = usesPerSecond;
        this.state = state;
        this.image = image;
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

    // we dont need this method.
    @Override
    public void use() {}
}
