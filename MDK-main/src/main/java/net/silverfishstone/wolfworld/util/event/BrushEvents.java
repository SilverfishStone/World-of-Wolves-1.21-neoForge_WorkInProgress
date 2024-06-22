package net.silverfishstone.wolfworld.util.event;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.silverfishstone.wolfworld.WolfWorld;
import net.silverfishstone.wolfworld.resources.item.WolfItems;
import net.silverfishstone.wolfworld.resources.item.custom.WolfBrushItem;

import java.util.List;

@EventBusSubscriber(modid = WolfWorld.MODID)
public class BrushEvents {

    @SubscribeEvent
    public static void addTypeName (ItemTooltipEvent event) {
        List<Component> tooltip = event.getToolTip();
        ItemStack brush = event.getItemStack();
        if (brush.is(WolfItems.WOlF_BRUSH)) {
            addName(tooltip, brush);
        }
    }
    public static void addName (List<Component> tooltip, ItemStack item) {
        tooltip.add(1, Component.translatable(WolfBrushItem.getBrushTypeName(item)).withStyle(ChatFormatting.GRAY));
    }
}