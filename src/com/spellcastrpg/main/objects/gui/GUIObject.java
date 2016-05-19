package com.spellcastrpg.main.objects.gui;

import com.spellcastrpg.main.objects.GameObject;

public class GUIObject extends GameObject {
    public GUIObject() {
        setFollowCamera(true);
        setLayer(Integer.MAX_VALUE);
    }
}
