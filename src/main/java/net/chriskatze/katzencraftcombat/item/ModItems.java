package net.chriskatze.katzencraftcombat.item;

import net.chriskatze.katzencraftcombat.KatzencraftCombatMod;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    // COMBAT ----------------------------------------------------------------------------------------------------------
    public static final Item STEEL_SHORTSWORD = registerItem("steel_shortsword",
            new SwordItem(ModToolMaterials.STEEL,
                    new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.STEEL,3,-2.4f))));

    // MATERIALS -------------------------------------------------------------------------------------------------------
    public static final Item STEEL_INGOT = registerItem("steel_ingot", new Item(new Item.Settings()));

    // |||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

    // USED TO REGISTER  MOD ITEMS -------------------------------------------------------------------------------------
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(KatzencraftCombatMod.MOD_ID, name), item);
    }

    // USED FOR INITIALIZATION AND LOGGING OF THE MODITEMS CLASS -------------------------------------------------------
    public static void registerModItems() {
        KatzencraftCombatMod.LOGGER.info("Registering ModItems for " + KatzencraftCombatMod.MOD_ID);
    }
}
