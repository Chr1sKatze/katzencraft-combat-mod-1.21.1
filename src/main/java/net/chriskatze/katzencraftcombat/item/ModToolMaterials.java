package net.chriskatze.katzencraftcombat.item;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import net.chriskatze.katzencraftcombat.util.ModTags;
import net.minecraft.block.Block;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.TagKey;
import net.spell_engine.api.item.weapon.Weapon;

public class ModToolMaterials extends Weapon.CustomMaterial {

        public static ModToolMaterials STEEL = new ModToolMaterials(ModTags.Blocks.INCORRECT_FOR_STEEL_TOOL, 59, 2.0F, 0.0F, 15, () -> Ingredient.ofItems(ModItems.STEEL_INGOT));

        private final TagKey<Block> inverseTag;
        private final int itemDurability;
        private final float miningSpeed;
        private final float attackDamage;
        private final int enchantability;
        private final Supplier<Ingredient> repairIngredient;

        ModToolMaterials(
                final TagKey<Block> inverseTag,
                final int itemDurability,
                final float miningSpeed,
                final float attackDamage,
                final int enchantability,
                final Supplier<Ingredient> repairIngredient
        ) {
            this.inverseTag = inverseTag;
            this.itemDurability = itemDurability;
            this.miningSpeed = miningSpeed;
            this.attackDamage = attackDamage;
            this.enchantability = enchantability;
            this.repairIngredient = Suppliers.memoize(repairIngredient::get);
        }

        @Override
        public int getDurability() {
            return this.itemDurability;
        }

        @Override
        public float getMiningSpeedMultiplier() {
            return this.miningSpeed;
        }

        @Override
        public float getAttackDamage() {
            return this.attackDamage;
        }

        @Override
        public TagKey<Block> getInverseTag() {
            return this.inverseTag;
        }

        @Override
        public int getEnchantability() {
            return this.enchantability;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return this.repairIngredient.get();
        }
}