package com.spellcastrpg.main.objects;

import com.spellcastrpg.main.SpellCast;
import com.spellcastrpg.main.geometry.Rectangle;
import com.spellcastrpg.main.geometry.Vector2d;
import com.spellcastrpg.main.rendering.RGBAColor;
import com.spellcastrpg.main.rendering.Renderer;

public class TestEnemy extends Enemy {
    public TestEnemy() {
        setBounds(new Rectangle(0, 0, 32, 32));
        setCenter(Vector2d.random(SpellCast.INSTANCE.getMapSize().expand(getBounds().getSize().divide(2).multiply(-1))));
    }
    @Override
    public void update() {
        moveToward(SpellCast.INSTANCE.getPlayer().getCenter());
        super.update();
    }
    @Override
    public void collide(Collider object) {
        if (object instanceof Player)
            ((Player) object).damage(0.1);
    }
    @Override
    public void render(Renderer r) {
        r.fillRect(getBounds(), RGBAColor.RED);
        super.render(r);
    }
}
