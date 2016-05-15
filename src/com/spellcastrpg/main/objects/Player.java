package com.spellcastrpg.main.objects;

import com.spellcastrpg.main.Input;
import com.spellcastrpg.main.Key;
import com.spellcastrpg.main.SpellCast;
import com.spellcastrpg.main.geometry.Rectangle;
import com.spellcastrpg.main.geometry.Vector2d;
import com.spellcastrpg.main.items.Club;
import com.spellcastrpg.main.items.Item;
import com.spellcastrpg.main.rendering.RGBAColor;
import com.spellcastrpg.main.rendering.Renderer;

/**
 * Created by laser_000 on 5/14/2016.
 */
public class Player extends LivingObject {
    private Inventory inventory;

    public Player() {
        setBounds(new Rectangle(0, 0, 100, 100));
        setCenter(SpellCast.INSTANCE.getMapCenter());
        setSpeed(4);
        setMaxHealth(100);
        setHealth(100);
        this.inventory = new Inventory();
    }

    public Inventory getInventory() {
        return this.inventory;
    }
    public Item getSelectedItem() {
        return this.inventory.getSelectedItem();
    }
    public boolean addItem(Item item) {
        return this.inventory.addItem(item);
    }



    @Override
    public void init() {
        super.init();
        this.inventory.init();
        addItem(new Club());
    }
    @Override
    public void update() {
        super.update();
        Vector2d movement = Vector2d.ZERO;
        if (Input.INSTANCE.keyDown(Key.W))
            movement = movement.add(0, -1);
        if (Input.INSTANCE.keyDown(Key.A))
            movement = movement.add(-1, 0);
        if (Input.INSTANCE.keyDown(Key.S))
            movement = movement.add(0, 1);
        if (Input.INSTANCE.keyDown(Key.D))
            movement = movement.add(1, 0);
        movement = movement.getNormalized().multiply(getSpeed());
        setPosition(getPosition().add(movement));
        setBounds(getBounds().clamp(SpellCast.INSTANCE.getMapSize()));
        SpellCast.INSTANCE.setCameraPosition(getCenter());
    }
    @Override
    public void render(Renderer r) {
        r.fillRect(getBounds(), RGBAColor.GREEN);
        super.render(r);
    }
}
