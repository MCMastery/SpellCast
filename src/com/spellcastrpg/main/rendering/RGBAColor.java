package com.spellcastrpg.main.rendering;

import java.awt.*;
import java.util.List;

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


    public RGBAColor add(RGBAColor other) {
        return new RGBAColor(this.r + other.getR(), this.g + other.getG(), this.b + other.getB(), this.a + other.getA());
    }
    // does not change alpha value
    public RGBAColor add(double r, double g, double b, double a) {
        return new RGBAColor(this.r + r, this.g + g, this.b + b, this.a + a);
    }
    public RGBAColor add(double r, double g, double b) {
        return new RGBAColor(this.r + r, this.g + g, this.b + b, this.a);
    }
    // does not change alpha value
    public RGBAColor add(double rgb) {
        return new RGBAColor(this.r + rgb, this.g + rgb, this.b + rgb, this.a);
    }

    public RGBAColor subtract(RGBAColor other) {
        return new RGBAColor(this.r - other.getR(), this.g - other.getG(), this.b - other.getB(), this.a - other.getA());
    }
    // does not change alpha value
    public RGBAColor subtract(double r, double g, double b, double a) {
        return new RGBAColor(this.r - r, this.g - g, this.b - b, this.a - a);
    }
    public RGBAColor subtract(double r, double g, double b) {
        return new RGBAColor(this.r - r, this.g - g, this.b - b, this.a);
    }
    // does not change alpha value
    public RGBAColor subtract(double rgb) {
        return new RGBAColor(this.r - rgb, this.g - rgb, this.b - rgb, this.a);
    }

    public RGBAColor multiply(RGBAColor other) {
        return new RGBAColor(this.r * other.getR(), this.g * other.getG(), this.b * other.getB(), this.a * other.getA());
    }
    // does not change alpha value
    public RGBAColor multiply(double r, double g, double b, double a) {
        return new RGBAColor(this.r * r, this.g * g, this.b * b, this.a * a);
    }
    public RGBAColor multiply(double r, double g, double b) {
        return new RGBAColor(this.r * r, this.g * g, this.b * b, this.a);
    }
    // does not change alpha value
    public RGBAColor multiply(double rgb) {
        return new RGBAColor(this.r * rgb, this.g * rgb, this.b * rgb, this.a);
    }

    public RGBAColor divide(RGBAColor other) {
        return new RGBAColor(this.r / other.getR(), this.g / other.getG(), this.b / other.getB(), this.a / other.getA());
    }
    // does not change alpha value
    public RGBAColor divide(double r, double g, double b, double a) {
        return new RGBAColor(this.r / r, this.g / g, this.b / b, this.a / a);
    }
    public RGBAColor divide(double r, double g, double b) {
        return new RGBAColor(this.r / r, this.g / g, this.b / b, this.a);
    }
    // does not change alpha value
    public RGBAColor divide(double rgb) {
        return new RGBAColor(this.r / rgb, this.g / rgb, this.b / rgb, this.a);
    }


    public boolean isGrayscale() {
        return this.r == this.g && this.g == this.b;
    }
    public double getDistance(RGBAColor other) {
        return Math.sqrt(Math.pow(other.getR() - this.r, 2) + Math.pow(other.getG() - this.g, 2) + Math.pow(other.getB() - this.b, 2) + Math.pow(other.getA() - this.a, 2));
    }


    public Color toColor() {
        return new Color((int) Math.round(getR() * 255), (int) Math.round(getG() * 255), (int) Math.round(getB() * 255),
                (int) Math.round(getA() * 255));
    }
    public static RGBAColor fromColor(Color color) {
        return new RGBAColor(color.getRed() / 255.0, color.getGreen() / 255.0, color.getBlue() / 255.0, color.getAlpha() / 255.0);
    }


    public static RGBAColor average(List<RGBAColor> colors) {
        RGBAColor average = TRANSPARENT;
        for (RGBAColor color : colors)
            average = average.add(color);
        return average.divide(colors.size());
    }


    @Override
    public boolean equals(Object other) {
        return other instanceof RGBAColor && ((RGBAColor) other).getR() == getR() && ((RGBAColor) other).getG() == getG()
                && ((RGBAColor) other).getB() == getB() && ((RGBAColor) other).getA() == getA();
    }
    @Override
    public String toString() {
        return "(" + getR() + "," + getG() + "," + getB() + "," + getA() + ")";
    }
}
