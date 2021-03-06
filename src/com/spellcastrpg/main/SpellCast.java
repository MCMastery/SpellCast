package com.spellcastrpg.main;

import com.spellcastrpg.main.geometry.Rectangle;
import com.spellcastrpg.main.geometry.Vector2d;
import com.spellcastrpg.main.map.Map;
import com.spellcastrpg.main.objects.GameObject;
import com.spellcastrpg.main.objects.Player;
import com.spellcastrpg.main.objects.TestEnemy;
import com.spellcastrpg.main.rendering.Animation;
import com.spellcastrpg.main.rendering.Renderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

        for (int i = 0; i < 10; i++)
            new TestEnemy().init();
    }

    private List<GameObject> objects, objectsClone;
    private Window window;
    private Player player;
    private Vector2d cameraPosition;
    private Map map;
    private boolean init;

    private SpellCast() {
        this.objects = new ArrayList<>();
        this.objects = Collections.synchronizedList(this.objects);
        updateCloneObjects();
        this.window = new Window();
        this.cameraPosition = Vector2d.ZERO;
        this.init = false;
    }

    void updateCloneObjects() {
        this.objectsClone = new ArrayList<>(this.objects);
    }

    public void registerObject(GameObject object) {
        this.objects.add(object);
    }
    public void unregisterObject(GameObject object) {
        this.objects.remove(object);
    }
    public List<GameObject> getObjects() {
        return this.objectsClone;
    }
    public void updateObjects() {
        for (GameObject object : getObjects())
            object.update();
    }
    public void collisionUpdateObjects() {
        for (GameObject object : getObjects())
            object.collisionUpdate();
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
    public Rectangle getCameraView() {
        return getWindowSize().setCenter(this.cameraPosition);
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
        for (GameObject object : getObjects())
            if (object.followsCamera())
                object.render(r);
    }
    public void renderObjects(Renderer r) {
        for (GameObject object : getObjects())
            if (!object.followsCamera())
                object.render(r);
    }


    public static InputStream getResourceStream(String name) {
        return SpellCast.class.getClassLoader().getResourceAsStream("com/spellcastrpg/main/resources/" + name);
    }

    public static BufferedImage loadImage(String name) {
        try {
            return ImageIO.read(getResourceStream(name));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static Animation loadAnimation(String name, int frameCount, double framesPerSecond) {
        BufferedImage image = loadImage(name);
        if (image == null)
            return null;
        int frameWidth = image.getWidth() / frameCount;
        List<BufferedImage> frames = new ArrayList<>();
        for (int x = 0; x < image.getWidth() - frameCount; x += frameWidth)
            frames.add(image.getSubimage(x, 0, frameWidth, image.getHeight()));
        return new Animation(framesPerSecond, frames);
    }
    public static Map loadMap(String name) {
        try {
            return Map.load(new BufferedReader(new InputStreamReader(getResourceStream(name))));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
