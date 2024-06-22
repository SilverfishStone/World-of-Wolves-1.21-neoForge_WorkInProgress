package net.silverfishstone.wolfworld.resources.item.armor;

import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.silverfishstone.wolfworld.WolfWorld;
import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;


//Thank you, AutovwDev.
//https://github.com/Autovw/AdvancedNetherite/blob/main/Common/src/main/java/com/autovw/advancednetherite/core/util/ModArmorMaterials.java

public class WolfMaterials  {

    public static final Holder<ArmorMaterial> JUNGLE_WOLF = register("jungle_wolf", Util.make(new EnumMap<>(ArmorItem.Type.class), (attribute) -> {
        attribute.put(ArmorItem.Type.BOOTS, 3);
        attribute.put(ArmorItem.Type.LEGGINGS, 6);
        attribute.put(ArmorItem.Type.CHESTPLATE, 8);
        attribute.put(ArmorItem.Type.HELMET, 3);
        attribute.put(ArmorItem.Type.BODY, 11);
    }), 10, 4.0F, 0.0F, Items.ARMADILLO_SCUTE);

    private static Holder<ArmorMaterial> register(String name, EnumMap<ArmorItem.Type, Integer> typeProtections, int enchantability, float toughness, float knockbackResistance, Item ingredientItem) {
        ResourceLocation loc = ResourceLocation.fromNamespaceAndPath(WolfWorld.MODID, name);
        Holder<SoundEvent> equipSound = SoundEvents.ARMOR_EQUIP_WOLF;
        Supplier<Ingredient> ingredient = () -> Ingredient.of(ingredientItem);
        List<ArmorMaterial.Layer> layers = List.of(new ArmorMaterial.Layer(loc));

        EnumMap<ArmorItem.Type, Integer> typeMap = new EnumMap<>(ArmorItem.Type.class);
        for (ArmorItem.Type type : ArmorItem.Type.values()) {
            typeMap.put(type, typeProtections.get(type));
        }
        return Registry.registerForHolder(BuiltInRegistries.ARMOR_MATERIAL, loc, new ArmorMaterial(typeProtections, enchantability, equipSound, ingredient, layers, toughness, knockbackResistance));
    }
}


