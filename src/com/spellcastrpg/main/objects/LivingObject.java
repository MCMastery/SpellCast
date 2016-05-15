package com.spellcastrpg.main.objects;

import com.spellcastrpg.main.geometry.Vector2d;
import com.spellcastrpg.main.rendering.RGBAColor;
import com.spellcastrpg.main.rendering.Renderer;

import java.awt.*;

/**
 * Created by laser_000 on 5/14/2016.
 */
public abstract class LivingObject extends GameObject {
    private double speed;
    private double health, maxHealth;

    public LivingObject() {
        this.speed = 2;
        this.health = 100;
        this.maxHealth = 100;
    }


    public double getSpeed() {
        return this.speed;
    }
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getHealth() {
        return this.health;
    }
    public void setHealth(double health) {
        this.health = health;
        checkHealth();
    }
    public double getMaxHealth() {
        return this.maxHealth;
    }
    public void setMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
        checkHealth();
    }

    public void heal() {
        this.health = this.maxHealth;
    }
    public void heal(double amount) {
        this.health += amount;
        checkHealth();
    }
    public void damage(double amount) {
        this.health -= amount;
        checkHealth();
    }

    public void checkHealth() {
        if (this.health <= 0)
            kill();
        else if (this.health > this.maxHealth)
            this.health = this.maxHealth;
    }

    public void kill() {
        destroy();
    }

    public void moveToward(Vector2d position) {
        setCenter(getCenter().lerp(position, this.speed));
    }

    @Override
    public void render(Renderer r) {
        r.drawTextCentered((int) Math.round(getHealth()) + " / " + (int) Math.round(getMaxHealth()),
                new Font(Font.MONOSPACED, Font.PLAIN, 12), getBounds(), RGBAColor.BLACK);
    }
}
