package com.mrbysco.ignition.mixin;

import com.mrbysco.ignition.config.IgnitionConfig;
import com.mrbysco.ignition.util.FlammabilityUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.CampfireBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.IntSupplier;

@Mixin(CampfireBlockEntity.class)
public class CampfireBlockEntityMixin {
	@Inject(method = "cookTick(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/entity/CampfireBlockEntity;)V",
			at = @At(value = "HEAD"))
	private static void ignitionCampfireTick(Level level, BlockPos pos, BlockState state, CampfireBlockEntity blockEntity, CallbackInfo ci) {
		if (IgnitionConfig.COMMON.enableCampfire.get() && state.is(Blocks.CAMPFIRE)) {
			if (level.getGameTime() % getIgnitionCampfireTickDelay(level.random, IgnitionConfig.COMMON.campfireTickDelay::get) == 0) {
				FlammabilityUtil.onFireTick(state, level, pos, level.random, Blocks.FIRE.defaultBlockState());
			}
		} else if (IgnitionConfig.COMMON.enableSoulCampfire.get() && state.is(Blocks.SOUL_CAMPFIRE)) {
			if (level.getGameTime() % getIgnitionCampfireTickDelay(level.random, IgnitionConfig.COMMON.soulCampfireTickDelay::get) == 0) {
				FlammabilityUtil.onFireTick(state, level, pos, level.random, Blocks.SOUL_FIRE.defaultBlockState());
			}
		}
	}

	private static int getIgnitionCampfireTickDelay(RandomSource rand, IntSupplier tickDelay) {
		return tickDelay.getAsInt() + rand.nextInt(10);
	}
}
