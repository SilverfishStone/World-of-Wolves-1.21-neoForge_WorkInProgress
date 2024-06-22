package net.silverfishstone.wolfworld.resources.enchantments;

import net.minecraft.advancements.critereon.DamageSourcePredicate;
import net.minecraft.advancements.critereon.TagPredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.*;
import net.minecraft.world.item.enchantment.effects.AddValue;
import net.minecraft.world.item.enchantment.effects.Ignite;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.predicates.DamageSourceCondition;
import net.silverfishstone.wolfworld.util.WolfTags;

public class WolfEnchantments extends Enchantments {
    public static final ResourceKey<Enchantment> ANTIDOTE = key("antidote");
    public static final ResourceKey<Enchantment> BURNING_WOLF = key("burning_wolf");

    public static void bootstrap(BootstrapContext<Enchantment> enchantment) {
        HolderGetter<DamageType> holdergetter = enchantment.lookup(Registries.DAMAGE_TYPE);
        HolderGetter<Enchantment> holdergetter1 = enchantment.lookup(Registries.ENCHANTMENT);
        HolderGetter<Item> holdergetter2 = enchantment.lookup(Registries.ITEM);
        HolderGetter<Block> holdergetter3 = enchantment.lookup(Registries.BLOCK);
        register(enchantment, ANTIDOTE, Enchantment.enchantment(Enchantment.definition(holdergetter2.getOrThrow(WolfTags.Items.WOLF_ARMORS), 10, 4, Enchantment.dynamicCost(1, 11), Enchantment.dynamicCost(12, 11), 1, EquipmentSlotGroup.ARMOR))
                .exclusiveWith(holdergetter1.getOrThrow(WolfTags.Enchantments.WOLF_ARMOR_ATTACK_EXCLUSIVE))
                .withEffect(
                        EnchantmentEffectComponents.DAMAGE_PROTECTION,
                        new AddValue(LevelBasedValue.perLevel(1.0F)),
                        DamageSourceCondition.hasDamageSource(
                                DamageSourcePredicate.Builder.damageType().tag(TagPredicate.isNot(DamageTypeTags.BYPASSES_INVULNERABILITY))
                        )
                ));
        register(enchantment, BURNING_WOLF, Enchantment.enchantment(Enchantment.definition(holdergetter2.getOrThrow(WolfTags.Items.WOLF_ARMORS), 10, 4, Enchantment.dynamicCost(1, 11), Enchantment.dynamicCost(12, 11), 1, EquipmentSlotGroup.ARMOR))
                .exclusiveWith(holdergetter1.getOrThrow(WolfTags.Enchantments.WOLF_ARMOR_ATTACK_EXCLUSIVE))
                .withEffect(
                        EnchantmentEffectComponents.POST_ATTACK,
                        EnchantmentTarget.ATTACKER,
                        EnchantmentTarget.VICTIM,
                        new Ignite(LevelBasedValue.perLevel(4.0F)),
                        DamageSourceCondition.hasDamageSource(DamageSourcePredicate.Builder.damageType().isDirect(true))
                ));
    }

    private static void register(BootstrapContext<Enchantment> enchantment, ResourceKey<Enchantment> enchantmentPresent, Enchantment.Builder enchantmentBuilt) {
        enchantment.register(enchantmentPresent, enchantmentBuilt.build(enchantmentPresent.location()));
    }

    private static ResourceKey<Enchantment> key(String p_345314_) {
        return ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.withDefaultNamespace(p_345314_));
    }
}