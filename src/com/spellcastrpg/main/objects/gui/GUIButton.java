package com.spellcastrpg.main.objects.gui;

import com.spellcastrpg.main.geometry.Vector2d;

public abstract class GUIButton extends GUITextContainer {
    public abstract void click(int button);

    @Override
    public void mouseDown(int button, Vector2d position) {
        if (getBounds().contains(position))
            click(button);
    }
}
