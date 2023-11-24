package com.mrbysco.ignition.mixin;

import com.mrbysco.ignition.blocks.Inferno;
import com.mrbysco.ignition.config.IgnitionConfig;
import com.mrbysco.ignition.util.FlammabilityUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CandleCakeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(CandleCakeBlock.class)
public class CandleCakeBlockMixin extends Block implements Inferno {
	@Shadow
	@Final
	public static BooleanProperty LIT;

	public CandleCakeBlockMixin(Properties properties) {
		super(properties);
	}

	@Override
	public boolean ignition$enableFire(BlockState state) {
		return IgnitionConfig.COMMON.enableCandles.get();
	}

	@Override
	public boolean ignition$randomlyTicksFire(BlockState state) {
		return IgnitionConfig.COMMON.randomCandleTicking.get();
	}

	@Override
	public BlockState ignition$getFireState(BlockState state) {
		return Blocks.FIRE.defaultBlockState();
	}

	@Override
	public int ignition$getFireTickDelay(BlockState state, RandomSource rand) {
		return IgnitionConfig.COMMON.candleTickDelay.get() + rand.nextInt(10);
	}

	@Override
	public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (ignition$enableFire(state)) {
			ignition$scheduleFireTick(level, pos, state);
			this.ignition$fireTick(state, level, pos, random);
		}
	}

	@Override
	public void ignition$fireTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (state.getValue(LIT)) {
			FlammabilityUtil.onFireTick(state, level, pos, random, ignition$getFireState(state));
		}
	}

	@Override
	public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
		super.onPlace(state, level, pos, oldState, isMoving);
		if (ignition$enableFire(state)) {
			if (!this.isRandomlyTicking && ignition$randomlyTicksFire(state)) {
				((BlockBehaviorAccessor) state.getBlock()).setIsRandomlyTicking(true);
			}
			ignition$scheduleFireTick(level, pos, state);
		}
	}
}
