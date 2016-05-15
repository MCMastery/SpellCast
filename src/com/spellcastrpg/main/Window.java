package com.spellcastrpg.main;

import com.spellcastrpg.main.geometry.Rectangle;

import javax.swing.*;
import java.awt.*;

/**
 * Created by laser_000 on 5/14/2016.
 */
public class Window {
    private JFrame frame;
    private WindowCanvas canvas;

    public Window() {
        this.frame = new JFrame();
        this.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.frame.setMinimumSize(new Dimension(800, 600));
        this.frame.setTitle("SpellCast v" + SpellCast.VERSION);
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.canvas = new WindowCanvas();
        this.frame.add(this.canvas);
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }

    public JFrame getFrame() {
        return this.frame;
    }
    public WindowCanvas getCanvas() {
        return this.canvas;
    }
    public String getTitle() {
        return this.frame.getTitle();
    }
    public void setTitle(String title) {
        this.frame.setTitle(title);
    }
    public Rectangle getSize() {
        return new Rectangle(this.frame.getWidth(), this.frame.getHeight());
    }
    public void setSize(Rectangle size) {
        this.frame.setSize((int) Math.round(size.getWidth()), (int) Math.round(size.getHeight()));
    }
}
