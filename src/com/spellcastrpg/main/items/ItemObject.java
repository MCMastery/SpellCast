package com.spellcastrpg.main.items;

public class ItemObject implements Item {
    private final ItemType type;
    private boolean canUse;

    public ItemObject(ItemType type) {
        this.canUse = true;
        this.type = type;
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

    public void use() {
        if (this.type.getUsesPerSecond() > 0) {
            this.canUse = false;
            new Thread(() -> {
                try {
                    Thread.sleep((long) (1000 / this.type.getUsesPerSecond()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.canUse = true;
            }).start();
        }
    }
}
