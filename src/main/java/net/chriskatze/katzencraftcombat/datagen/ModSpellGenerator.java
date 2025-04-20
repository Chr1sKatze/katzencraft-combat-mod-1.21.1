package net.chriskatze.katzencraftcombat.datagen;

import net.chriskatze.katzencraftcombat.content.ModSpells;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.registry.RegistryWrapper;
import net.spell_engine.api.datagen.SpellGenerator;

import java.util.concurrent.CompletableFuture;

public class ModSpellGenerator extends SpellGenerator {

    public ModSpellGenerator(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateSpells(Builder builder) {
        for (var entry: ModSpells.entries) {
            builder.add(entry.id(), entry.spell());
        }
    }
}