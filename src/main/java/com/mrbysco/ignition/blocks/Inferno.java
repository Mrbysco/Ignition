package com.mrbysco.ignition.blocks;

import com.mrbysco.ignition.config.IgnitionConfig;
import com.mrbysco.ignition.util.FlammabilityUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public interface Inferno {
	boolean ignition$enableFire(BlockState state);

	int ignition$getFireTickDelay(BlockState state, RandomSource random);

	boolean ignition$randomlyTicksFire(BlockState state);

	BlockState ignition$getFireState(BlockState state);

	default void ignition$fireTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		FlammabilityUtil.onFireTick(state, level, pos, random, ignition$getFireState(state));
	}

	default void ignition$scheduleFireTick(Level level, BlockPos pos, BlockState state) {
		if (!IgnitionConfig.COMMON.randomTicking.get()) {
			level.scheduleTick(pos, state.getBlock(), ignition$getFireTickDelay(state, level.random));
		}
	}
}
