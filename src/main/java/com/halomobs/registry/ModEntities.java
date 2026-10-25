package com.halomobs.registry;

import com.halomobs.HaloMod;
import com.halomobs.entity.HaloMobEntity;
import com.halomobs.entity.HaloMobKind;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.EnumMap;
import java.util.Map;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, HaloMod.MODID);
    public static final Map<HaloMobKind, RegistryObject<EntityType<HaloMobEntity>>> TYPES = new EnumMap<>(HaloMobKind.class);

    static {
        for (HaloMobKind kind : HaloMobKind.values()) {
            TYPES.put(kind, ENTITIES.register(kind.id, () ->
                    EntityType.Builder.of(HaloMobEntity::new, MobCategory.MONSTER)
                            .sized(kind.width, kind.height)
                            .clientTrackingRange(10)
                            .build(new ResourceLocation(HaloMod.MODID, kind.id).toString())));
        }
    }
}
