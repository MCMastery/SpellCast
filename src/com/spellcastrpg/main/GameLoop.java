package com.spellcastrpg.main;

/**
 * Created by laser_000 on 5/14/2016.
 */
public class GameLoop implements Runnable {
    @Override
    public void run() {
        while (true) {
            SpellCast.INSTANCE.updateCloneObjects();
            Input.INSTANCE.update();
            SpellCast.INSTANCE.updateObjects();
            SpellCast.INSTANCE.collisionUpdateObjects();
            SpellCast.INSTANCE.getWindow().getCanvas().repaint();
            try {
                Thread.sleep((long) (1000 / SpellCast.FPS));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
