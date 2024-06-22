package net.silverfishstone.wolfworld.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.silverfishstone.wolfworld.WolfWorld;

public class WolfTags {
    public static class Blocks {

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(ResourceLocation.parse(name));
        }
    }

    public static class Items {

        public static final TagKey<Item> WOLF_ARMORS = tag(WolfWorld.MODID + ":" + "wolf_armor");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(ResourceLocation.parse(name));
        }
    }

    public static class Effects {


        private static TagKey<MobEffect> tag(String name) {
            return WolfTags.createEffect(ResourceLocation.parse(name));
        }
    }
    public static TagKey<MobEffect> createEffect(final ResourceLocation name) {
        return TagKey.create(Registries.MOB_EFFECT, name);
    }

    public static class EntityTypes {
        public static final TagKey<EntityType<?>> WOLVES = create(WolfWorld.MODID + ":" + "wolves");

    }
    private static TagKey<EntityType<?>> create(String pName) {
        return TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse(pName));
    }

    public static class Enchantments {
        public static final TagKey<Enchantment> WOLF_ARMOR_ATTACK_EXCLUSIVE = createEnchant(WolfWorld.MODID + ":" + "wolf_armor");

    }
    private static TagKey<Enchantment> createEnchant(String pName) {
        return TagKey.create(Registries.ENCHANTMENT, ResourceLocation.parse(pName));
    }
}
