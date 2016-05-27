package com.spellcastrpg.main.objects.gui;

import com.spellcastrpg.main.objects.gui.layout.GUILayout;
import com.spellcastrpg.main.objects.gui.layout.ManualLayout;
import com.spellcastrpg.main.rendering.RGBAColor;
import com.spellcastrpg.main.rendering.Renderer;

import java.util.HashSet;
import java.util.Set;

public class GUIContainer extends GUIObject {
    public static final RGBAColor DEFAULT_BACKGROUND = new RGBAColor(0.75, 0.75, 0.75, 0.85);
    public static final RGBAColor DEFAULT_BORDER = new RGBAColor(0, 0, 0, 0.85);
    public static final double DEFAULT_PADDING = 10;
    public static final double DEFAULT_RADIUS = 10;
    public static final double DEFAULT_BORDER_WEIGHT = 1;

    private Set<GUIObject> children;
    private GUILayout layout;
    private RGBAColor background, border;
    private double radius, borderWeight, padding;

    public GUIContainer() {
        this.background = DEFAULT_BACKGROUND;
        this.border = DEFAULT_BORDER;
        this.radius = DEFAULT_RADIUS;
        this.borderWeight = DEFAULT_BORDER_WEIGHT;
        this.padding = DEFAULT_PADDING;
        this.children = new HashSet<>();
        this.layout = new ManualLayout();
        setLayer(Integer.MAX_VALUE - 10);
    }

    @Override
    public void init() {
        super.init();
        for (GUIObject child : this.children)
            child.init();
    }
    @Override
    public void destroy() {
        super.destroy();
        for (GUIObject child : this.children)
            child.destroy();
    }

    public void addChild(GUIObject child) {
        this.children.add(child);
        arrange();
    }
    public void removeChild(GUIObject child) {
        this.children.remove(child);
        arrange();
    }
    public GUILayout getLayout() {
        return this.layout;
    }
    public void setLayout(GUILayout layout) {
        this.layout = layout;
    }
    public void arrange() {
        this.layout.arrange(this, this.children);
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
    public double getPadding() {
        return this.padding;
    }
    public void setPadding(double padding) {
        this.padding = padding;
    }

    @Override
    public void render(Renderer r) {
        r.fillRoundedRect(getBounds(), this.radius, this.radius, this.background);
        r.drawRoundedRect(getBounds(), this.radius, this.radius, this.borderWeight, this.border);
    }
}
