package com.github.constanze3.lycanthropy;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class ModStatusEffect extends StatusEffect {
    public ModStatusEffect()
    {
        super(StatusEffectCategory.BENEFICIAL, 0x111111);
    }
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
