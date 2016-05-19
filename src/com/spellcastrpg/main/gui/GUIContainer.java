package com.spellcastrpg.main.gui;

import com.spellcastrpg.main.rendering.RGBAColor;
import com.spellcastrpg.main.rendering.Renderer;

public class GUIContainer extends GUIObject {
    public static final RGBAColor DEFAULT_BACKGROUND = new RGBAColor(0.75, 0.75, 0.75, 0.75);
    public static final RGBAColor DEFAULT_BORDER = new RGBAColor(0, 0, 0, 0.75);
    public static final double DEFAULT_RADIUS = 10;
    public static final double DEFAULT_BORDER_WEIGHT = 1;

    private RGBAColor background, border;
    private double radius, borderWeight;

    public GUIContainer() {
        this.background = DEFAULT_BACKGROUND;
        this.border = DEFAULT_BORDER;
        this.radius = DEFAULT_RADIUS;
        this.borderWeight = DEFAULT_BORDER_WEIGHT;
    }

    public RGBAColor getBackground() {
        return this.background;
    }
    public void setBackground(RGBAColor background) {
        this.background = background;
    }
    public RGBAColor getBorder() {
        return this.border;
    }
    public void setBorder(RGBAColor border) {
        this.border = border;
    }
    public double getRadius() {
        return this.radius;
    }
    public void setRadius(double radius) {
        this.radius = radius;
    }
    public double getBorderWeight() {
        return this.borderWeight;
    }
    public void setBorderWeight(double borderWeight) {
        this.borderWeight = borderWeight;
    }

    @Override
    public void render(Renderer r) {
        r.fillRoundedRect(getBounds(), this.radius, this.radius, this.background);
        r.drawRoundedRect(getBounds(), this.radius, this.radius, this.borderWeight, this.border);
    }
}
