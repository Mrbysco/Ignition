package com.mrbysco.ignition.blocks;

import com.mrbysco.ignition.config.IgnitionConfig;
import com.mrbysco.ignition.util.FlammabilityUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public interface Inferno {
	boolean ignitionEnableFire(BlockState state);

	int ignitionGetFireTickDelay(BlockState state, RandomSource random);

	boolean ignitionRandomlyTicksFire(BlockState state);

	BlockState ignitionGetFireState(BlockState state);

	default void ignitionFireTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		FlammabilityUtil.onFireTick(state, level, pos, random, ignitionGetFireState(state));
	}

	default void ignitionScheduleFireTick(Level level, BlockPos pos, BlockState state) {
		if (!IgnitionConfig.COMMON.randomTicking.get()) {
			level.scheduleTick(pos, state.getBlock(), ignitionGetFireTickDelay(state, level.random));
		}
	}
}
