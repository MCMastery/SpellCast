package com.spellcastrpg.main.objects.gui.layout;

import com.spellcastrpg.main.objects.gui.GUIContainer;
import com.spellcastrpg.main.objects.gui.GUIObject;

import java.util.Map;

public interface GUILayout {
    void arrange(GUIContainer container, Map<GUIObject, GUILayoutOption> objects);
}
