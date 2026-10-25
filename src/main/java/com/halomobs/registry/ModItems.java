package com.halomobs.registry;

import com.halomobs.HaloMod;
import com.halomobs.entity.HaloMobKind;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.EnumMap;
import java.util.Map;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, HaloMod.MODID);
    public static final Map<HaloMobKind, RegistryObject<Item>> EGGS = new EnumMap<>(HaloMobKind.class);

    static {
        for (HaloMobKind kind : HaloMobKind.values()) {
            EGGS.put(kind, ITEMS.register(kind.id + "_spawn_egg", () ->
                    new ForgeSpawnEggItem(ModEntities.TYPES.get(kind), kind.eggPrimary, kind.eggSecondary, new Item.Properties())));
        }
    }
}
