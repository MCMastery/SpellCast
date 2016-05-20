package com.spellcastrpg.main.objects;

import com.spellcastrpg.main.geometry.Rectangle;
import com.spellcastrpg.main.geometry.Vector2d;

public class Collider {
    private Rectangle bounds;

    public Collider() {
        this.bounds = new Rectangle();
    }

    public Rectangle getBounds() {
        return this.bounds;
    }
    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }
    public Vector2d getPosition() {
        return this.bounds.getPosition();
    }
    public void setPosition(Vector2d position) {
        this.bounds = this.bounds.setPosition(position);
    }
    public Vector2d getCenter() {
        return this.bounds.getCenter();
    }
    public void setCenter(Vector2d center) {
        this.bounds = this.bounds.setCenter(center);
    }
    public void translate(Vector2d amount) {
        this.bounds = this.bounds.translate(amount);
    }
    public void translate(double tx, double ty) {
        this.bounds = this.bounds.translate(tx, ty);
    }
    public boolean intersects(Collider other) {
        return this.bounds.intersects(other.getBounds());
    }

    public void cancelCollision(Collider obj) {
        if (obj.getBounds().intersects(getBounds())) {
            Rectangle intersection = obj.getBounds().getIntersection(getBounds());
            if (intersection.getWidth() < intersection.getHeight()) {
                if (getBounds().getX() > obj.getBounds().getX())
                    translate(intersection.getWidth(), 0);
                else if (getBounds().getX2() > obj.getBounds().getX())
                    translate(-intersection.getWidth(), 0);
            } else {
                if (getBounds().getY() > obj.getBounds().getY())
                    translate(0, intersection.getHeight());
                else if (getBounds().getY2() > obj.getBounds().getY())
                    translate(0, -intersection.getHeight());
            }
        }
    }
}
