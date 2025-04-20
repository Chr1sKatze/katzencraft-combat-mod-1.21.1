package net.chriskatze.katzencraftcombat.item;

import net.chriskatze.katzencraftcombat.KatzencraftCombatMod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;

public class ModItemGroups {

    // ADDS ITEMS INTO THE CORRESPONDING VANILLA CREATIVE TAB GROUPS ---------------------------------------------------
    private static void Mod_Ingredients(FabricItemGroupEntries entries) {
        entries.add(ModItems.STEEL_INGOT);
    }

    private static void Mod_Tools(FabricItemGroupEntries entries) {

    }

    private static void Mod_Combat(FabricItemGroupEntries entries) {
        entries.add(ModItems.STEEL_SHORTSWORD);
    }

    // |||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

    // USED FOR INITIALIZATION AND LOGGING OF THE MOD ITEM GROUPS CLASS ------------------------------------------------
    public static void registerItemGroups() {
        KatzencraftCombatMod.LOGGER.info("Registering Item Groups for " + KatzencraftCombatMod.MOD_ID);

        // USED TO MODIFY THE VANILLA CREATIVE TAB ENTRIES -------------------------------------------------------------
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItemGroups::Mod_Ingredients);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(ModItemGroups::Mod_Tools);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(ModItemGroups::Mod_Combat);
    }
}
