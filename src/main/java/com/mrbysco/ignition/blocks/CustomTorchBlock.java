package com.mrbysco.ignition.blocks;

import com.mrbysco.ignition.config.IgnitionConfig;
import com.mrbysco.ignition.util.FlammabilityUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public class CustomTorchBlock extends TorchBlock {
	public CustomTorchBlock(Properties properties, ParticleOptions options) {
		super(properties, options);
	}

	@Override
	public boolean isRandomlyTicking(BlockState state) {
		return IgnitionConfig.COMMON.randomTicking.get();
	}

	@Override
	public void tick(BlockState state, ServerLevel level, BlockPos pos, Random random) {
		if (!IgnitionConfig.COMMON.randomTicking.get()) {
			level.scheduleTick(pos, this, FlammabilityUtil.getFireTickDelay(level.random));
		}
		if (level.getGameRules().getBoolean(GameRules.RULE_DOFIRETICK)) {
			int i = random.nextInt(3);
			if (i > 0) {
				BlockPos blockpos = pos;

				for (int j = 0; j < i; ++j) {
					blockpos = blockpos.offset(random.nextInt(3) - 1, 1, random.nextInt(3) - 1);
					if (!level.isLoaded(blockpos)) {
						return;
					}

					BlockState blockstate = level.getBlockState(blockpos);
					if (blockstate.isAir()) {
						if (FlammabilityUtil.hasFlammableNeighbours(level, blockpos)) {
							level.setBlockAndUpdate(blockpos, Blocks.FIRE.defaultBlockState());
							return;
						}
					} else if (blockstate.getMaterial().blocksMotion()) {
						return;
					}
				}
			} else {
				for (int k = 0; k < 3; ++k) {
					BlockPos offsetPos = pos.offset(random.nextInt(3) - 1, 0, random.nextInt(3) - 1);
					if (!level.isLoaded(offsetPos)) {
						return;
					}

					if (level.isEmptyBlock(offsetPos.above()) && FlammabilityUtil.isFlammable(level, offsetPos, Direction.UP)) {
						level.setBlockAndUpdate(offsetPos.above(), Blocks.FIRE.defaultBlockState());
					}
				}
			}

		}
	}

	@Override
	public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
		super.onPlace(state, level, pos, oldState, isMoving);
		if (!IgnitionConfig.COMMON.randomTicking.get()) {
			level.scheduleTick(pos, this, FlammabilityUtil.getFireTickDelay(level.random));
		}
	}
}
