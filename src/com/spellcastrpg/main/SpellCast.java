package com.spellcastrpg.main;

import com.spellcastrpg.main.geometry.Rectangle;
import com.spellcastrpg.main.geometry.Vector2d;
import com.spellcastrpg.main.map.Map;
import com.spellcastrpg.main.objects.GameObject;
import com.spellcastrpg.main.objects.Player;
import com.spellcastrpg.main.rendering.Renderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by laser_000 on 5/14/2016.
 */
public class SpellCast {
    public static final String VERSION = "0.0.1";
    public static final double FPS = 60;

    public static final SpellCast INSTANCE = new SpellCast();

    public static void main(String[] args) {
        INSTANCE.map = loadMap("Map1.map");
        if (INSTANCE.map == null) {
            System.err.println("Map file could not be read!");
            return;
        }
        INSTANCE.map.init();

        INSTANCE.player = new Player();
        INSTANCE.player.init();
        Thread thread = new Thread(new GameLoop());
        thread.setDaemon(true);
        thread.start();
        INSTANCE.init = true;
    }

    private List<GameObject> objects;
    private Window window;
    private Player player;
    private Vector2d cameraPosition;
    private Map map;
    private boolean init;

    private SpellCast() {
        this.objects = new ArrayList<>();
        this.window = new Window();
        this.cameraPosition = Vector2d.ZERO;
        this.init = false;
    }

    public List<GameObject> getObjects() {
        return this.objects;
    }
    public void updateObjects() {
        for (GameObject object : new ArrayList<>(this.objects))
            object.update();
    }
    public Window getWindow() {
        return this.window;
    }
    public Player getPlayer() {
        return this.player;
    }
    public Map getMap() {
        return this.map;
    }
    // center of screen
    public Vector2d getCameraPosition() {
        return this.cameraPosition;
    }
    public void setCameraPosition(Vector2d cameraPosition) {
        this.cameraPosition = cameraPosition;
    }


    public Rectangle getWindowSize() {
        return this.window.getCanvas().getRectSize();
    }
    public Vector2d getWindowCenter() {
        return getWindowSize().getCenter();
    }
    public Rectangle getMapSize() {
        return this.map.getPixelSize();
    }
    public Vector2d getMapCenter() {
        return getMapSize().getCenter();
    }

    public boolean initalized() {
        return this.init;
    }

    public void sortObjects() {
        Collections.sort(this.objects, (o1, o2) -> {
            if (o1.getLayer() > o2.getLayer())
                return 1;
            else if (o2.getLayer() > o1.getLayer())
                return -1;
            return 0;
        });
    }

    public void renderCameraFollowObjects(Renderer r) {
        for (GameObject object : new ArrayList<>(this.objects))
            if (object.followsCamera())
                object.render(r);
    }
    public void renderObjects(Renderer r) {
        for (GameObject object : new ArrayList<>(this.objects))
            if (!object.followsCamera())
                object.render(r);
    }


    public static BufferedImage loadImage(String name) {
        try {
            InputStream stream = SpellCast.class.getClassLoader().getResourceAsStream("com/spellcastrpg/main/resources/" + name);
            return ImageIO.read(stream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static Map loadMap(String name) {
        try {
            InputStream stream = SpellCast.class.getClassLoader().getResourceAsStream("com/spellcastrpg/main/resources/" + name);
            return Map.load(new BufferedReader(new InputStreamReader(stream)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
