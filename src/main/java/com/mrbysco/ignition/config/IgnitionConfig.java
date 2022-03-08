package com.mrbysco.ignition.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import org.apache.commons.lang3.tuple.Pair;

public class IgnitionConfig {
	public static class Common {
		public final IntValue torchTickDelay;
		public final BooleanValue randomTicking;

		Common(ForgeConfigSpec.Builder builder) {
			builder.comment("General settings")
					.push("General");

			torchTickDelay = builder
					.comment("The delay between torch ticks [Default: 60]")
					.defineInRange("torchTickDelay", 60, 1, Integer.MAX_VALUE);

			randomTicking = builder
					.comment("Make the ticking completely random [Default: false]")
					.define("randomTicking", false);

			builder.pop();
		}
	}

	public static final ForgeConfigSpec commonSpec;
	public static final Common COMMON;

	static {
		final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
		commonSpec = specPair.getRight();
		COMMON = specPair.getLeft();
	}
}
