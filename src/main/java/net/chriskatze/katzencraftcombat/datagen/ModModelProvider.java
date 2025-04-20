package net.chriskatze.katzencraftcombat.datagen;

import net.chriskatze.katzencraftcombat.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

        // GENERATES STANDARD BLOCK MODELS WITH PROVIDED TEXTURE -------------------------------------------------------

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

        // GENERATES STANDARD ITEM MODELS WITH PROVIDED TEXTURE --------------------------------------------------------
        itemModelGenerator.register(ModItems.STEEL_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.STEEL_SHORTSWORD, Models.HANDHELD);
    }
}
