package com.spellcastrpg.main.geometry;

import java.awt.geom.Rectangle2D;

/**
 * Created by laser_000 on 5/14/2016.
 */
public class Rectangle {
    private final Vector2d position;
    private final double width, height;

    public Rectangle() {
        this(0, 0);
    }
    public Rectangle(double width, double height) {
        this(0, 0, width, height);
    }
    public Rectangle(double x, double y, double width, double height) {
        this(new Vector2d(x, y), width, height);
    }
    public Rectangle(Vector2d position, double width, double height) {
        this.position = position;
        this.width = width;
        this.height = height;
    }

    public Vector2d getPosition() {
        return this.position;
    }
    public Rectangle setPosition(Vector2d position) {
        return new Rectangle(position, this.width, this.height);
    }
    public Rectangle setPosition(double x, double y) {
        return setPosition(new Vector2d(x, y));
    }

    public double getX() {
        return this.position.getX();
    }
    public Rectangle setX(double x) {
        return new Rectangle(this.position.setX(x), this.width, this.height);
    }
    public double getY() {
        return this.position.getY();
    }
    public Rectangle setY(double y) {
        return new Rectangle(this.position.setY(y), this.width, this.height);
    }
    public double getX2() {
        return this.position.getX() + this.width;
    }
    public double getY2() {
        return this.position.getY() + this.height;
    }

    public double getWidth() {
        return this.width;
    }
    public Rectangle setWidth(double width) {
        return new Rectangle(this.position, width, this.height);
    }
    public double getHeight() {
        return this.height;
    }
    public Rectangle setHeight(double height) {
        return new Rectangle(this.position, this.width, height);
    }

    public Vector2d getSize() {
        return new Vector2d(this.width, this.height);
    }
    public Rectangle setSize(Vector2d size) {
        return new Rectangle(this.position, size.getX(), size.getY());
    }
    public Rectangle setSize(double x, double y) {
        return setSize(new Vector2d(x, y));
    }

    public Rectangle clamp(Rectangle bounds) {
        Rectangle clamped = this;
        // left
        if (clamped.getX() < bounds.getX())
            clamped = clamped.setX(bounds.getX());
        // top
        if (clamped.getY() < bounds.getY())
            clamped = clamped.setY(bounds.getY());
        // right
        if (clamped.getX2() > bounds.getX2())
            clamped = clamped.setX(bounds.getX2() - clamped.getWidth());
        // bottom
        if (clamped.getY2() > bounds.getY2())
            clamped = clamped.setY(bounds.getY2() - clamped.getHeight());
        return clamped;
    }
    public Rectangle expand(Vector2d amount) {
        return new Rectangle(this.position, this.width + amount.getX(), this.height + amount.getY()).setCenter(getCenter());
    }
    public Rectangle expand(double sx, double sy) {
        return expand(new Vector2d(sx, sy));
    }

    public boolean intersects(Rectangle other) {
        return toRect2D().intersects(other.toRect2D());
    }
    public boolean contains(Rectangle other) {
        return toRect2D().contains(other.toRect2D());
    }
    public boolean contains(Vector2d point) {
        return toRect2D().contains(point.toPoint2D());
    }
    public Rectangle getIntersection(Rectangle other) {
        return fromRect2D(toRect2D().createIntersection(other.toRect2D()));
    }


    public Vector2d getCenter() {
        return this.position.add(this.width / 2, this.height / 2);
    }
    public Rectangle setCenter(Vector2d center) {
        return setPosition(-this.width / 2 + center.getX(), -this.height / 2 + center.getY());
    }
    public Rectangle setCenter(double x, double y) {
        return setCenter(new Vector2d(x, y));
    }


    public Rectangle2D toRect2D() {
        return new Rectangle2D.Double(this.position.getX(), this.position.getY(), this.width, this.height);
    }
    public Rectangle fromRect2D(Rectangle2D rect2D) {
        return new Rectangle(rect2D.getX(), rect2D.getY(), rect2D.getWidth(), rect2D.getHeight());
    }


    @Override
    public boolean equals(Object other) {
        return other instanceof Rectangle && ((Rectangle) other).getPosition().equals(this.position)
                && ((Rectangle) other).getWidth() == this.width && ((Rectangle) other).getHeight() == this.height;
    }
    @Override
    public String toString() {
        return "(" + this.position + "," + this.width + "," + this.height + ")";
    }
}
