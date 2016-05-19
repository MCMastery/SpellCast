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
    public void setPosition(Vector2d center) {
        this.bounds = this.bounds.setPosition(center);
    }
    public Vector2d getCenter() {
        return this.bounds.getCenter();
    }
    public void setCenter(Vector2d center) {
        this.bounds = this.bounds.setCenter(center);
    }
    public boolean intersects(Collider other) {
        return this.bounds.intersects(other.getBounds());
    }

    public void cancelCollision(Collider obj) {
        if (obj.getBounds().intersects(getBounds())) {
            Rectangle intersection = obj.getBounds().getIntersection(getBounds());
            if (intersection.getWidth() < intersection.getHeight()) {
                if (getBounds().getX() > obj.getBounds().getX())
                    setPosition(getBounds().getPosition().add(intersection.getWidth(), 0));
                else if (getBounds().getX2() > obj.getBounds().getX())
                    setPosition(getBounds().getPosition().subtract(intersection.getWidth(), 0));
            } else {
                if (getBounds().getY() > obj.getBounds().getY())
                    setPosition(getBounds().getPosition().add(0, intersection.getHeight()));
                else if (getBounds().getY2() > obj.getBounds().getY())
                    setPosition(getBounds().getPosition().subtract(0, intersection.getHeight()));
            }
        }
    }
}
