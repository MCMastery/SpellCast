package com.spellcastrpg.main.objects;

import com.spellcastrpg.main.Input;
import com.spellcastrpg.main.Key;
import com.spellcastrpg.main.SpellCast;
import com.spellcastrpg.main.geometry.Rectangle;
import com.spellcastrpg.main.geometry.Vector2d;
import com.spellcastrpg.main.items.ItemObject;
import com.spellcastrpg.main.items.ItemType;
import com.spellcastrpg.main.items.SpellItem;
import com.spellcastrpg.main.items.Tome;
import com.spellcastrpg.main.items.ingredients.*;
import com.spellcastrpg.main.map.RenderedMapTile;
import com.spellcastrpg.main.objects.gui.GUIContainer;
import com.spellcastrpg.main.objects.gui.Inventory;
import com.spellcastrpg.main.objects.gui.TestGUI;
import com.spellcastrpg.main.objects.gui.Workbench;
import com.spellcastrpg.main.objects.spells.SpellType;
import com.spellcastrpg.main.rendering.Animation;
import com.spellcastrpg.main.rendering.Renderer;

/**
 * Created by laser_000 on 5/14/2016.
 */
public class Player extends LivingObject {
    private Inventory inventory;
    private Animation animation;
    private GUIContainer openedGUI;

    public Player() {
        setBounds(new Rectangle(0, 0, 52, 100));
        setCenter(SpellCast.INSTANCE.getMapCenter());
        setSpeed(4);
        setMaxHealth(100);
        setHealth(100);
        this.animation = SpellCast.loadAnimation("wizard.png", 10, 30);
        this.inventory = new Inventory();
        this.openedGUI = null;
    }

    public Inventory getInventory() {
        return this.inventory;
    }
    public ItemObject getSelectedItem() {
        return this.inventory.getSelectedItem();
    }
    public boolean addItem(ItemObject item) {
        return this.inventory.addItem(item);
    }
    public GUIContainer getOpenedGUI() {
        return this.openedGUI;
    }
    // also instantiates the given gui
    public void setOpenedGUI(GUIContainer gui) {
        if (this.openedGUI != null)
            this.openedGUI.destroy();
        this.openedGUI = gui;
        if (gui != null)
            gui.init();
    }
    public void closeGUI() {
        if (this.openedGUI != null)
            this.openedGUI.destroy();
        this.openedGUI = null;
    }


    @Override
    public void init() {
        super.init();
        this.inventory.init();
        addItem(new SpellItem(SpellType.WIND, ItemType.ROSEWOOD_EMBERS));
        addItem(new CaperhornLeaf());
        addItem(new GanckleTreeNut());
        addItem(new Glowstone());
        addItem(new GoberFishEye());
        addItem(new ManagotRoot());
        addItem(new PhoenixFeather());
        addItem(new QuatasiumJade());
        addItem(new RosewoodEmbers());
        addItem(new Tome());
    }
    @Override
    public void update() {
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
        translate(movement);
        setBounds(getBounds().clamp(SpellCast.INSTANCE.getMapSize()));
        SpellCast.INSTANCE.setCameraPosition(getCenter());
        if (!movement.equals(Vector2d.ZERO))
            this.animation.update();
    }
    @Override
    public void keyDown(Key key) {
        if (key == Key.E) {
            if (!(this.openedGUI instanceof Workbench))
                setOpenedGUI(new Workbench());
        } else if (key == Key.R) {
            if (!(this.openedGUI instanceof TestGUI))
                setOpenedGUI(new TestGUI());
        } else if (key == Key.K) {
            for (GameObject object : SpellCast.INSTANCE.getObjects())
                if (object instanceof Enemy)
                    object.destroy();
        }
    }
    @Override
    public void collide(Collider object) {
        if (object instanceof RenderedMapTile && ((RenderedMapTile) object).getTile().isSolid())
            cancelCollision(object);
    }
    @Override
    public void render(Renderer r) {
        //r.fillRect(getBounds(), RGBAColor.GREEN);
        r.drawAnimationFrame(this.animation, getPosition());
        super.render(r);
    }
}
