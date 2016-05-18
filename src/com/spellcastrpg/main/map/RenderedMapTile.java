package com.spellcastrpg.main.map;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Created by laser_000 on 5/14/2016.
 */
public class RenderedMapTile {
    private MapTile tile;
    private int rotation;
    private BufferedImage rotatedImage;

    public RenderedMapTile(MapTile tile) {
        setTile(tile);
    }

    public MapTile getTile() {
        return this.tile;
    }
    public void setTile(MapTile tile) {
        this.tile = tile;
        if (tile.rotate())
            setRotation(new Random().nextInt(4) * 90);
        else
            setRotation(0);
    }
    public BufferedImage getRotatedImage() {
        return this.rotatedImage;
    }
    public int getRotation() {
        return this.rotation;
    }
    public void setRotation(int rotation) {
        this.rotation = rotation;
        if (this.tile.rotate() && this.tile.getImage() != null) {
            AffineTransform transform = new AffineTransform();
            transform.rotate(Math.toRadians(this.rotation), this.tile.getImage().getWidth() / 2, this.tile.getImage().getHeight() / 2);
            AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
            this.rotatedImage = op.filter(this.tile.getImage(), null);
        } else
            this.rotatedImage = null;
    }
}
