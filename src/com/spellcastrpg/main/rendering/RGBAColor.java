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
        // clamp
        return this.r > 1 ? 1 : (this.r < 0 ? 0 : this.r);
    }
    public RGBAColor setR(double r) {
        return new RGBAColor(r, this.g, this.b, this.a);
    }
    public double getG() {
        // clamp
        return this.g > 1 ? 1 : (this.g < 0 ? 0 : this.g);
    }
    public RGBAColor setG(double g) {
        return new RGBAColor(this.r, g, this.b, this.a);
    }
    public double getB() {
        // clamp
        return this.b > 1 ? 1 : (this.b < 0 ? 0 : this.b);
    }
    public RGBAColor setB(double b) {
        return new RGBAColor(this.r, this.g, b, this.a);
    }
    public double getA() {
        // clamp
        return this.a > 1 ? 1 : (this.a < 0 ? 0 : this.a);
    }
    public RGBAColor setA(double a) {
        return new RGBAColor(this.r, this.g, this.b, a);
    }


    public Color toColor() {
        return new Color((int) Math.round(getR() * 255), (int) Math.round(getG() * 255), (int) Math.round(getB() * 255),
                (int) Math.round(getA() * 255));
    }


    @Override
    public boolean equals(Object other) {
        return other instanceof RGBAColor && ((RGBAColor) other).getR() == getR() && ((RGBAColor) other).getG() == getG()
                && ((RGBAColor) other).getB() == getB() && ((RGBAColor) other).getA() == getA();
    }
}
