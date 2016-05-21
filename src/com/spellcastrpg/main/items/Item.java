package com.spellcastrpg.main.items;

import java.awt.image.BufferedImage;

public interface Item {
    enum State {
        LIQUID, SOLID
    }

    String getName();
    double getUsesPerSecond();
    Item.State getState();
    default boolean hasState() {
        return getState() != null;
    }
    BufferedImage getImage();

    void use();
}
