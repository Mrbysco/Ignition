package com.mrbysco.ignition.util;

import com.mrbysco.ignition.config.IgnitionConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelReader;

import java.util.Random;

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

	public static int getFireTickDelay(Random rand) {
		return IgnitionConfig.COMMON.torchTickDelay.get() + rand.nextInt(10);
	}
}
