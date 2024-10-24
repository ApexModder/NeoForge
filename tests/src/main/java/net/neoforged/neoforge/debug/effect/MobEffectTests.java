/*
 * Copyright (c) NeoForged and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */

// FIXME: effect cures need to be implemented differently and the test adapted
/*
package net.neoforged.neoforge.debug.effect;


@ForEachTest(groups = MobEffectTests.GROUP)
public class MobEffectTests {
    public static final String GROUP = "level.effect";

    @GameTest
    @EmptyTemplate
    @TestHolder(description = "Tests whether items and effects can properly specify what they cure and what they are cured by respectively")
    static void effectCures(final DynamicTest test, final RegistrationHelper reg) {
        final var testCure = EffectCure.get("test_cure");
        final var testCureTwo = EffectCure.get("test_cure_two");

        final var testEffect = reg.registrar(Registries.MOB_EFFECT).register("test_effect", () -> new MobEffect(
                MobEffectCategory.HARMFUL, 0xFF0000) {
            @Override
            public void fillEffectCures(Set<EffectCure> cures, MobEffectInstance effectInstance) {
                super.fillEffectCures(cures, effectInstance);
                cures.remove(EffectCures.MILK);
                cures.add(testCureTwo);
            }
        });

        test.eventListeners().forge().addListener((MobEffectEvent.Added event) -> {
            if (event.getEffectInstance().getEffect() == MobEffects.NIGHT_VISION) {
                event.getEffectInstance().getCures().add(testCure);
            }
        });

        test.onGameTest(helper -> {
            Pig pig = helper.spawnWithNoFreeWill(EntityType.PIG, 1, 1, 1);

            pig.addEffect(new MobEffectInstance(MobEffects.CONFUSION));
            helper.assertMobEffectPresent(pig, MobEffects.CONFUSION, "'confusion was applied'");
            pig.removeEffectsCuredBy(testCure);
            helper.assertMobEffectPresent(pig, MobEffects.CONFUSION, "'confusion not removed by test cure'");
            pig.removeEffectsCuredBy(EffectCures.MILK);
            helper.assertMobEffectAbsent(pig, MobEffects.CONFUSION, "'confusion removed by milk'");

            pig.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION));
            helper.assertMobEffectPresent(pig, MobEffects.NIGHT_VISION, "'nightvision was applied'");
            pig.removeEffectsCuredBy(testCure);
            helper.assertMobEffectAbsent(pig, MobEffects.NIGHT_VISION, "'nightvision removed by test cure'");

            pig.addEffect(new MobEffectInstance(testEffect));
            helper.assertMobEffectPresent(pig, testEffect, "'test effect was applied'");
            pig.removeEffectsCuredBy(EffectCures.MILK);
            helper.assertMobEffectPresent(pig, testEffect, "'test effect not removed by milk'");
            pig.removeEffectsCuredBy(testCureTwo);
            helper.assertMobEffectAbsent(pig, testEffect, "'test effect removed by test cure'");

            MobEffectInstance srcInst = new MobEffectInstance(MobEffects.CONFUSION);
            MobEffectInstance destInst = MobEffectInstance.load((CompoundTag) srcInst.save());
            helper.assertTrue(srcInst.getCures().equals(destInst.getCures()), "'MobEffectInstance serialization roundtrip (standard cures)'");

            srcInst.getCures().add(testCure);
            destInst = MobEffectInstance.load((CompoundTag) srcInst.save());
            helper.assertTrue(srcInst.getCures().equals(destInst.getCures()), "'MobEffectInstance serialization roundtrip (custom additional cure)'");

            srcInst.getCures().clear();
            destInst = MobEffectInstance.load((CompoundTag) srcInst.save());
            helper.assertTrue(srcInst.getCures().equals(destInst.getCures()), "'MobEffectInstance serialization roundtrip (no cures)'");

            helper.succeed();
        });
    }
}
*/
