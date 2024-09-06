/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */

package net.neoforged.neoforge.common.extensions;

import java.util.function.BooleanSupplier;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.network.IContainerFactory;

public interface IMenuTypeExtension<T> {
    /**
     * Creates new menu type which is marked as requiring a specified flag.
     * <p>
     * Best used in conjunction with server config files.
     * <p>
     * The value of the provided supplier must be kept in sync with the server and client at all times.
     *
     * @param requiredFlag Flag which must result in {@code true} in order for this menu type to be enabled.
     */
    static <T extends AbstractContainerMenu> MenuType<T> create(IContainerFactory<T> factory, BooleanSupplier requiredFlag) {
        return new MenuType<>(factory, FeatureFlags.DEFAULT_FLAGS, requiredFlag);
    }

    static <T extends AbstractContainerMenu> MenuType<T> create(IContainerFactory<T> factory) {
        return create(factory, () -> true);
    }

    T create(int windowId, Inventory playerInv, RegistryFriendlyByteBuf extraData);
}
