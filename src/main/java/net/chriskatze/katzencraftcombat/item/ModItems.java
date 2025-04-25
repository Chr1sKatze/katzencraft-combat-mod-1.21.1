package net.chriskatze.katzencraftcombat.item;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import net.chriskatze.katzencraftcombat.KatzencraftCombatMod;

import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import net.minecraft.util.Lazy;
import net.spell_engine.api.config.AttributeModifier;
import net.spell_engine.api.config.WeaponConfig;
import net.spell_engine.api.item.Equipment;
import net.spell_engine.api.item.weapon.SpellSwordItem;
import net.spell_engine.api.item.weapon.Weapon;
import net.spell_power.api.SpellSchools;

import java.util.ArrayList;
import java.util.Map;

public class ModItems {

    public static final ArrayList<Weapon.Entry> entries = new ArrayList<>();

    public static class WeaponItem {

        public Item ITEM;
        public Weapon.Entry ENTRY;

        // Constructor Overload: Constructor only used to have fewer parameters than original one
        public WeaponItem(String name,
                          Weapon.CustomMaterial material,
                          Ingredient repairIngredient,
                          float attackDamage,
                          float attackSpeed,
                          Identifier spellSchoolId,
                          float spellBonus) {
            // call original constructor with last parameter (Item.Settings)
            this(name, material, repairIngredient, attackDamage, attackSpeed, spellSchoolId, spellBonus, new Item.Settings());
        }

        // Wenn das nicht geht -> Mario ist schuld!
        public WeaponItem(String name,
                          ToolMaterials vanillaMaterial,
                          Ingredient repairIngredient,
                          float attackDamage,
                          float attackSpeed,
                          Identifier spellSchoolId,
                          float spellBonus,
                          Item.Settings settings) {
            // example call: new WeaponItem(name, ToolMaterials.DIAMOND, Items.DIAMOND, attackDamage, attackSpeed, spellSchoolId, spellBonus, settings);
            // call original constructor with ToolMaterials
            this(name, Weapon.CustomMaterial.matching(vanillaMaterial, () -> repairIngredient), repairIngredient, attackDamage, attackSpeed, spellSchoolId, spellBonus, settings);
        }

        // Constructor (original)
        public WeaponItem(String name,
                          Weapon.CustomMaterial material,
                          Ingredient repairIngredient,
                          float attackDamage,
                          float attackSpeed,
                          Identifier spellSchoolId,
                          float spellBonus,
                          Item.Settings settings) {

            // SPELLSWORD CREATOR ----------------------------------------------------------------------------------------------
            var entry = new Weapon.Entry(
                    KatzencraftCombatMod.MOD_ID,
                    name,
                    material,
                    SpellSwordItem::new,
                    new WeaponConfig(attackDamage, attackSpeed),
                    Equipment.WeaponType.SWORD
            );

            // Set the spell attributes
            entry.attribute(AttributeModifier.bonus(spellSchoolId, spellBonus));
            entries.add(entry);

            // Set member variables in class
            this.ITEM = registerItem(name, new Item(settings)); // be careful: settings cannot be null!
            this.ENTRY = entry;
        }
    }

    // |||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

    // MATERIALS
    public static final Item STEEL_INGOT = registerItem("steel_ingot", new Item(new Item.Settings()));

    // COMBAT
    // -----------------------------------------------------------------------------------------------------------------

    public static final WeaponItem STEEL_SHORTSWORD = new WeaponItem(
            "steel_shortsword",
            ModToolMaterials.STEEL,
            Ingredient.ofItems(Items.DIAMOND),
            3.0F,
            -2.8F,
            SpellSchools.HEALING.id,
            2.0F
            // new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.DIAMOND,3,-3.0f))
            // wenn keine custom attribute verwendet werden sollen
    );

    // |||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

    // USED FOR INITIALIZATION AND LOGGING OF THE MODITEMS CLASS -------------------------------------------------------
    public static void registerModItems(Map<String, WeaponConfig> configs) {
        Weapon.register(configs, entries, ItemGroups.COMBAT);
        KatzencraftCombatMod.LOGGER.info("Registering ModItems for " + KatzencraftCombatMod.MOD_ID);
    }

    // USED TO REGISTER  MOD ITEMS -------------------------------------------------------------------------------------
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(KatzencraftCombatMod.MOD_ID, name), item);
    }
}