/*
 * Copyright (c) NeoForged and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */

package net.neoforged.neoforge.oldtest;

import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.fml.ModList;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.internal.versions.neoforge.NeoForgeVersion;
import net.neoforged.testframework.DynamicTest;
import net.neoforged.testframework.annotation.ForEachTest;
import net.neoforged.testframework.annotation.TestHolder;

@ForEachTest(groups = "custom_flag_tests")
public interface FlagTest {
    @TestHolder(description = "Tests custom flag system")
    static void test(DynamicTest test) {
        var namespace = test.createModId();
        var registration = test.registrationHelper();
        var items = registration.items();
        // var modContainer = ModList.get().getModContainerById(namespace).orElseThrow();
        var modContainer = ModList.get().getModContainerById(NeoForgeVersion.MOD_ID).orElseThrow();

        var configBuilder = new ModConfigSpec.Builder();
        var testFlag = configBuilder.comment("Test flag to toggle on/off various game elements").define("my_flag", false);
        var config = configBuilder.build();
        modContainer.registerConfig(ModConfig.Type.SERVER, config, namespace + ".toml");

        // register various elements which require our flag
        items.registerSimpleItem("flagged_item", new Item.Properties().requiredFlag(testFlag));

        // block disabled via matching block item
        var flaggedBlock = registration.blocks().registerSimpleBlock("flagged_block", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).requiredFlag(testFlag));
        items.registerSimpleBlockItem(flaggedBlock);

        // spawn egg disabled via matching entity type
        var flaggedEntity = registration.entityTypes().registerType("flagged_entity", () -> EntityType.Builder
                .of(DummyEntity::new, MobCategory.MISC)
                .requiredFlag(testFlag)).withRenderer(() -> NoopRenderer::new).withAttributes(Mob::createMobAttributes);

        items.registerItem("flagged_entity_egg", properties -> new DeferredSpawnEggItem(flaggedEntity, 0, 0, properties));
    }

    final class DummyEntity extends Mob {
        DummyEntity(EntityType<? extends Mob> entityType, Level level) {
            super(entityType, level);
        }
    }
}
