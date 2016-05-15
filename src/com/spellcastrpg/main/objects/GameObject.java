package com.spellcastrpg.main.objects;

import com.spellcastrpg.main.Key;
import com.spellcastrpg.main.SpellCast;
import com.spellcastrpg.main.geometry.Rectangle;
import com.spellcastrpg.main.geometry.Vector2d;
import com.spellcastrpg.main.rendering.Renderer;

/**
 * Created by laser_000 on 5/14/2016.
 */
public class GameObject {
    private Rectangle bounds;
    private boolean alive, followCamera;
    private int layer;

    public GameObject() {
        this.bounds = new Rectangle();
        this.alive = false;
        this.layer = 0;
        // does this object always appear at a certain place on the screen, regardless of where the camera is?
        this.followCamera = false;
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
    public boolean followsCamera() {
        return this.followCamera;
    }
    public void setFollowCamera(boolean followCamera) {
        this.followCamera = followCamera;
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


    public void keyDown(Key key) {

    }
    public void keyUp(Key key) {

    }
    public void mouseDown(int btn, Vector2d position) {

    }
    public void mouseUp(int btn, Vector2d position) {

    }
    public void mouseScroll(double amount) {

    }
}
