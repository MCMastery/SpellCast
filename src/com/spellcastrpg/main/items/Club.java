package com.spellcastrpg.main.items;

import com.spellcastrpg.main.SpellCast;
import com.spellcastrpg.main.objects.Enemy;
import com.spellcastrpg.main.objects.GameObject;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Club extends Item {
    public static final BufferedImage IMAGE = SpellCast.loadImage("club.png");

    public Club() {
        setImage(IMAGE);
        setName("Club");
        setUsesPerSecond(1);
    }

    @Override
    public void use() {
        super.use();
        for (GameObject object : new ArrayList<>(SpellCast.INSTANCE.getObjects()))
            if (object instanceof Enemy && object.getCenter().getDistance(SpellCast.INSTANCE.getPlayer().getCenter()) <= 150)
                ((Enemy) object).damage(20);
    }
}
