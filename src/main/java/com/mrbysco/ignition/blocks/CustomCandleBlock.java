package com.mrbysco.ignition.blocks;

import com.mrbysco.ignition.config.IgnitionConfig;
import com.mrbysco.ignition.util.FlammabilityUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public class CustomCandleBlock extends CandleBlock implements Inferno {

	public CustomCandleBlock(Properties properties) {
		super(properties);
	}

	@Override
	public boolean enableFire(BlockState state) {
		return IgnitionConfig.COMMON.enableCandles.get();
	}

	@Override
	public boolean randomlyTicksFire(BlockState state) {
		return IgnitionConfig.COMMON.randomCandleTicking.get();
	}

	@Override
	public BlockState getFireState() {
		return Blocks.FIRE.defaultBlockState();
	}

	@Override
	public int getFireTickDelay(Random rand) {
		return IgnitionConfig.COMMON.candleTickDelay.get() + rand.nextInt(10);
	}

	@Override
	public boolean isRandomlyTicking(BlockState state) {
		return (enableFire(state) && randomlyTicksFire(state)) || super.isRandomlyTicking(state);
	}

	@Override
	public void tick(BlockState state, ServerLevel level, BlockPos pos, Random random) {
		if (enableFire(state)) {
			scheduleFireTick(level, pos, state);
			fireTick(state, level, pos, random);
		}
	}

	@Override
	public void fireTick(BlockState state, ServerLevel level, BlockPos pos, Random random) {
		if (state.getValue(LIT)) {
			FlammabilityUtil.onFireTick(state, level, pos, random, getFireState());
		}
	}

	@Override
	public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
		super.onPlace(state, level, pos, oldState, isMoving);
		if (enableFire(state)) {
			scheduleFireTick(level, pos, state);
		}
	}
}
