package com.spellcastrpg.main;

import com.spellcastrpg.main.geometry.Rectangle;

public class Anchor implements IAnchor {
    private final HAnchor hAnchor;
    private final VAnchor vAnchor;

    public Anchor() {
        this(HAnchor.WEST, VAnchor.NORTH);
    }
    public Anchor(HAnchor hAnchor, VAnchor vAnchor) {
        this.hAnchor = hAnchor;
        this.vAnchor = vAnchor;
    }

    public HAnchor getHorizontalAnchor() {
        return this.hAnchor;
    }
    public Anchor setHorizontalAnchor(HAnchor horizontalAnchor) {
        return new Anchor(horizontalAnchor, this.vAnchor);
    }
    public VAnchor getVerticalAnchor() {
        return this.vAnchor;
    }
    public Anchor setVerticalAnchor(VAnchor verticalAnchor) {
        return new Anchor(this.hAnchor, verticalAnchor);
    }

    @Override
    public Rectangle anchor(Rectangle rect) {
        return this.hAnchor.anchor(this.vAnchor.anchor(rect));
    }
}
