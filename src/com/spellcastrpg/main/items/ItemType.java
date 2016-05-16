package com.spellcastrpg.main.items;


import com.spellcastrpg.main.SpellCast;

import java.awt.image.BufferedImage;

public enum ItemType {
    // uses per second will be affected by the spell
    WAND("Wand", 0, SpellCast.loadImage("wand.png"));

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
