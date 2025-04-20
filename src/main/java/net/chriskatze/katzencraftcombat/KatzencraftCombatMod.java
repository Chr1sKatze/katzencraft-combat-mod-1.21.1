package net.chriskatze.katzencraftcombat;

import net.chriskatze.katzencraftcombat.content.ModSpells;
import net.chriskatze.katzencraftcombat.item.ModItemGroups;
import net.chriskatze.katzencraftcombat.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KatzencraftCombatMod implements ModInitializer {
	public static final String MOD_ID = "katzencraftcombat";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// THIS CODE RUNS AS SOON AS MINECRAFT IS IN A MOD-LOAD-READY STATE --------------------------------------------
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModSpells.registerModSpells();
	}
}