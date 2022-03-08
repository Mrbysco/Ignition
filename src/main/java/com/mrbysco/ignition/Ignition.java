package com.mrbysco.ignition;

import com.mrbysco.ignition.config.IgnitionConfig;
import com.mrbysco.ignition.registry.IgnitionRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Ignition.MOD_ID)
public class Ignition {
	public static final String MOD_ID = "ignition";

	public Ignition() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		ModLoadingContext.get().registerConfig(Type.COMMON, IgnitionConfig.commonSpec);

		IgnitionRegistry.BLOCKS.register(eventBus);
		IgnitionRegistry.ITEMS.register(eventBus);
	}
}
