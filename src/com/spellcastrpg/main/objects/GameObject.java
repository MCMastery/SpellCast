package com.spellcastrpg.main.objects;

import com.spellcastrpg.main.Key;
import com.spellcastrpg.main.SpellCast;
import com.spellcastrpg.main.geometry.Rectangle;
import com.spellcastrpg.main.geometry.Vector2d;
import com.spellcastrpg.main.rendering.Renderer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
        SpellCast.INSTANCE.registerObject(this);
        this.alive = true;
    }
    public void update() {
        for (GameObject object : getCollisions())
            collide(object);
    }
    public void destroy() {
        SpellCast.INSTANCE.unregisterObject(this);
        this.alive = false;
    }
    public void render(Renderer r) {

    }

    public Set<GameObject> getCollisions() {
        Set<GameObject> objects = new HashSet<>();
        for (GameObject object : SpellCast.INSTANCE.getObjects())
            if (!this.equals(object) && getBounds().intersects(object.getBounds()))
                objects.add(object);
        return objects;
    }

    public void cancelCollision(GameObject obj) {
        if (obj.getBounds().intersects(this.bounds)) {
            Rectangle intersection = obj.getBounds().getIntersection(this.bounds);
            if (intersection.getWidth() < intersection.getHeight()) {
                if (this.bounds.getX() > obj.getBounds().getX())
                    this.bounds.setPosition(this.bounds.getPosition().add(intersection.getWidth(), 0));
                else if (this.bounds.getX2() > obj.getBounds().getX())
                    this.bounds.setPosition(this.bounds.getPosition().subtract(intersection.getWidth(), 0));
            } else {
                if (this.bounds.getY() > obj.getBounds().getY())
                    this.bounds.setPosition(this.bounds.getPosition().add(0, intersection.getHeight()));
                else if (this.bounds.getY2() > obj.getBounds().getY())
                    this.bounds.setPosition(this.bounds.getPosition().subtract(0, intersection.getHeight()));
            }
        }
    }


    public void collide(GameObject other) {

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
