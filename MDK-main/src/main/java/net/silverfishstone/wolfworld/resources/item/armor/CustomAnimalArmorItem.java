package net.silverfishstone.wolfworld.resources.item.armor;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.*;
import net.silverfishstone.wolfworld.WolfWorld;

import javax.annotation.Nullable;
import java.util.function.Function;

public class CustomAnimalArmorItem extends ArmorItem {
    private final ResourceLocation textureLocation;
    @Nullable
    private final ResourceLocation overlayTextureLocation;
    private final CustomAnimalArmorItem.BodyType bodyType;

    public CustomAnimalArmorItem(Holder<ArmorMaterial> pArmorMaterial, CustomAnimalArmorItem.BodyType pBodyType, boolean pHasOverlay, Item.Properties pProperties) {
        super(pArmorMaterial, ArmorItem.Type.BODY, pProperties);
        this.bodyType = pBodyType;
        ResourceLocation resourcelocation = pBodyType.textureLocator.apply(pArmorMaterial.unwrapKey().orElseThrow().location());
        this.textureLocation = resourcelocation.withSuffix(".png");
        if (pHasOverlay) {
            this.overlayTextureLocation = resourcelocation.withSuffix("_overlay.png");
        } else {
            this.overlayTextureLocation = null;
        }
    }

    public ResourceLocation getTexture() {
        return this.textureLocation;
    }

    @Nullable
    public ResourceLocation getOverlayTexture() {
        return this.overlayTextureLocation;
    }

    public BodyType getBodyType() {
        return this.bodyType;
    }

    @Override
    public SoundEvent getBreakingSound() {
        return this.bodyType.breakingSound;
    }

    /**
     * Checks isDamagable and if it cannot be stacked
     */
    @Override
    public boolean isEnchantable(ItemStack pStack) {
        return false;
    }

    public enum BodyType {
        CANINE_OVERGROWN((location) -> ResourceLocation.fromNamespaceAndPath(WolfWorld.MODID,"textures/entity/wolf/jungle_wolf"), SoundEvents.WOLF_ARMOR_BREAK);

        final Function<ResourceLocation, ResourceLocation> textureLocator;
        final SoundEvent breakingSound;

        private BodyType(Function<ResourceLocation, ResourceLocation> pTextureLocator, SoundEvent pBreakingSound) {
            this.textureLocator = pTextureLocator;
            this.breakingSound = pBreakingSound;
        }
    }
}