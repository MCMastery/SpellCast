package com.spellcastrpg.main.objects;

import com.spellcastrpg.main.SpellCast;
import com.spellcastrpg.main.geometry.Rectangle;
import com.spellcastrpg.main.geometry.Vector2d;
import com.spellcastrpg.main.rendering.Renderer;

/**
 * Created by laser_000 on 5/14/2016.
 */
public class GameObject {
    private Rectangle bounds;
    private boolean alive;
    private int layer;

    public GameObject() {
        this.bounds = new Rectangle();
        this.alive = false;
        this.layer = 0;
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
    public boolean intersects(GameObject other) {
        return this.bounds.intersects(other.getBounds());
    }
    public boolean isAlive() {
        return this.alive;
    }
    public int getLayer() {
        return this.layer;
    }
    public void setLayer(int layer) {
        this.layer = layer;
    }


    public void init() {
        SpellCast.INSTANCE.getObjects().add(this);
        this.alive = true;
    }
    public void update() {}
    public void destroy() {
        SpellCast.INSTANCE.getObjects().remove(this);
        this.alive = false;
    }
    public void render(Renderer r) {

    }
}
