package com.spellcastrpg.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by laser_000 on 5/14/2016.
 */
public class Input implements KeyListener, MouseListener {
    public static final int MOUSE_LEFT = 1;
    public static final Input INSTANCE = new Input();

    private Set<Integer> keysDown, buttonsDown;

    private Input() {
        this.keysDown = new HashSet<>();
        this.buttonsDown = new HashSet<>();
    }

    public boolean keyDown(int key) {
        return this.keysDown.contains(key);
    }
    public boolean keyUp(int key) {
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
        if (keyUp(e.getKeyCode()))
            this.keysDown.add(e.getKeyCode());
    }
    @Override
    public void keyReleased(KeyEvent e) {
        this.keysDown.remove(e.getKeyCode());
    }

    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {
        if (mouseUp(e.getButton()))
            this.buttonsDown.add(e.getButton());
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        this.buttonsDown.remove(e.getButton());
    }
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}
