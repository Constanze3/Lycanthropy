package com.github.constanze3.lycanthropy;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.LootTableEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class LycanthropyMod implements ModInitializer {

	public static final String MODID = "lycanthropy";
	public static final ItemGroup WEREWOLF_ITEMS = FabricItemGroupBuilder.build(new Identifier(MODID, "werewolf"), () -> new ItemStack(LycanthropyMod.SILVER_SWORD));
	public static final Item SILVER_INGOT = new Item(new Item.Settings().group(LycanthropyMod.WEREWOLF_ITEMS).maxCount(20));
	public static final SwordItem SILVER_SWORD = new SwordItem(new ToolMaterial() {
		@Override
		public int getDurability() {
			return 156;
		}

		@Override
		public float getMiningSpeedMultiplier() {
			return 6F;
		}

		@Override
		public float getAttackDamage() {
			return 1F;
		}

		@Override
		public int getMiningLevel() {
			return 0;
		}

		@Override
		public int getEnchantability() {
			return 15;
		}

		@Override
		public Ingredient getRepairIngredient() {
			return Ingredient.ofItems(Items.IRON_INGOT);
		}
	}, 3,-2.4F, new FabricItemSettings().group(LycanthropyMod.WEREWOLF_ITEMS).maxCount(1));
	public static final ModStatusEffect BLOODSENSE = new ModStatusEffect();

	@Override
	public void onInitialize() {
		//silver items:
		Registry.register(Registry.ITEM, new Identifier(MODID, "silver_ingot"), SILVER_INGOT);
		Registry.register(Registry.ITEM, new Identifier(MODID, "silver_sword"), SILVER_SWORD);
		//blood sense effect: (the "actual" effect is in an origin power (minecraft origins mod))
		Registry.register(Registry.STATUS_EFFECT, new Identifier(MODID, "blood_sense"), BLOODSENSE);
		Registry.register(Registry.ITEM, new Identifier(MODID, "bottle_of_werewolf_blood"), new ModPotionItem(new StatusEffectInstance(StatusEffects.NAUSEA, 200), false, new Item.Settings().group(LycanthropyMod.WEREWOLF_ITEMS).maxCount(1)));
		Registry.register(Registry.ITEM, new Identifier(MODID, "potion_of_blood_sense"), new ModPotionItem(new StatusEffectInstance(LycanthropyMod.BLOODSENSE, 1200), true, new Item.Settings().group(LycanthropyMod.WEREWOLF_ITEMS).maxCount(1)));
		GenerateLootTables.register();
	}
}
