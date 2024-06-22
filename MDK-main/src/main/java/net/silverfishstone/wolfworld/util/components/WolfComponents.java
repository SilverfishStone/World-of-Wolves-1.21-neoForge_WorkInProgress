package net.silverfishstone.wolfworld.util.components;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.silverfishstone.wolfworld.WolfWorld;

public class WolfComponents {

    public static final DeferredRegister<DataComponentType<?>> REGISTRAR = DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, WolfWorld.MODID);
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Component>> WOLF_BRUSH_TYPE = REGISTRAR.register("wolf_brush_type",
            () -> DataComponentType.<Component>builder()
                    // The codec to read/write the data to disk
                    .persistent(ComponentSerialization.FLAT_CODEC)
                    // The codec to read/write the data across the network
                    .networkSynchronized(ComponentSerialization.STREAM_CODEC)
                    .build()
    );

}
