package com.mrbysco.ignition.mixin;

import com.mrbysco.ignition.blocks.Inferno;
import com.mrbysco.ignition.config.IgnitionConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(TorchBlock.class)
public class TorchBlockMixin extends Block implements Inferno {

	public TorchBlockMixin(Properties properties) {
		super(properties);
	}

	@Override
	public boolean ignitionEnableFire(BlockState state) {
		if (state.is(Blocks.SOUL_TORCH))
			return IgnitionConfig.COMMON.enableSoulTorch.get();
		else
			return IgnitionConfig.COMMON.enableTorch.get();
	}

	@Override
	public boolean ignitionRandomlyTicksFire(BlockState state) {
		if (state.is(Blocks.SOUL_TORCH))
			return IgnitionConfig.COMMON.randomSoulTicking.get();
		else
			return IgnitionConfig.COMMON.randomTicking.get();
	}

	@Override
	public BlockState ignitionGetFireState(BlockState state) {
		if (state.is(Blocks.SOUL_TORCH))
			return Blocks.SOUL_FIRE.defaultBlockState();
		else
			return Blocks.FIRE.defaultBlockState();
	}

	@Override
	public int ignitionGetFireTickDelay(BlockState state, RandomSource rand) {
		int tickDelay;
		if (state.is(Blocks.SOUL_TORCH))
			tickDelay = IgnitionConfig.COMMON.soulCampfireTickDelay.get();
		else
			tickDelay = IgnitionConfig.COMMON.torchTickDelay.get();
		return tickDelay + rand.nextInt(10);
	}

	@Override
	public boolean isRandomlyTicking(BlockState state) {
		return (ignitionEnableFire(state) && ignitionRandomlyTicksFire(state)) || super.isRandomlyTicking(state);
	}

	@Override
	public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (ignitionEnableFire(state)) {
			ignitionScheduleFireTick(level, pos, state);
			ignitionFireTick(state, level, pos, random);
		}
	}

	@Override
	public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
		super.onPlace(state, level, pos, oldState, isMoving);
		if (ignitionEnableFire(state)) {
			ignitionScheduleFireTick(level, pos, state);
		}
	}
}
