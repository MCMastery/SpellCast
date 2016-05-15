package com.spellcastrpg.main.items;

import java.awt.image.BufferedImage;

public class Item {
    private BufferedImage image;
    private String name;
    private boolean canUse;
    private double usesPerSecond;

    public Item() {
        this.image = null;
        this.name = "";
        this.canUse = true;
        this.usesPerSecond = 1;
    }

    public BufferedImage getImage() {
        return this.image;
    }
    void setImage(BufferedImage image) {
        this.image = image;
    }
    public String getName() {
        return this.name;
    }
    void setName(String name) {
        this.name = name;
    }
    public boolean canUse() {
        return this.canUse;
    }
    public double getUsesPerSecond() {
        return this.usesPerSecond;
    }
    void setUsesPerSecond(double usesPerSecond) {
        this.usesPerSecond = usesPerSecond;
    }

    public void use() {
        this.canUse = false;
        new Thread(() -> {
            try {
                Thread.sleep((long) (1000 / this.usesPerSecond));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.canUse = true;
        }).start();
    }
}
