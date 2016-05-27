package com.spellcastrpg.main.objects.gui;

import com.spellcastrpg.main.geometry.Rectangle;
import com.spellcastrpg.main.rendering.Renderer;

import java.awt.image.BufferedImage;

public class GUIImage extends GUIObject {
    public static final double DEFAULT_RADIUS = 10;

    private BufferedImage image;
    private double radius;

    public GUIImage() {
        this(0, 0);
    }
    public GUIImage(int width, int height) {
        setImage(new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB));
        this.radius = DEFAULT_RADIUS;
    }

    public BufferedImage getImage() {
        return this.image;
    }
    public void setImage(BufferedImage image) {
        this.image = image;
        setBounds(new Rectangle(getPosition(), image.getWidth(), image.getHeight()));
    }
    public double getRadius() {
        return this.radius;
    }
    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public void render(Renderer r) {
        if (this.image != null)
            r.drawRoundedImage(this.image, this.radius, this.radius, getPosition());
    }
}
