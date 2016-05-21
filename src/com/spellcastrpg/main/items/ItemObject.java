package com.spellcastrpg.main.items;

import com.spellcastrpg.main.objects.timers.DelayTimer;

import java.awt.image.BufferedImage;

public class ItemObject implements Item {
    public static final int SIZE = 64;
    private final ItemType type;
    private boolean canUse;
    private String name;
    private double usesPerSecond;
    private Item.State state;
    private BufferedImage image;

    public ItemObject(ItemType type) {
        this.canUse = true;
        this.type = type;
        this.name = type.getName();
        this.usesPerSecond = type.getUsesPerSecond();
        this.state = type.getState();
        this.image = type.getImage();
    }

    public ItemType getType() {
        return this.type;
    }
    public boolean canUse() {
        return this.canUse;
    }
    void setCanUse(boolean canUse) {
        this.canUse = canUse;
    }

    @Override
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public double getUsesPerSecond() {
        return this.usesPerSecond;
    }
    public void setUsesPerSecond(double usesPerSecond) {
        this.usesPerSecond = usesPerSecond;
    }
    @Override
    public Item.State getState() {
        return this.state;
    }
    public void setState(Item.State state) {
        this.state = state;
    }
    @Override
    public BufferedImage getImage() {
        return this.image;
    }
    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void use() {
        if (this.usesPerSecond > 0) {
            this.canUse = false;
            // 1.0 / since its uses PER SECOND, not seconds delay between uses
            new DelayTimer(1.0 / this.usesPerSecond) {
                @Override
                public void run() {
                    canUse = true;
                }
            }.init();
        }
    }
}
