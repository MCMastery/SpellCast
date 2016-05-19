package com.spellcastrpg.main;

import com.spellcastrpg.main.geometry.Rectangle;
import com.spellcastrpg.main.geometry.Vector2d;
import com.spellcastrpg.main.rendering.Renderer;

import javax.swing.*;
import java.awt.*;

/**
 * Created by laser_000 on 5/14/2016.
 */
public class WindowCanvas extends JComponent {
    public WindowCanvas() {
        setFocusable(true);
        addKeyListener(Input.INSTANCE);
        addMouseListener(Input.INSTANCE);
        addMouseWheelListener(Input.INSTANCE);
    }

    @Override
    public void paintComponent(Graphics g) {
        if (!SpellCast.INSTANCE.initalized())
            return;
        Graphics2D g2d = (Graphics2D) g;
        Renderer r = new Renderer(g2d);

        // clamp camera to inside of map
        Rectangle cameraClamp = SpellCast.INSTANCE.getMapSize().expand(-SpellCast.INSTANCE.getWindowSize().getWidth(), -SpellCast.INSTANCE.getWindowSize().getHeight());
        SpellCast.INSTANCE.setCameraPosition(SpellCast.INSTANCE.getCameraPosition().clamp(cameraClamp));

        // sort objects by layers
        SpellCast.INSTANCE.sortObjects();


        Vector2d translate = SpellCast.INSTANCE.getCameraPosition().subtract(getRectSize().getSize().divide(2)).multiply(-1);
        g2d.translate(translate.getX(), translate.getY());

        SpellCast.INSTANCE.renderObjects(r);

        g2d.translate(-translate.getX(), -translate.getY());

        // render camera-following objects after reverse translate
        SpellCast.INSTANCE.renderCameraFollowObjects(r);
    }

    public Rectangle getRectSize() {
        return new Rectangle(getWidth(), getHeight());
    }
}
