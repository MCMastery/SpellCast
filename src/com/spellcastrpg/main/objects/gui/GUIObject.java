package com.spellcastrpg.main.objects.gui;

import com.spellcastrpg.main.Anchor;
import com.spellcastrpg.main.geometry.Rectangle;
import com.spellcastrpg.main.geometry.Vector2d;
import com.spellcastrpg.main.objects.GameObject;

public class GUIObject extends GameObject {
    private Anchor anchor;

    public GUIObject() {
        this.anchor = new Anchor();
        setFollowCamera(true);
        setLayer(Integer.MAX_VALUE);
    }

    public Anchor getAnchor() {
        return this.anchor;
    }
    public void setAnchor(Anchor anchor) {
        this.anchor = anchor;
    }
    //todo find better way to use anchors
    @Override
    public Rectangle getBounds() {
        return this.anchor.anchor(super.getBounds());
    }
    //todo find better way to use anchors
    @Override
    public Vector2d getPosition() {
        return getBounds().getPosition();
    }
    //todo find better way to use anchors
    @Override
    public Vector2d getCenter() {
        return getBounds().getCenter();
    }
}
