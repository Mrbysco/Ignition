package com.mrbysco.ignition.mixin;

import com.mrbysco.ignition.config.IgnitionConfig;
import com.mrbysco.ignition.util.FlammabilityUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.item.crafting.RecipeManager.CachedCheck;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.CampfireBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.IntSupplier;

@Mixin(CampfireBlockEntity.class)
public class CampfireBlockEntityMixin {
	@Inject(method = "cookTick(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/entity/CampfireBlockEntity;Lnet/minecraft/world/item/crafting/RecipeManager$CachedCheck;)V",
			at = @At(value = "HEAD"))
	private static void ignitionCampfireTick(ServerLevel level, BlockPos pos, BlockState state, CampfireBlockEntity campfire, CachedCheck<SingleRecipeInput, CampfireCookingRecipe> check, CallbackInfo ci) {
		if (IgnitionConfig.COMMON.enableCampfire.get() && state.is(Blocks.CAMPFIRE)) {
			if (level.getGameTime() % ignition$getCampfireTickDelay(level.getRandom(), IgnitionConfig.COMMON.campfireTickDelay::get) == 0) {
				FlammabilityUtil.onFireTick(state, level, pos, level.getRandom(), Blocks.FIRE.defaultBlockState());
			}
		} else if (IgnitionConfig.COMMON.enableSoulCampfire.get() && state.is(Blocks.SOUL_CAMPFIRE)) {
			if (level.getGameTime() % ignition$getCampfireTickDelay(level.getRandom(), IgnitionConfig.COMMON.soulCampfireTickDelay::get) == 0) {
				FlammabilityUtil.onFireTick(state, level, pos, level.getRandom(), Blocks.SOUL_FIRE.defaultBlockState());
			}
		}
	}

	@Unique
	private static int ignition$getCampfireTickDelay(RandomSource rand, IntSupplier tickDelay) {
		return tickDelay.getAsInt() + rand.nextInt(10);
	}
}
