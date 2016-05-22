package com.spellcastrpg.main.objects.spells;

import com.spellcastrpg.main.items.Item;
import com.spellcastrpg.main.items.ingredients.Ingredient;
import com.spellcastrpg.main.rendering.RGBAColor;
import com.spellcastrpg.main.rendering.Renderer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SpellUtils {
    private SpellUtils() {}


    public static RGBAColor getColor(Ingredient base, Set<Ingredient> modifiers) {
        List<RGBAColor> colors = new ArrayList<>();
        // weight the base item heavier
        RGBAColor baseAvgColor = Renderer.getAverageColor(base.getImage());
        colors.add(baseAvgColor);
        colors.add(baseAvgColor);

        for (Ingredient modifier : modifiers)
            colors.add(Renderer.getAverageColor(modifier.getImage()));
        return RGBAColor.average(colors);
    }

    // solid if it should be powdered spell
    // liquid if it should be in a bowl or whatever
    public static Item.State getSpellState(Set<Ingredient> modifiers) {
        boolean liquid = false;
        for (Ingredient modifier : modifiers) {
            if (modifier.getState() == Item.State.LIQUID)
                liquid = true;
            else if (modifier.getState() == Item.State.SOLID && liquid)
                return Item.State.PASTE;
        }
        return liquid ? Item.State.LIQUID : Item.State.SOLID;
    }
}
