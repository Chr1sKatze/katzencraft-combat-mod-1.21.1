package net.chriskatze.katzencraftcombat;

import net.chriskatze.katzencraftcombat.datagen.ModItemTagProvider;
import net.chriskatze.katzencraftcombat.datagen.ModModelProvider;
import net.chriskatze.katzencraftcombat.datagen.ModRecipeGenerator;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;

public class KatzencraftCombatModDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		// INITIALIZES DATAGENERATION PACKS ----------------------------------------------------------------------------
		pack.addProvider(ModItemTagProvider::new);
		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModRecipeGenerator::new);
	}

	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {

	}
}