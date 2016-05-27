package com.spellcastrpg.main.objects.spells;

import com.spellcastrpg.main.items.Item;
import com.spellcastrpg.main.items.ItemType;
import com.spellcastrpg.main.items.SpellItem;
import com.spellcastrpg.main.rendering.ImageUtils;
import com.spellcastrpg.main.rendering.RGBAColor;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SpellUtils {
    private SpellUtils() {}

    public static BufferedImage colorPowderSpell(RGBAColor color) {
        BufferedImage image = SpellItem.POWDER_SPELL;
        // clone image
        BufferedImage colored = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        Graphics2D g2d = colored.createGraphics();
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();

        if (color == null)
            return colored;

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                RGBAColor pixel = RGBAColor.fromColor(new Color(image.getRGB(x, y), true));
                if (!pixel.isGrayscale() || pixel.getA() == 0)
                    continue;
                double distance = pixel.getDistance(color);
                colored.setRGB(x, y, color.multiply(distance).toColor().getRGB());
            }
        }
        return colored;
    }

    public static RGBAColor getColor(ItemType base, Set<ItemType> modifiers) {
        List<RGBAColor> colors = new ArrayList<>();
        // weight the base item heavier
        RGBAColor baseAvgColor = ImageUtils.getAverageColor(base.getImage());
        // +1 so it doesn't do Math.sqrt(0) if there are no modifiers
        colors.add(baseAvgColor.multiply(Math.sqrt(modifiers.size() + 1)));

        for (ItemType modifier : modifiers)
            if (modifier.getImage() != null)
                colors.add(ImageUtils.getAverageColor(modifier.getImage()));
        return RGBAColor.average(colors);
    }

    // solid if it should be powdered spell
    // liquid if it should be in a bowl or whatever
    public static Item.State getSpellState(Set<ItemType> modifiers) {
        boolean liquid = false;
        for (ItemType modifier : modifiers) {
            if (modifier.getState() == Item.State.LIQUID)
                liquid = true;
            else if (modifier.getState() == Item.State.SOLID && liquid)
                return Item.State.PASTE;
        }
        return liquid ? Item.State.LIQUID : Item.State.SOLID;
    }
}
