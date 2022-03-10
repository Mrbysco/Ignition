package com.mrbysco.ignition.blocks;

import com.mrbysco.ignition.config.IgnitionConfig;
import com.mrbysco.ignition.util.FlammabilityUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public interface Inferno {
	boolean enableFire(BlockState state);

	int getFireTickDelay(Random random);

	boolean randomlyTicksFire(BlockState state);

	BlockState getFireState();

	default void fireTick(BlockState state, ServerLevel level, BlockPos pos, Random random) {
		FlammabilityUtil.onFireTick(state, level, pos, random, getFireState());
	}

	default void scheduleFireTick(Level level, BlockPos pos, BlockState state) {
		if (!IgnitionConfig.COMMON.randomTicking.get()) {
			level.scheduleTick(pos, state.getBlock(), getFireTickDelay(level.random));
		}
	}
}
