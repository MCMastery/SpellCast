package com.spellcastrpg.main.items;

import com.spellcastrpg.main.objects.timers.DelayTimer;

public class ItemObject implements Item {
    public static final int WIDTh = 64, HEIGHT = 64;
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
            // 1.0 / since its uses PER SECOND, not seconds delay between uses
            new DelayTimer(1.0 / this.type.getUsesPerSecond()) {
                @Override
                public void run() {
                    canUse = true;
                }
            }.init();
        }
    }
}
