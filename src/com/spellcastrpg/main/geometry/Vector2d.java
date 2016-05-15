package com.spellcastrpg.main.geometry;

import java.awt.geom.Point2D;

/**
 * Created by laser_000 on 5/14/2016.
 */
public class Vector2d {
    public static final Vector2d ZERO = new Vector2d(0, 0);
    public static final Vector2d ONE = new Vector2d(1, 1);

    private final double x, y;

    public Vector2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return this.x;
    }
    public Vector2d setX(double x) {
        return new Vector2d(x, this.y);
    }
    public double getY() {
        return this.y;
    }
    public Vector2d setY(double y) {
        return new Vector2d(this.x, y);
    }

    public Vector2d add(Vector2d other) {
        return new Vector2d(this.x + other.getX(), this.y + other.getY());
    }
    public Vector2d add(double a) {
        return new Vector2d(this.x + a, this.y + a);
    }
    public Vector2d add(double x, double y) {
        return new Vector2d(this.x + x, this.y + y);
    }

    public Vector2d subtract(Vector2d other) {
        return new Vector2d(this.x - other.getX(), this.y - other.getY());
    }
    public Vector2d subtract(double a) {
        return new Vector2d(this.x - a, this.y - a);
    }
    public Vector2d subtract(double x, double y) {
        return new Vector2d(this.x - x, this.y - y);
    }

    public Vector2d multiply(Vector2d other) {
        return new Vector2d(this.x * other.getX(), this.y * other.getY());
    }
    public Vector2d multiply(double a) {
        return new Vector2d(this.x * a, this.y * a);
    }
    public Vector2d multiply(double x, double y) {
        return new Vector2d(this.x * x, this.y * y);
    }

    public Vector2d divide(Vector2d other) {
        return new Vector2d(this.x / other.getX(), this.y / other.getY());
    }
    public Vector2d divide(double a) {
        return new Vector2d(this.x / a, this.y / a);
    }
    public Vector2d divide(double x, double y) {
        return new Vector2d(this.x / x, this.y / y);
    }


    public double getDistance(Vector2d other) {
        return Math.sqrt(Math.pow(other.getX() - this.x, 2) + Math.pow(other.getY() - this.y, 2));
    }
    public double getMagnitude() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }
    public Vector2d getNormalized() {
        if (this.x == 0 && this.y == 0)
            return ZERO;
        else if (this.x == 0)
            return divide(1, getMagnitude());
        else if (this.y == 0)
            return divide(getMagnitude(), 1);
        return divide(getMagnitude());
    }
    public double getSlope(Vector2d other) {
        return (other.getY() - this.y) / (other.getX() - this.x);
    }


    public Vector2d clamp(Vector2d min, Vector2d max) {
        Vector2d clamped = this;
        if (this.x < min.getX())
            clamped = clamped.setX(min.getX());
        if (this.y < min.getY())
            clamped = clamped.setY(min.getY());
        if (this.x > max.getX())
            clamped = clamped.setX(max.getX());
        if (this.y > max.getY())
            clamped = clamped.setY(max.getY());
        return clamped;
    }
    public Vector2d clamp(Rectangle bounds) {
        return clamp(bounds.getPosition(), bounds.getPosition().add(bounds.getSize()));
    }


    public Vector2d lerp(Vector2d other, double speed) {
        return lerp(other, speed,  speed);
    }
    public Vector2d lerp(Vector2d other, double speed, double error) {
        if (equals(other) || this.getDistance(other) <= error)
            return other;
        double dx = other.getX() - this.x, dy = other.getY() - this.y;
        double direction = Math.atan2(dy, dx);
        double x = this.x + (speed * Math.cos(direction));
        double y = this.y + (speed * Math.sin(direction));
        return new Vector2d(x, y);
    }

    public static Vector2d random(Vector2d min, Vector2d max) {
        return new Vector2d(min.getX() + (max.getX() - min.getX()) * Math.random(),
                min.getY() + (max.getY() - min.getY()) * Math.random());
    }


    public Point2D toPoint2D() {
        return new Point2D.Double(this.x, this.y);
    }


    @Override
    public boolean equals(Object other) {
        return  other instanceof Vector2d && ((Vector2d) other).getX() == this.x && ((Vector2d) other).getY() == this.y;
    }
    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
}
