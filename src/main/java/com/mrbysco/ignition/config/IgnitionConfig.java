package com.mrbysco.ignition.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import org.apache.commons.lang3.tuple.Pair;

public class IgnitionConfig {
	public static class Common {
		public final BooleanValue enableTorch;
		public final IntValue torchTickDelay;
		public final BooleanValue randomTicking;

		public final BooleanValue enableSoulTorch;
		public final IntValue soulTorchTickDelay;
		public final BooleanValue randomSoulTicking;

		public final BooleanValue enableCampfire;
		public final IntValue campfireTickDelay;

		public final BooleanValue enableSoulCampfire;
		public final IntValue soulCampfireTickDelay;

		public final BooleanValue enableCandles;
		public final IntValue candleTickDelay;
		public final BooleanValue randomCandleTicking;

		Common(ForgeConfigSpec.Builder builder) {
			builder.comment("General settings")
					.push("General");

			enableTorch = builder
					.comment("Enable Torches setting flammable blocks on fire [Default: true]")
					.define("enableTorch", true);

			torchTickDelay = builder
					.comment("The delay between torch ticks [Default: 60]")
					.defineInRange("torchTickDelay", 60, 1, Integer.MAX_VALUE);

			randomTicking = builder
					.comment("Make the torch ticking completely random [Default: false]")
					.define("randomTicking", false);

			enableSoulTorch = builder
					.comment("Enable Torches setting flammable blocks on fire [Default: true]")
					.define("enableSoulTorch", true);

			soulTorchTickDelay = builder
					.comment("The delay between soul torch ticks [Default: 60]")
					.defineInRange("soulTorchTickDelay", 60, 1, Integer.MAX_VALUE);

			randomSoulTicking = builder
					.comment("Make the soul torch ticking completely random [Default: false]")
					.define("randomSoulTicking", false);

			enableCampfire = builder
					.comment("Enable Campfires setting flammable blocks on fire [Default: true]")
					.define("enableCampfire", true);

			campfireTickDelay = builder
					.comment("The delay between campfire ticks [Default: 60]")
					.defineInRange("campfireTickDelay", 60, 1, Integer.MAX_VALUE);

			enableSoulCampfire = builder
					.comment("Enable Soul Campfires setting flammable blocks on fire [Default: true]")
					.define("enableSoulCampfire", true);

			soulCampfireTickDelay = builder
					.comment("The delay between soul campfire ticks [Default: 60]")
					.defineInRange("soulCampfireTickDelay", 60, 1, Integer.MAX_VALUE);

			enableCandles = builder
					.comment("Enable Candles setting flammable blocks on fire [Default: true]")
					.define("enableCandles", true);

			candleTickDelay = builder
					.comment("The delay between candle ticks [Default: 60]")
					.defineInRange("candleTickDelay", 60, 1, Integer.MAX_VALUE);

			randomCandleTicking = builder
					.comment("Make the candle ticking completely random [Default: false]")
					.define("randomCandleTicking", false);

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
