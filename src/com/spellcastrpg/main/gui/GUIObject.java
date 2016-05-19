package com.spellcastrpg.main.gui;

import com.spellcastrpg.main.objects.GameObject;

public class GUIObject extends GameObject {
    public GUIObject() {
        setFollowCamera(true);
        setLayer(Integer.MAX_VALUE);
    }
}
