package net.silverfishstone.wolfworld.util.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.silverfishstone.wolfworld.WolfWorld;
import net.silverfishstone.wolfworld.resources.enchantments.WolfEnchantments;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class EnchantmentProviders extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.ENCHANTMENT, WolfEnchantments::bootstrap);


    public EnchantmentProviders(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, Set<String> modIds) {
        super(output, registries, BUILDER,Set.of(WolfWorld.MODID));
    }
}

