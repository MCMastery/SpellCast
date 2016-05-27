package com.spellcastrpg.main.objects.gui;

import com.spellcastrpg.main.Key;
import com.spellcastrpg.main.SpellCast;
import com.spellcastrpg.main.geometry.Rectangle;
import com.spellcastrpg.main.objects.gui.layout.BorderLayout;

public class TestGUI extends GUIContainer {
    private GUIButton north, east, south, west, center;

    public TestGUI() {
        updateBounds();
        setLayout(new BorderLayout());
        this.north = new GUIButton() {
            @Override
            public void click(int button) {
                System.out.println("Clicked north");
            }
        };
        this.north.setTextValue("NORTH");
        this.north.constrainHeight(true);
        addChild(this.north, BorderLayout.NORTH);

        this.east = new GUIButton() {
            @Override
            public void click(int button) {
                System.out.println("Clicked east");
            }
        };
        this.east.setTextValue("EAST");
        this.east.constrainWidth(true);
        addChild(this.east, BorderLayout.EAST);

        this.south = new GUIButton() {
            @Override
            public void click(int button) {
                System.out.println("Clicked south");
            }
        };
        this.south.setTextValue("SOUTH");
        this.south.constrainHeight(true);
        addChild(this.south, BorderLayout.SOUTH);

        this.west = new GUIButton() {
            @Override
            public void click(int button) {
                System.out.println("Clicked west");
            }
        };
        this.west.setTextValue("WEST");
        this.west.constrainWidth(true);
        addChild(this.west, BorderLayout.WEST);

        this.center = new GUIButton() {
            @Override
            public void click(int button) {
                System.out.println("Clicked center");
            }
        };
        this.center.setTextValue("CENTER");
        addChild(this.center, BorderLayout.CENTER);
    }
    public void updateBounds() {
        setBounds(new Rectangle(400, 400).setCenter(SpellCast.INSTANCE.getWindowCenter()));
        arrange();
    }
    @Override
    public void keyDown(Key key) {
        if (key == Key.R)
            SpellCast.INSTANCE.getPlayer().closeGUI();
    }

    @Override
    public void update() {
        updateBounds();
    }
}
