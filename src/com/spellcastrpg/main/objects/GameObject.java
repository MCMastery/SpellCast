package com.spellcastrpg.main.objects;

import com.spellcastrpg.main.Key;
import com.spellcastrpg.main.SpellCast;
import com.spellcastrpg.main.geometry.Vector2d;
import com.spellcastrpg.main.map.RenderedMapTile;
import com.spellcastrpg.main.rendering.Renderer;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by laser_000 on 5/14/2016.
 */
public class GameObject extends Collider {
    private boolean alive, followCamera;
    private int layer;

    public GameObject() {
        this.alive = false;
        this.layer = 0;
        // does this object always appear at a certain place on the screen, regardless of where the camera is?
        this.followCamera = false;
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


    public boolean inCameraView() {
        return getBounds().intersects(SpellCast.INSTANCE.getCameraView());
    }


    public void init() {
        SpellCast.INSTANCE.registerObject(this);
        this.alive = true;
    }
    public void update() {

    }
    public void collisionUpdate() {
        for (Collider object : getCollisions())
            collide(object);
    }
    public void destroy() {
        SpellCast.INSTANCE.unregisterObject(this);
        this.alive = false;
    }
    public void render(Renderer r) {

    }


    public Set<Collider> getCollisions() {
        Set<Collider> objects = new HashSet<>();
        for (GameObject object : SpellCast.INSTANCE.getObjects())
            if (!this.equals(object) && getBounds().intersects(object.getBounds()))
                objects.add(object);
        for (RenderedMapTile mapTile : SpellCast.INSTANCE.getMap().getTiles())
            if (getBounds().intersects(mapTile.getBounds()))
                objects.add(mapTile);
        return objects;
    }

    public void collide(Collider other) {

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
