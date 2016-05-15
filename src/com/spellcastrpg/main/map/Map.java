package com.spellcastrpg.main.map;

import com.spellcastrpg.main.geometry.Rectangle;
import com.spellcastrpg.main.geometry.Vector2d;
import com.spellcastrpg.main.objects.GameObject;
import com.spellcastrpg.main.rendering.Renderer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by laser_000 on 5/14/2016.
 */
public class Map extends GameObject {
    private List<List<RenderedMapTile>> tiles;

    public Map() {
        this.tiles = new ArrayList<>();
        setBounds(new Rectangle());
        setLayer(Integer.MIN_VALUE);
    }

    public RenderedMapTile getTileAt(int x, int y) {
        try {
            return this.tiles.get(x).get(y);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
    public void setTileAt(int x, int y, MapTile tile) {
        while (this.tiles.size() <= x)
            this.tiles.add(new ArrayList<>());
        while (this.tiles.get(x).size() <= y)
            this.tiles.get(x).add(new RenderedMapTile(MapTile.AIR));
        this.tiles.get(x).set(y, new RenderedMapTile(tile));
    }


    public Rectangle getPixelSize() {
        if (this.tiles.size() == 0)
            return new Rectangle();
        return new Rectangle(0, 0, this.tiles.size() * MapTile.WIDTH, this.tiles.get(0).size() * MapTile.HEIGHT);
    }


    public static Map load(File file) throws IOException {
        List<String> lines = Files.readAllLines(file.toPath());
        Map map = new Map();
        int y = 0;
        for (String line : lines) {
            if (line.isEmpty())
                continue;
            String[] split = line.split(" ");
            for (int x = 0; x < split.length; x++) {
                String tile = split[x];
                int id;
                try {
                    id = Integer.parseInt(tile);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    id = 0;
                }
                map.setTileAt(x, y, MapTile.values()[id]);
            }
            y++;
        }
        return map;
    }


    @Override
    public void update() {}

    @Override
    public void render(Renderer r) {
        for (int x = 0; x < this.tiles.size(); x++) {
            for (int y = 0; y < this.tiles.get(x).size(); y++) {
                RenderedMapTile tile = getTileAt(x, y);
                if (tile.getRotatedImage() != null)
                    r.drawImage(tile.getRotatedImage(), new Vector2d(x * MapTile.WIDTH, y * MapTile.HEIGHT));
            }
        }
    }
}
