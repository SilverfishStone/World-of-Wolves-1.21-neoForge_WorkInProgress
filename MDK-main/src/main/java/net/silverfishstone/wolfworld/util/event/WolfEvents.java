package net.silverfishstone.wolfworld.util.event;


import net.minecraft.client.telemetry.events.WorldLoadEvent;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.AnimalTameEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.silverfishstone.wolfworld.WolfConfig;
import net.silverfishstone.wolfworld.WolfWorld;
import net.silverfishstone.wolfworld.resources.item.WolfItems;
import net.silverfishstone.wolfworld.resources.item.custom.WolfBrushItem;
import net.silverfishstone.wolfworld.util.WolfTags;

import static net.minecraft.world.entity.LivingEntity.getSlotForHand;

@EventBusSubscriber(modid = WolfWorld.MODID)
public class WolfEvents {

    @SubscribeEvent
    public static void creatureTamed (AnimalTameEvent event) {
        Animal animal = event.getAnimal();
        EntityType<?> type = animal.getType();
        Player player = event.getTamer();
        if (WolfConfig.wolvesCustom && type.equals(EntityType.WOLF)) {
            wolfBrush(player, (Wolf) animal);
        }
    }

    public static void wolfBrush(Player player, Wolf wolf) {
        WolfBrushItem.spawnBrushType(wolf.getVariant().getRegisteredName(), wolf);
    }

    @SubscribeEvent
    public static void wolfBrushUse (PlayerInteractEvent.EntityInteract event) {
        if (event.getTarget().getType().is(WolfTags.EntityTypes.WOLVES)) {
            Entity animal = event.getTarget();
            Player player = event.getEntity();
            InteractionHand hand = event.getHand();
            ItemStack stack = player.getMainHandItem();
            Wolf wolf = (Wolf) animal;
            wolfBrushify(stack, wolf, player);
        }
    }
    public static void wolfBrushify (ItemStack stack, Wolf wolf, Player player) {
        if (WolfConfig.wolvesCustom && stack.getItem() instanceof WolfBrushItem) {
            String textstring = WolfBrushItem.getBrushTypeString(stack);
            if (!textstring.equals("item.wolfworld.wolf_brush_empty")) {
                WolfBrushItem.setWolfType(stack, wolf, player);
            } else {
                WolfBrushItem.setBrushType(wolf.getVariant().getRegisteredName(), stack, player);
            }
        }
    }
}
