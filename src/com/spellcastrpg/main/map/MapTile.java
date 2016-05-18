package com.spellcastrpg.main.map;

import com.spellcastrpg.main.SpellCast;

import java.awt.image.BufferedImage;

/**
 * Created by laser_000 on 5/14/2016.
 */
public enum MapTile {
    AIR(null, false, false),
    GRASS("grass.png", true, false),
    STONE("stone.png", true, true)
    ;

    public static final int WIDTH = 32, HEIGHT = 32;

    private final BufferedImage image;
    private final boolean rotate, solid;

    MapTile(String image, boolean rotate, boolean solid) {
        this.rotate = rotate;
        this.solid = solid;
        if (image != null)
            this.image = SpellCast.loadImage(image);
        else
            this.image = null;
    }

    public boolean rotate() {
        return this.rotate;
    }
    public boolean isSolid() {
        return this.solid;
    }
    public BufferedImage getImage() {
        return this.image;
    }
}
