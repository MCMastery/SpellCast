package com.spellcastrpg.main;

import com.spellcastrpg.main.geometry.Vector2d;
import com.spellcastrpg.main.objects.GameObject;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by laser_000 on 5/14/2016.
 */
public class Input implements KeyListener, MouseListener, MouseWheelListener {
    public static final int MOUSE_LEFT = 1;
    public static final Input INSTANCE = new Input();

    private Set<Key> keysDown;
    private Set<Integer> buttonsDown;

    private Input() {
        this.keysDown = new HashSet<>();
        this.buttonsDown = new HashSet<>();
    }

    public Vector2d getMousePosition() {
        Point point = SpellCast.INSTANCE.getWindow().getCanvas().getMousePosition();
        Vector2d position = new Vector2d(point.getX(), point.getY());
        position = position.add(SpellCast.INSTANCE.getCameraPosition());
        // subtract half of screen size, since camera pos is in center
        position = position.subtract(SpellCast.INSTANCE.getWindowSize().getSize().divide(2));
        return position;
    }
    public Vector2d getMouseScreenPosition() {
        Point point = SpellCast.INSTANCE.getWindow().getCanvas().getMousePosition();
        return new Vector2d(point.getX(), point.getY());
    }

    public boolean keyDown(Key key) {
        return this.keysDown.contains(key);
    }
    public boolean keyUp(Key key) {
        return !keyDown(key);
    }
    public boolean mouseDown(int button) {
        return this.buttonsDown.contains(button);
    }
    public boolean mouseUp(int button) {
        return !mouseDown(button);
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {
        Key key = Key.fromCode(e.getKeyCode());
        if (keyUp(key)) {
            this.keysDown.add(key);
            for (GameObject object : SpellCast.INSTANCE.getObjects())
                object.keyDown(key);
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        Key key = Key.fromCode(e.getKeyCode());
        this.keysDown.remove(key);
        for (GameObject object : SpellCast.INSTANCE.getObjects())
            object.keyUp(key);
    }

    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {
        if (mouseUp(e.getButton())) {
            this.buttonsDown.add(e.getButton());
            for (GameObject object : SpellCast.INSTANCE.getObjects())
                object.mouseDown(e.getButton(), getMousePosition());
        }
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        this.buttonsDown.remove(e.getButton());
        for (GameObject object : SpellCast.INSTANCE.getObjects())
            object.mouseUp(e.getButton(), getMousePosition());
    }
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        for (GameObject object : SpellCast.INSTANCE.getObjects())
            object.mouseScroll(e.getPreciseWheelRotation());
    }
}
