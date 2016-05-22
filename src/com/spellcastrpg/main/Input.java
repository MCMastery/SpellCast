package com.spellcastrpg.main;

import com.spellcastrpg.main.geometry.Vector2d;
import com.spellcastrpg.main.objects.GameObject;
import com.spellcastrpg.main.objects.gui.GUIObject;

import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by laser_000 on 5/14/2016.
 */
public class Input implements KeyListener, MouseListener, MouseWheelListener {
    public static final int MOUSE_LEFT = 1;
    public static final int MOUSE_MIDDLE = 2;
    public static final int MOUSE_RIGHT = 3;
    public static final Input INSTANCE = new Input();

    private Set<Key> keysDown;
    private Set<Integer> buttonsDown;
    private Vector2d mouseScreenPos, mouseGamePos;

    private Input() {
        this.keysDown = new HashSet<>();
        this.buttonsDown = new HashSet<>();
        this.mouseScreenPos = null;
        this.mouseGamePos = null;
    }

    public Vector2d getMousePosition() {
        return this.mouseGamePos;
    }
    public Vector2d getMouseScreenPosition() {
        return this.mouseScreenPos;
    }
    public boolean mouseOverGUI() {
        Vector2d mousePosition = getMouseScreenPosition();
        for (GameObject object : SpellCast.INSTANCE.getObjects())
            if (object instanceof GUIObject && object.getBounds().contains(mousePosition))
                return true;
        return false;
    }

    public void update() {
        Point point = SpellCast.INSTANCE.getWindow().getCanvas().getMousePosition();
        if (point == null)
            return;
        this.mouseGamePos = new Vector2d(point.getX(), point.getY());
        this.mouseGamePos = this.mouseGamePos.add(SpellCast.INSTANCE.getCameraPosition());
        // subtract half of screen size, since camera pos is in center
        this.mouseGamePos = this.mouseGamePos.subtract(SpellCast.INSTANCE.getWindowSize().getSize().divide(2));
        this.mouseScreenPos = new Vector2d(point.getX(), point.getY());
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
            boolean overGUI = mouseOverGUI();
            for (GameObject object : SpellCast.INSTANCE.getObjects())
                if (!overGUI || object instanceof GUIObject)
                    object.mouseDown(e.getButton(), getMousePosition());
        }
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        this.buttonsDown.remove(e.getButton());
        boolean overGUI = mouseOverGUI();
        for (GameObject object : SpellCast.INSTANCE.getObjects())
            if (!overGUI || object instanceof GUIObject)
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
