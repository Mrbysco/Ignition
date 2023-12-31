package com.mrbysco.ignition;

import com.mojang.logging.LogUtils;
import com.mrbysco.ignition.config.IgnitionConfig;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import org.slf4j.Logger;

@Mod(Ignition.MOD_ID)
public class Ignition {
	public static final String MOD_ID = "ignition";
	public static final Logger LOGGER = LogUtils.getLogger();

	public Ignition(IEventBus eventBus) {
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, IgnitionConfig.commonSpec);
		eventBus.register(IgnitionConfig.class);
	}
}
