package com.spellcastrpg.main.map;

import com.spellcastrpg.main.geometry.Rectangle;
import com.spellcastrpg.main.geometry.Vector2d;
import com.spellcastrpg.main.objects.GameObject;
import com.spellcastrpg.main.rendering.Renderer;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by laser_000 on 5/14/2016.
 */
public class Map extends GameObject {
    private List<RenderedMapTile> tiles;

    public Map() {
        this.tiles = new ArrayList<>();
        setBounds(new Rectangle());
        setLayer(Integer.MIN_VALUE);
    }

    public List<RenderedMapTile> getTiles() {
        return this.tiles;
    }
    public RenderedMapTile getTileAt(int x, int y) {
        Vector2d position = new Vector2d(x * MapTile.SIZE, y * MapTile.SIZE);
        return getTileAt(position);
    }
    public void setTileAt(int x, int y, MapTile tile) {
        RenderedMapTile rmt = getTileAt(x, y);
        if (rmt == null) {
            rmt = new RenderedMapTile(tile);
            rmt.setPosition(new Vector2d(x * MapTile.SIZE, y * MapTile.SIZE));
            this.tiles.add(rmt);
        }
        rmt.setTile(tile);
    }
    public RenderedMapTile getTileAt(Vector2d position) {
        for (RenderedMapTile tile : this.tiles)
            if (tile.getBounds().contains(position))
                return tile;
        return null;
    }


    public Rectangle getPixelSize() {
        if (this.tiles.size() == 0)
            return new Rectangle();
        double largestX = 0;
        double largestY = 0;
        for (RenderedMapTile rmt : this.tiles) {
            if (rmt.getBounds().getX2() > largestX)
                largestX = rmt.getBounds().getX2();
            if (rmt.getBounds().getY2() > largestY)
                largestY = rmt.getBounds().getY2();
        }
        return new Rectangle(largestX, largestY);
    }


    public static Map load(BufferedReader reader) throws IOException {
        Map map = new Map();
        int y = 0;
        String line;
        while ((line = reader.readLine()) != null) {
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
        for (RenderedMapTile tile : this.tiles)
            if (tile.getRotatedImage() != null)
                r.drawImage(tile.getRotatedImage(), tile.getPosition());
    }
}
