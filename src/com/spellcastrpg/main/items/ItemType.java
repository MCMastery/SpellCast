package com.spellcastrpg.main.items;


import com.spellcastrpg.main.SpellCast;

import java.awt.image.BufferedImage;

public enum ItemType {
    // uses per second will be affected by the spell type
    SPELL("Spell", 0, SpellCast.loadImage("tome.png")),
    ROSEWOOD_EMBERS("Rosewood Embers", 0, SpellCast.loadImage("rosewood embers.png"));

    private final String name;
    private final double usesPerSecond;
    private final BufferedImage image;

    ItemType(String name, double usesPerSecond, BufferedImage image) {
        this.name = name;
        this.usesPerSecond = usesPerSecond;
        this.image = image;
    }

    public String getName() {
        return this.name;
    }
    public double getUsesPerSecond() {
        return this.usesPerSecond;
    }
    public BufferedImage getImage() {
        return this.image;
    }
}
