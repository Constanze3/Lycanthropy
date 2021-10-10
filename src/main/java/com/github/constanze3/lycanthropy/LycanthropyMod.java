package com.github.constanze3.lycanthropy;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class LycanthropyMod implements ModInitializer {

	public static final String MODID = "lycanthropy";
	public static final ItemGroup WEREWOLF_ITEMS = FabricItemGroupBuilder.build(new Identifier(LycanthropyMod.MODID, "werewolf"), () -> new ItemStack(LycanthropyMod.SILVER_SWORD));
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

	@Override
	public void onInitialize() {
		//silver items:
		Registry.register(Registry.ITEM, new Identifier(LycanthropyMod.MODID, "silver_ingot"), new Item(new Item.Settings().group(LycanthropyMod.WEREWOLF_ITEMS).maxCount(20)));
		Registry.register(Registry.ITEM, new Identifier(LycanthropyMod.MODID, "silver_sword"), SILVER_SWORD);
		//wolf vision:
		Registry.register(Registry.ITEM, new Identifier(LycanthropyMod.MODID, "bottle_of_werewolf_blood"), new BottleOfBloodItem(new Item.Settings().group(LycanthropyMod.WEREWOLF_ITEMS).maxCount(1)));
	}
}
