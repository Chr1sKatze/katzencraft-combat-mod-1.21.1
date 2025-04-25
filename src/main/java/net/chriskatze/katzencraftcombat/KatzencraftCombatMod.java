package net.chriskatze.katzencraftcombat;

import net.chriskatze.katzencraftcombat.content.ModSpells;
import net.chriskatze.katzencraftcombat.item.ModItemGroups;
import net.chriskatze.katzencraftcombat.item.ModItems;
import net.fabricmc.api.ModInitializer;

import net.spell_engine.api.config.WeaponConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class KatzencraftCombatMod implements ModInitializer {
	public static final String MOD_ID = "katzencraftcombat";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// THIS CODE RUNS AS SOON AS MINECRAFT IS IN A MOD-LOAD-READY STATE --------------------------------------------
		ModItemGroups.registerItemGroups();
		Map<String, WeaponConfig> weaponConfigs = new HashMap<>();
		ModItems.registerModItems(weaponConfigs);
		ModSpells.registerModSpells();
	}
}