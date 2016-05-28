package com.spellcastrpg.main.objects.gui.layout;

import com.spellcastrpg.main.geometry.Rectangle;
import com.spellcastrpg.main.geometry.Vector2d;
import com.spellcastrpg.main.objects.gui.GUIContainer;
import com.spellcastrpg.main.objects.gui.GUIObject;

import java.util.Map;

public class BorderLayout implements GUILayout {
    public static final GUILayoutOption NORTH = new GUILayoutOption();
    public static final GUILayoutOption EAST = new GUILayoutOption();
    public static final GUILayoutOption SOUTH = new GUILayoutOption();
    public static final GUILayoutOption WEST = new GUILayoutOption();
    public static final GUILayoutOption CENTER = new GUILayoutOption();

    @Override
    public void arrange(GUIContainer container, Map<GUIObject, GUILayoutOption> objects) {
        GUIObject north = getNorth(objects), east = getEast(objects), south = getSouth(objects), west = getWest(objects), center = getCenter(objects);

        Rectangle northBounds = (north != null) ? north.getBounds() : new Rectangle();
        Rectangle eastBounds = (east != null) ? east.getBounds() : new Rectangle();
        Rectangle southBounds = (south != null) ? south.getBounds() : new Rectangle();
        Rectangle westBounds = (west != null) ? west.getBounds() : new Rectangle();
        Rectangle centerBounds = (center != null) ? center.getBounds() : new Rectangle();

        // height of middle row (max of east, center, west)
        double midHeight = Math.max(Math.max(eastBounds.getHeight(), centerBounds.getHeight()), westBounds.getHeight());

        if (north != null)
            north.setBounds(getNorthBounds(container.getAvailableBounds(), northBounds));
        if (south != null)
            south.setBounds(getSouthBounds(container.getAvailableBounds(), northBounds, southBounds));
        if (west != null)
            west.setBounds(getWestBounds(container.getAvailableBounds(), northBounds, eastBounds, midHeight));
        if (east != null)
            east.setBounds(getEastBounds(container.getAvailableBounds(), northBounds, eastBounds, midHeight));
        if (center != null)
            center.setBounds(getCenterBounds(container.getAvailableBounds(), northBounds, eastBounds, southBounds, westBounds));
    }

    public Rectangle getNorthBounds(Rectangle bounds, Rectangle north) {
        return new Rectangle(bounds.getPosition(), bounds.getWidth(), north.getHeight());
    }
    public Rectangle getSouthBounds(Rectangle bounds, Rectangle north, Rectangle south) {
        return new Rectangle(new Vector2d(bounds.getX(), bounds.getY2() - south.getHeight()), bounds.getWidth(), north.getHeight());
    }
    public Rectangle getWestBounds(Rectangle bounds, Rectangle north, Rectangle east, double midHeight) {
        return new Rectangle(new Vector2d(bounds.getX(), north.getY2()), east.getWidth(), midHeight);
    }
    public Rectangle getEastBounds(Rectangle bounds, Rectangle north, Rectangle east, double midHeight) {
        return new Rectangle(bounds.getPosition().add(bounds.getWidth() - east.getWidth(), north.getHeight()), east.getWidth(), midHeight);
    }
    public Rectangle getCenterBounds(Rectangle bounds, Rectangle north, Rectangle east, Rectangle south, Rectangle west) {
        return new Rectangle(new Vector2d(west.getX2(), west.getY()), east.getX() - west.getX2(), south.getY() - north.getY2());
    }

    public GUIObject getNorth(Map<GUIObject, GUILayoutOption> objects) {
        for (GUIObject object : objects.keySet())
            if (objects.get(object) == NORTH)
                return object;
        return null;
    }
    public GUIObject getEast(Map<GUIObject, GUILayoutOption> objects) {
        for (GUIObject object : objects.keySet())
            if (objects.get(object) == EAST)
                return object;
        return null;
    }
    public GUIObject getSouth(Map<GUIObject, GUILayoutOption> objects) {
        for (GUIObject object : objects.keySet())
            if (objects.get(object) == SOUTH)
                return object;
        return null;
    }
    public GUIObject getWest(Map<GUIObject, GUILayoutOption> objects) {
        for (GUIObject object : objects.keySet())
            if (objects.get(object) == WEST)
                return object;
        return null;
    }
    public GUIObject getCenter(Map<GUIObject, GUILayoutOption> objects) {
        for (GUIObject object : objects.keySet())
            if (objects.get(object) == CENTER)
                return object;
        return null;
    }
}
