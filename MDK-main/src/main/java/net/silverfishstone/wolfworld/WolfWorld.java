package net.silverfishstone.wolfworld;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.silverfishstone.wolfworld.resources.block.WolfBlocks;
import net.silverfishstone.wolfworld.resources.item.WolfItems;
import net.silverfishstone.wolfworld.resources.item.custom.WolfBrushItem;
import net.silverfishstone.wolfworld.util.components.WolfComponents;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

@Mod(WolfWorld.MODID)
public class WolfWorld
{
    public static final String MODID = "wolfworld";
    private static final Logger LOGGER = LogUtils.getLogger();


    public WolfWorld(IEventBus modEventBus, ModContainer modContainer)
    {
        modEventBus.addListener(this::commonSetup);

        WolfBlocks.register(modEventBus);
        WolfItems.register(modEventBus);
        WolfItems.CREATIVE_MODE_TABS.register(modEventBus);
        WolfComponents.REGISTRAR.register(modEventBus);

        NeoForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);

        modContainer.registerConfig(ModConfig.Type.COMMON, WolfConfig.SPEC);
    }

    public static void removeItemUnlessCreative (Player player, ItemStack stack) {
        if (!player.isCreative()) {
            stack.shrink(1);
        }
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (WolfConfig.wolvesCustom)
            LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));

        LOGGER.info(WolfConfig.magicNumberIntroduction + WolfConfig.magicNumber);

        WolfConfig.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS)
            event.accept(WolfItems.WOlF_BRUSH);
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        LOGGER.info("HELLO from server starting");
    }

    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());

            event.enqueueWork(() -> {
                ItemProperties.register(
                        WolfItems.WOlF_BRUSH.get(),
                        ResourceLocation.fromNamespaceAndPath(WolfWorld.MODID, "wolf_brush_type"),
                        // A reference to a method that calculates the override value.
                        // Parameters are the used item stack, the level context, the player using the item,
                        // and a random seed you can use.
                        (stack, level, player, seed) -> (WolfBrushItem.brushtypeFloat(stack))
                );
            });
        }
    }
}
