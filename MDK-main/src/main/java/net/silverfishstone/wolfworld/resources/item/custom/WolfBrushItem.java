package net.silverfishstone.wolfworld.resources.item.custom;

import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.animal.WolfVariant;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.silverfishstone.wolfworld.resources.item.WolfItems;
import net.silverfishstone.wolfworld.util.components.WolfComponents;

import static net.silverfishstone.wolfworld.WolfWorld.removeItemUnlessCreative;


public class WolfBrushItem extends Item {

    WolfBrushType type = WolfBrushType.EMPTY;

    public WolfBrushItem(Properties pProperties) {
        super(pProperties);
    }

    public static void spawnBrushType(String type, Wolf wolf) {
        ItemStack stack = WolfItems.WOlF_BRUSH.toStack();
        Component component = Component.literal(type);
        stack.set(WolfComponents.WOLF_BRUSH_TYPE, component);
        wolf.spawnAtLocation(stack);
    }

    public static void setBrushType(String type, ItemStack stack, Player player) {
        ItemStack returnItem = WolfItems.WOlF_BRUSH.get().getDefaultInstance();
        WolfBrushItem item = (WolfBrushItem) stack.getItem();
        item.type.name = type;
        Component component = Component.literal(item.type.name);
        returnItem.set(WolfComponents.WOLF_BRUSH_TYPE, component);
        removeItemUnlessCreative(player, stack);
        returnItem.setCount(1);
        player.addItem(returnItem);
    }

    public static String getBrushTypeName (ItemStack brush) {
        return getBrushTypeString(brush) + ".brush";
    }

    public static String getBrushTypeString(ItemStack brush) {
        return brushType(brush);
    }

    public static String brushType (ItemStack brush) {
        Component type = brush.getComponents().getOrDefault(WolfComponents.WOLF_BRUSH_TYPE.get(), Component.translatable("item.wolfworld.wolf_brush_empty"));
        return (type).getString();
    }

    public static void setWolfType(ItemStack stack, Wolf wolf, Player player) {
        RegistryAccess registryaccess = wolf.registryAccess();
        Registry<WolfVariant> registry = registryaccess.registryOrThrow(Registries.WOLF_VARIANT);
        if (!wolf.getVariant().is(ResourceLocation.parse(brushType(stack)))) {
            wolf.setVariant(registry.getHolder(ResourceLocation.parse(brushType(stack))).or(registry::getAny).orElseThrow());
            removeItemUnlessCreative(player, stack);
        }
    }

    public static float brushtypeFloat (ItemStack stack) {
        float typeNumber = 1F;
        if (stack.getComponents().get(WolfComponents.WOLF_BRUSH_TYPE.get()).getString().contains("snowy")) {
            typeNumber = 0.1F;
        } else if (stack.getComponents().get(WolfComponents.WOLF_BRUSH_TYPE.get()).getString().contains("woods")) {
            typeNumber = 0.2F;
        } else if (stack.getComponents().get(WolfComponents.WOLF_BRUSH_TYPE.get()).getString().contains("striped")) {
            typeNumber = 0.3F;
        } else if (stack.getComponents().get(WolfComponents.WOLF_BRUSH_TYPE.get()).getString().contains("spotted")) {
            typeNumber = 0.4F;
        } else if (stack.getComponents().get(WolfComponents.WOLF_BRUSH_TYPE.get()).getString().contains("chestnut")) {
            typeNumber = 0.5F;
        } else if (stack.getComponents().get(WolfComponents.WOLF_BRUSH_TYPE.get()).getString().contains("black")) {
            typeNumber = 0.6F;
        } else if (stack.getComponents().get(WolfComponents.WOLF_BRUSH_TYPE.get()).getString().contains("rusty")) {
            typeNumber = 0.7F;
        } else if (stack.getComponents().get(WolfComponents.WOLF_BRUSH_TYPE.get()).getString().contains("pale")) {
            typeNumber = 0.8F;
        } else if (stack.getComponents().get(WolfComponents.WOLF_BRUSH_TYPE.get()).getString().contains("ashen")) {
            typeNumber = 0.9F;
        } else if (!stack.getComponents().get(WolfComponents.WOLF_BRUSH_TYPE.get()).getString().contains("none")) {
            typeNumber = 1F;
        }
        return typeNumber;
    }

    public enum WolfBrushType implements StringRepresentable {
        WOODS("woods"),
        ASHEN("ashen"),
        SPOTTED("spotted"),
        SNOWY("snowy"),
        BLACK("black"),
        RUSTY("rusty"),
        CHESTNUT("chestnut"),
        STRIPED("striped"),
        PALE("pale"),
        EMPTY("none");

        public String name;

        WolfBrushType(String pName) {
            this.name = pName;
        }


        @Override
        public String toString() {
            return this.getSerializedName();
        }

        @Override
        public String getSerializedName() {
            return this.name;
        }
    }
}
