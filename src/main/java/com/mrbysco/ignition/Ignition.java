package com.mrbysco.ignition;

import com.mojang.logging.LogUtils;
import com.mrbysco.ignition.config.IgnitionConfig;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(Ignition.MOD_ID)
public class Ignition {
	public static final String MOD_ID = "ignition";
	public static final Logger LOGGER = LogUtils.getLogger();

	public Ignition() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		ModLoadingContext.get().registerConfig(Type.COMMON, IgnitionConfig.commonSpec);
	}
}
