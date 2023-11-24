package com.mrbysco.ignition.mixin;

import net.minecraft.world.level.block.state.BlockBehaviour;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(BlockBehaviour.class)
public interface BlockBehaviorAccessor {
	@Accessor("isRandomlyTicking")
	void setIsRandomlyTicking(boolean value);
}
