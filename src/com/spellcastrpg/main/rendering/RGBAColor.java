package com.spellcastrpg.main.rendering;

import java.awt.*;

/**
 * Created by laser_000 on 5/14/2016.
 */
public class RGBAColor {
    public static final RGBAColor WHITE = new RGBAColor(1, 1, 1);
    public static final RGBAColor GRAY = new RGBAColor(0.5, 0.5, 0.5);
    public static final RGBAColor BLACK = new RGBAColor(0, 0, 0);
    public static final RGBAColor TRANSPARENT = new RGBAColor(0, 0, 0, 0);
    public static final RGBAColor RED = new RGBAColor(1, 0, 0);
    public static final RGBAColor GREEN = new RGBAColor(0, 1, 0);
    public static final RGBAColor BLUE = new RGBAColor(0, 0, 1);

    private final double r, g, b, a;
    
    public RGBAColor(double rgb) {
        this(rgb, rgb, rgb);
    }
    public RGBAColor(double r, double g, double b) {
        this(r, g, b, 1);
    }
    public RGBAColor(double r, double g, double b, double a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }
    
    public double getR() {
        return this.r;
    }
    public RGBAColor setR(double r) {
        return new RGBAColor(r, this.g, this.b, this.a);
    }
    public double getG() {
        return this.g;
    }
    public RGBAColor setG(double g) {
        return new RGBAColor(this.r, g, this.b, this.a);
    }
    public double getB() {
        return this.b;
    }
    public RGBAColor setB(double b) {
        return new RGBAColor(this.r, this.g, b, this.a);
    }
    public double getA() {
        return this.a;
    }
    public RGBAColor setA(double a) {
        return new RGBAColor(this.r, this.g, this.b, a);
    }


    public Color toColor() {
        return new Color((int) Math.round(this.r * 255), (int) Math.round(this.g * 255), (int) Math.round(this.b * 255),
                (int) Math.round(this.a * 255));
    }


    @Override
    public boolean equals(Object other) {
        return other instanceof RGBAColor && ((RGBAColor) other).getR() == this.r && ((RGBAColor) other).getG() == this.g
                && ((RGBAColor) other).getB() == this.b && ((RGBAColor) other).getA() == this.a;
    }
}
