package com.github.constanze3.lycanthropy;

import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.FabricLootSupplierBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.item.Item;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;


public class GenerateLootTables {

    private static FabricLootSupplierBuilder supplier;

    public static void register(){

        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
            if(!id.toString().contains("minecraft:chests/")) return;
            GenerateLootTables.supplier = supplier;
            switch (id.toString()) {
                case "minecraft:chests/simple_dungeon", "minecraft:chests/desert_pyramid", "minecraft:chests/jungle_temple" -> {
                    buildPool(LycanthropyMod.SILVER_SWORD, 0.2F, 1F);
                    buildPool(LycanthropyMod.SILVER_INGOT, 0.3F, 2F);
                    System.out.println("dungeon loot set");
                }
                case "minecraft:chests/village/village_weaponsmith" -> {
                    buildPool(LycanthropyMod.SILVER_SWORD, 0.2F, 1F);
                    buildPool(LycanthropyMod.SILVER_INGOT, 0.7F, 3F);
                }
                case "minecraft:chests/abandoned_mineshaft" -> {
                    buildPool(LycanthropyMod.SILVER_SWORD, 0.3F, 1F);
                    buildPool(LycanthropyMod.SILVER_INGOT, 0.4F, 2F);
                }
                case "minecraft:chests/stronghold_crossing" -> {
                    buildPool(LycanthropyMod.SILVER_SWORD, 0.2F, 1F);
                    buildPool(LycanthropyMod.SILVER_INGOT, 0.1F, 2F);
                }
                case "minecraft:chests/woodland_mansion" -> buildPool(LycanthropyMod.SILVER_SWORD, 0.2F, 1F);
                case "minecraft:chests/underwater_ruin_small" -> buildPool(LycanthropyMod.SILVER_INGOT, 0.4F, 2F);
                case "minecraft:chests/shipwreck_treasure" -> {
                    buildPool(LycanthropyMod.SILVER_SWORD, 0.3F, 1F);
                    buildPool(LycanthropyMod.SILVER_INGOT, 0.2F, 2F);
                }
            }
        });
    }

    static void buildPool(Item item, Float chance, float maxAmount)
    {
        FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                .rolls(ConstantLootNumberProvider.create(1)).withCondition(RandomChanceLootCondition.builder(chance).build())
                .withEntry(ItemEntry.builder(item).build()).withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, maxAmount)).build());
        supplier.withPool(poolBuilder.build());
    }
}
