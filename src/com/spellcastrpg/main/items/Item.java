package com.spellcastrpg.main.items;

public class Item {
    private String name;
    private boolean canUse;
    private double usesPerSecond;

    public Item() {
        this.name = "";
        this.canUse = true;
        this.usesPerSecond = 1;
    }

    public String getName() {
        return this.name;
    }
    private void setName(String name) {
        this.name = name;
    }
    public boolean canUse() {
        return this.canUse;
    }
    public double getUsesPerSecond() {
        return this.usesPerSecond;
    }
    private void setUsesPerSecond(double usesPerSecond) {
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
