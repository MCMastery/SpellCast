package com.spellcastrpg.main.rendering;

import com.spellcastrpg.main.SpellCast;

import java.awt.image.BufferedImage;
import java.util.*;

/**
 * Created by laser_000 on 5/21/2016.
 */
public class Animation {
    private List<BufferedImage> frames;
    private double framesPerSecond;
    // updates left = updates until current frame increments
    private int updatesLeft, currentFrame;

    public Animation() {
        this(SpellCast.FPS);
    }
    public Animation(double framesPerSecond) {
        this(framesPerSecond, new ArrayList<>());
    }
    public Animation(double framesPerSecond, BufferedImage... frames) {
        this(framesPerSecond, new ArrayList<>(Arrays.asList(frames)));
    }
    public Animation(double framesPerSecond, List<BufferedImage> frames) {
        this.frames = frames;
        this.framesPerSecond = framesPerSecond;
        this.currentFrame = 0;
        resetUpdatesLeft();
    }

    public List<BufferedImage> getFrames() {
        return this.frames;
    }
    public BufferedImage getFrame(int frame) {
        return frame >= this.frames.size() ? null : this.frames.get(frame);
    }
    public void addFrame(BufferedImage frame) {
        this.frames.add(frame);
    }
    public void removeFrame(BufferedImage frame) {
        this.frames.remove(frame);
    }
    public void removeFrame(int frame) {
        this.frames.remove(frame);
    }
    public BufferedImage getCurrentFrame() {
        return getFrame(this.currentFrame);
    }

    private void resetUpdatesLeft() {
        this.updatesLeft = (int) Math.round(SpellCast.FPS / this.framesPerSecond);
    }
    private void nextFrame() {
        this.currentFrame++;
        if (this.currentFrame >= this.frames.size())
            this.currentFrame = 0;
    }

    public void update() {
        if (this.updatesLeft <= 0) {
            resetUpdatesLeft();
            nextFrame();
        } else
            this.updatesLeft--;
    }
}
