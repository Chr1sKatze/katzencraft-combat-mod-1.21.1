package net.chriskatze.katzencraftcombat.content;

import net.chriskatze.katzencraftcombat.KatzencraftCombatMod;
import net.minecraft.util.Identifier;
import net.spell_engine.api.spell.Spell;
import net.spell_engine.api.spell.fx.ParticleBatch;
import net.spell_engine.api.spell.fx.Sound;
import net.spell_engine.api.util.TriState;
import net.spell_engine.client.gui.SpellTooltip;
import net.spell_engine.fx.SpellEngineParticles;
import net.spell_engine.fx.SpellEngineSounds;
import net.spell_power.api.SpellSchools;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ModSpells {

    private static Spell activeSpellBase() {
        var spell = new Spell();
        spell.type = Spell.Type.ACTIVE;
        spell.active = new Spell.Active();
        spell.active.cast = new Spell.Active.Cast();

        spell.learn = new Spell.Learn();
        spell.active.scroll = new Spell.Active.Scroll();

        return spell;
    }
    public record Entry(Identifier id, Spell spell, String title, String description,
                        @Nullable SpellTooltip.DescriptionMutator mutator) { }
    public static final List<Entry> entries = new ArrayList<>();
    private static Entry add(Entry entry) {
        entries.add(entry);
        return entry;
    }

    private static ParticleBatch castingParticles(String particleId) {
        return new ParticleBatch(
                particleId,
                ParticleBatch.Shape.WIDE_PIPE, ParticleBatch.Origin.FEET,
                1, 0.05F, 0.1F);
    }
    private static final Identifier HOLY_SPARKS = SpellEngineParticles.getMagicParticleVariant(
            SpellEngineParticles.HOLY,
            SpellEngineParticles.MagicParticleFamily.Shape.SPARK,
            SpellEngineParticles.MagicParticleFamily.Motion.FLOAT
    ).id();

    private static final String GROUP_PRIMARY = "primary";

    private static Spell.Impact createHeal(float coefficient) {
        var buff = new Spell.Impact();
        buff.action = new Spell.Impact.Action();
        buff.action.type = Spell.Impact.Action.Type.HEAL;
        buff.action.heal = new Spell.Impact.Action.Heal();
        buff.action.heal.spell_power_coefficient = coefficient;
        return buff;
    }

    private static void impactDeniedForMechanical(Spell.Impact impact) {
        var modifier = createImpactModifier("#spell_engine:mechanical");
        modifier.execute = TriState.DENY;
        impact.target_modifiers = List.of(modifier);
    }
    private static Spell.Impact.TargetModifier createImpactModifier(String entityType) {
        var condition = new Spell.TargetCondition();
        condition.entity_type = entityType;
        var modifier = new Spell.Impact.TargetModifier();
        modifier.conditions = List.of(condition);
        return modifier;
    }
    // |||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

    public static final Entry HEAL = add(heal());
    private static Entry heal() {
        var id = Identifier.of(KatzencraftCombatMod.MOD_ID, "heal");
        var title = "Heal";
        var description = "";

        var spell = activeSpellBase();
        spell.school = SpellSchools.HEALING;
        spell.group = GROUP_PRIMARY;
        spell.range = 16;
        spell.tier = 0;

        spell.learn = null;
        spell.active.scroll = null;

        spell.active.cast.duration = 1F;
        spell.active.cast.animation = "spell_engine:one_handed_healing_charge";
        spell.active.cast.sound = Sound.withRandomness(SpellEngineSounds.GENERIC_HEALING_CASTING.id(), 0);
        spell.active.cast.particles = new ParticleBatch[] {
                castingParticles(HOLY_SPARKS.toString())
        };

        spell.release.animation = "spell_engine:one_handed_healing_release";
        spell.release.sound = new Sound(SpellEngineSounds.GENERIC_HEALING_RELEASE.id());

        spell.target.type = Spell.Target.Type.AIM;
        spell.target.aim = new Spell.Target.Aim();
        spell.target.aim.use_caster_as_fallback = true;

        var heal = createHeal(0.8F);
        impactDeniedForMechanical(heal);
        heal.sound = new Sound(SpellEngineSounds.GENERIC_HEALING_IMPACT_1.id());
//        heal.particles = new ParticleBatch[] {
//                new ParticleBatch(
//                        HEALING_PARTICLES.toString(),
//                        ParticleBatch.Shape.PILLAR, ParticleBatch.Origin.FEET,
//                        20, 0.02F, 0.15F)
//        };
        spell.impacts = List.of(heal);

        configureCooldown(spell, 4);
//        configureItemCost(spell, "runes:healing_stone");

        return new Entry(id, spell, title, description, null);
    }
//    private static final Identifier HEALING_PARTICLES = SpellEngineParticles.getMagicParticleVariant(
//            SpellEngineParticles.NATURE,
//            SpellEngineParticles.MagicParticleFamily.Shape.IMPACT,
//            SpellEngineParticles.MagicParticleFamily.Motion.ASCEND
//    ).id();

    private static void configureCooldown(Spell spell, float duration) {
        if (spell.cost == null) {
            spell.cost = new Spell.Cost();
        }
        spell.cost.cooldown = new Spell.Cost.Cooldown();
        spell.cost.cooldown.duration = duration;
    }

//    private static void configureItemCost(Spell spell, String itemId) {
//        if (spell.cost == null) {
//            spell.cost = new Spell.Cost();
//        }
//        spell.cost.item = new Spell.Cost.Item();
//        spell.cost.item.id = itemId;
//    }

    // |||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

    // USED FOR INITIALIZATION AND LOGGING OF THE SPELLS CLASS ---------------------------------------------------------
    public static void registerModSpells() {
        KatzencraftCombatMod.LOGGER.info("Registering ModSpells for " + KatzencraftCombatMod.MOD_ID);
    }
}