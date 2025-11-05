package com.mrbysco.ignition;

import com.mojang.logging.LogUtils;
import com.mrbysco.ignition.config.IgnitionConfig;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import org.slf4j.Logger;

@Mod(Ignition.MOD_ID)
public class Ignition {
	public static final String MOD_ID = "ignition";
	public static final Logger LOGGER = LogUtils.getLogger();

	public Ignition(IEventBus eventBus, ModContainer container, Dist dist) {
		container.registerConfig(ModConfig.Type.COMMON, IgnitionConfig.commonSpec);
		eventBus.register(IgnitionConfig.class);

		if (dist.isClient()) {
			container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
		}
	}
}
