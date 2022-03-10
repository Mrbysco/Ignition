package com.mrbysco.ignition.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;
import java.util.function.BooleanSupplier;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

public class CustomWallTorchBlock extends WallTorchBlock implements Inferno {
	private final Supplier<Block> fireBlockSupplier;
	private final BooleanSupplier enableFire;
	private final BooleanSupplier randomTicking;
	private final IntSupplier tickDelay;

	public CustomWallTorchBlock(Properties properties, ParticleOptions particleOptions, Supplier<Block> fireBlockSupplier,
								BooleanSupplier enableFire, BooleanSupplier randomTicking, IntSupplier tickDelay) {
		super(properties, particleOptions);
		this.fireBlockSupplier = fireBlockSupplier;
		this.enableFire = enableFire;
		this.randomTicking = randomTicking;
		this.tickDelay = tickDelay;
	}

	@Override
	public boolean enableFire(BlockState state) {
		return enableFire.getAsBoolean();
	}

	@Override
	public boolean randomlyTicksFire(BlockState state) {
		return randomTicking.getAsBoolean();
	}

	@Override
	public BlockState getFireState() {
		return fireBlockSupplier.get().defaultBlockState();
	}

	@Override
	public int getFireTickDelay(Random rand) {
		return tickDelay.getAsInt() + rand.nextInt(10);
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
	public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
		super.onPlace(state, level, pos, oldState, isMoving);
		if (enableFire(state)) {
			scheduleFireTick(level, pos, state);
		}
	}
}
