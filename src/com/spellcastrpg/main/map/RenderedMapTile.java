package com.spellcastrpg.main.map;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Created by laser_000 on 5/14/2016.
 */
public class RenderedMapTile {
    private final MapTile tile;
    private final int rotation;
    private final BufferedImage rotatedImage;

    public RenderedMapTile(MapTile tile) {
        this.tile = tile;
        if (this.tile.rotate() && this.tile.getImage() != null) {
            this.rotation = new Random().nextInt(4) * 90;
            AffineTransform transform = new AffineTransform();
            transform.rotate(Math.toRadians(this.rotation), this.tile.getImage().getWidth() / 2, this.tile.getImage().getHeight() / 2);
            AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
            this.rotatedImage = op.filter(this.tile.getImage(), null);
        } else {
            this.rotation = 0;
            this.rotatedImage = null;
        }
    }

    public MapTile getTile() {
        return this.tile;
    }
    public BufferedImage getRotatedImage() {
        return this.rotatedImage;
    }
    public int getRotation() {
        return this.rotation;
    }
}
