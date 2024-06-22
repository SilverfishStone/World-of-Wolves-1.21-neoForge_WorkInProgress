package net.silverfishstone.wolfworld.util.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.silverfishstone.wolfworld.WolfWorld;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = WolfWorld.MODID, bus = EventBusSubscriber.Bus.MOD)
public class DataGens {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> LookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeClient(), new WolfItemModelsGen(packOutput, WolfWorld.MODID, existingFileHelper));

        generator.addProvider(event.includeServer(), new EnchantmentProviders(packOutput, LookupProvider, Collections.singleton(WolfWorld.MODID)));
    }
}
