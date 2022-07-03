package com.mrbysco.ignition.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;

public class FlammabilityUtil {
	public static boolean hasFlammableNeighbours(LevelReader reader, BlockPos pos) {
		for (Direction direction : Direction.values()) {
			if (isFlammable(reader, pos.relative(direction), direction.getOpposite())) {
				return true;
			}
		}

		return false;
	}

	public static boolean isFlammable(LevelReader level, BlockPos pos, Direction face) {
		return (pos.getY() < level.getMinBuildHeight() || pos.getY() >= level.getMaxBuildHeight() ||
				level.hasChunkAt(pos)) && level.getBlockState(pos).isFlammable(level, pos, face);
	}

	public static void onFireTick(BlockState state, Level level, BlockPos pos, RandomSource random, BlockState fireState) {
		if (level.getGameRules().getBoolean(GameRules.RULE_DOFIRETICK)) {
			int i = random.nextInt(3);
			if (i > 0) {
				BlockPos blockpos = pos;

				for (int j = 0; j < i; ++j) {
					blockpos = blockpos.offset(random.nextInt(3) - 1, random.nextInt(2), random.nextInt(3) - 1);
					if (!level.isLoaded(blockpos)) {
						return;
					}

					BlockState blockstate = level.getBlockState(blockpos);
					if (blockstate.isAir()) {
						if (FlammabilityUtil.hasFlammableNeighbours(level, blockpos)) {
							level.setBlockAndUpdate(blockpos, fireState);
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
						level.setBlockAndUpdate(offsetPos.above(), fireState);
					}
				}
			}
		}
	}
}
