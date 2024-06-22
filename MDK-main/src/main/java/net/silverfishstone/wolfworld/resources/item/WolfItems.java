package net.silverfishstone.wolfworld.resources.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.silverfishstone.wolfworld.WolfWorld;
import net.silverfishstone.wolfworld.resources.item.armor.CustomAnimalArmorItem;
import net.silverfishstone.wolfworld.resources.item.armor.WolfMaterials;
import net.silverfishstone.wolfworld.resources.item.custom.WolfBrushItem;
import net.silverfishstone.wolfworld.resources.item.custom.WolfBrushItem.WolfBrushType;

public class WolfItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(WolfWorld.MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, WolfWorld.MODID);

    public static final DeferredItem<Item> WOlF_BRUSH = ITEMS.register("wolf_brush", () -> new WolfBrushItem(new Item.Properties()));



    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> WOLF_TAB = CREATIVE_MODE_TABS.register("wolf_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.wolfworld")) //The language key for the title of your CreativeModeTab
            .icon(() -> WOlF_BRUSH.get().getDefaultInstance())
            .displayItems((parameters, output) -> {

                output.accept(WOlF_BRUSH.get());

            }).build());

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
