package com.github.constanze3.lycanthropy;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class BottleOfBloodItem extends Item {
    private static final StatusEffectInstance statusEffectInstance = new StatusEffectInstance(StatusEffects.NAUSEA, 200);
    public BottleOfBloodItem(Settings settings)
    {
        super(settings);
    }

    @Override
    public int getMaxUseTime(ItemStack stack)
    {
        return 32;
    }

    @Override
    public UseAction getUseAction(ItemStack stack)
    {
        return UseAction.DRINK;
    }

    @Override
    public TypedActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn)
    {
        playerIn.setCurrentHand(handIn);
        return TypedActionResult.consume(playerIn.getStackInHand(handIn));
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity livingEntity)
    {
        PlayerEntity playerEntity = livingEntity instanceof PlayerEntity ? (PlayerEntity) livingEntity : null;
        if(playerEntity instanceof ServerPlayerEntity)
        {
            Criteria.CONSUME_ITEM.trigger((ServerPlayerEntity) playerEntity, stack);
        }
        if(!world.isClient)
        {
            livingEntity.damage(DamageSource.CRAMMING, 4F);
            livingEntity.addStatusEffect(new StatusEffectInstance(statusEffectInstance));
        }
        if(playerEntity != null)
        {
            playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
            if(!playerEntity.isCreative())
            {
                stack.decrement(1);
            }
        }
        if(playerEntity == null || !playerEntity.isCreative())
        {
            if(stack.isEmpty())
            {
                return new ItemStack(Items.GLASS_BOTTLE);
            }

            if(playerEntity != null)
            {
                playerEntity.getInventory().insertStack(new ItemStack(Items.GLASS_BOTTLE));
            }
        }

        return stack;
    }

}
