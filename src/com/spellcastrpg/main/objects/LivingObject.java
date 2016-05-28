package com.spellcastrpg.main.objects;

import com.spellcastrpg.main.geometry.Rectangle;
import com.spellcastrpg.main.geometry.Vector2d;
import com.spellcastrpg.main.rendering.RGBAColor;
import com.spellcastrpg.main.rendering.Renderer;

/**
 * Created by laser_000 on 5/14/2016.
 */
public class LivingObject extends GameObject {
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
        this.moveToward(position, this.speed);
    }
    public void moveAwayFrom(Vector2d position) {
        this.moveAwayFrom(position, this.speed);
    }
    public void moveToward(Vector2d position, double speed) {
        setCenter(getCenter().lerp(position, speed));
    }
    public void moveAwayFrom(Vector2d position, double speed) {
        Vector2d towardCenter = getCenter().lerp(position, speed);
        Vector2d delta = getCenter().subtract(towardCenter);
        //todo if i am already at the given position I should move from, i will choose a random direction to move in (maybe not the best way)
        if (getCenter().equals(position))
            delta = Vector2d.random(new Vector2d(-1, -1), new Vector2d(1, 1)).getNormalized().multiply(speed);
        setCenter(getCenter().add(delta));
    }

    @Override
    public void render(Renderer r) {
        //r.drawTextCentered((int) Math.round(getHealth()) + " / " + (int) Math.round(getMaxHealth()),
        //        new Font(Font.MONOSPACED, Font.PLAIN, 12), getBounds(), RGBAColor.BLACK);
        Rectangle healthBar = new Rectangle(getPosition().getX(), getPosition().getY() - 20, getBounds().getWidth(), 16);
        r.fillRoundedRect(healthBar, 10, 10, RGBAColor.RED);
        r.fillRoundedRect(healthBar.setWidth(healthBar.getWidth() * (this.health / this.maxHealth)), 10, 10, RGBAColor.GREEN);
        r.drawRoundedRect(healthBar, 10, 10, 1, RGBAColor.BLACK);
    }
}
