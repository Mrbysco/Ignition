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
	public boolean ignitionEnableFire(BlockState state) {
		return IgnitionConfig.COMMON.enableCandles.get();
	}

	@Override
	public boolean ignitionRandomlyTicksFire(BlockState state) {
		return IgnitionConfig.COMMON.randomCandleTicking.get();
	}

	@Override
	public BlockState ignitionGetFireState(BlockState state) {
		return Blocks.FIRE.defaultBlockState();
	}

	@Override
	public int ignitionGetFireTickDelay(BlockState state, RandomSource rand) {
		return IgnitionConfig.COMMON.candleTickDelay.get() + rand.nextInt(10);
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
	public void ignitionFireTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (state.getValue(LIT)) {
			FlammabilityUtil.onFireTick(state, level, pos, random, ignitionGetFireState(state));
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
