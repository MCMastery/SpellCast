package com.spellcastrpg.main.map;

import com.spellcastrpg.main.SpellCast;

import java.awt.image.BufferedImage;

/**
 * Created by laser_000 on 5/14/2016.
 */
public enum MapTile {
    AIR(null, false),
    GRASS("grass.png", true)
    ;

    public static final int WIDTH = 32, HEIGHT = 32;

    private final BufferedImage image;
    private final boolean rotate;

    MapTile(String image, boolean rotate) {
        this.rotate = rotate;
        if (image != null)
            this.image = SpellCast.loadImage(image);
        else
            this.image = null;
    }

    public boolean rotate() {
        return this.rotate;
    }
    public BufferedImage getImage() {
        return this.image;
    }
}
