package com.spellcastrpg.main.objects.timers;

import com.spellcastrpg.main.SpellCast;

public abstract class DelayTimer extends Timer {
    private final double secondsDelay;
    private int updatesLeft;

    public DelayTimer(double secondsDelay) {
        this.secondsDelay = secondsDelay;
        this.updatesLeft = (int) Math.round(secondsDelay * SpellCast.FPS);
    }

    public double getSecondsDelay() {
        return this.secondsDelay;
    }
    public int getUpdatesLeft() {
        return this.updatesLeft;
    }

    @Override
    public void update() {
        super.update();
        if (this.updatesLeft <= 0) {
            run();
            destroy();
            return;
        }
        this.updatesLeft--;
    }
}
