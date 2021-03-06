package com.spellcastrpg.main.objects.spells;

import com.spellcastrpg.main.SpellCast;
import com.spellcastrpg.main.geometry.Rectangle;
import com.spellcastrpg.main.items.ItemType;
import com.spellcastrpg.main.objects.Enemy;
import com.spellcastrpg.main.objects.GameObject;
import com.spellcastrpg.main.rendering.RGBAColor;
import com.spellcastrpg.main.rendering.Renderer;

public class WindSpell extends SpellObject {
    // power is scaling factor per update
    private double range, radius, power;
    private RGBAColor color;

    public WindSpell() {
        this.range = 300;
        this.radius = 1;
        this.power = 1.1;
        this.color = new RGBAColor(0.75, 0.75, 0.75);
        updateBounds();
    }

    @Override
    public void init() {
        super.init();
        // fire wind spell
        if (containsModifierType(ItemType.ROSEWOOD_EMBERS))
            this.color = new RGBAColor(1, 0.5, 0.25);
    }

    public double getRange() {
        return this.range;
    }
    public void setRange(double range) {
        this.range = range;
    }
    public double getRadius() {
        return this.radius;
    }
    public void setRadius(double radius) {
        this.radius = radius;
    }
    public double getPower() {
        return this.power;
    }
    public void setPower(double power) {
        this.power = power;
    }
    public boolean isExpanding() {
        return this.radius < this.range;
    }

    public void updateBounds() {
        Rectangle bounds = new Rectangle(this.radius * 2, this.radius * 2);
        bounds = bounds.setCenter(SpellCast.INSTANCE.getPlayer().getCenter());
        setBounds(bounds);
    }

    @Override
    public void update() {
        this.color = this.color.setA(this.color.getA() - (this.power / this.range * 2.0));
        if (this.radius >= this.range && this.color.getA() <= 0)
            destroy();
        if (this.radius >= this.range)
            return;
        double oldRadius = this.radius;
        this.radius *= this.power;

        updateBounds();

        for (GameObject object : SpellCast.INSTANCE.getObjects()) {
            double distanceSquared = getCenter().getDistanceSquared(object.getCenter());
            if (object instanceof Enemy && distanceSquared <= this.radius * this.radius && distanceSquared >= this.radius * this.radius / 2.0) {
                Enemy enemy = (Enemy) object;
                // radius - oldRadius = how much larger this spell got
                enemy.moveAwayFrom(getCenter(), this.radius - oldRadius);
                // fire wind spell
                if (containsModifierType(ItemType.ROSEWOOD_EMBERS))
                    enemy.damage(5);
            }
        }
    }

    @Override
    public void render(Renderer r) {
        r.drawEllipse(getBounds(), this.radius / 2.0, this.color);
    }
}
