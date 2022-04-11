package com.mrbysco.ignition.registry;

import com.mrbysco.ignition.blocks.CustomCandleBlock;
import com.mrbysco.ignition.blocks.CustomCandleCakeBlock;
import com.mrbysco.ignition.blocks.CustomTorchBlock;
import com.mrbysco.ignition.blocks.CustomWallTorchBlock;
import com.mrbysco.ignition.config.IgnitionConfig;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class IgnitionRegistry {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "minecraft");
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "minecraft");

	public static final RegistryObject<Block> TORCH = BLOCKS.register("torch", () -> new CustomTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((state) -> {
		return 14;
	}).sound(SoundType.WOOD), ParticleTypes.FLAME, () -> Blocks.FIRE, IgnitionConfig.COMMON.enableTorch::get, IgnitionConfig.COMMON.randomTicking::get, IgnitionConfig.COMMON.torchTickDelay::get));

	public static final RegistryObject<Block> WALL_TORCH = BLOCKS.register("wall_torch", () -> new CustomWallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((state) -> {
		return 14;
	}).sound(SoundType.WOOD).dropsLike(TORCH.get()), ParticleTypes.FLAME, () -> Blocks.FIRE, IgnitionConfig.COMMON.enableTorch::get, IgnitionConfig.COMMON.randomTicking::get, IgnitionConfig.COMMON.torchTickDelay::get));

	public static final RegistryObject<Block> SOUL_TORCH = BLOCKS.register("soul_torch", () -> new CustomTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((state) -> {
		return 10;
	}).sound(SoundType.WOOD), ParticleTypes.SOUL_FIRE_FLAME, () -> Blocks.SOUL_FIRE, IgnitionConfig.COMMON.enableSoulTorch::get, IgnitionConfig.COMMON.randomSoulTicking::get, IgnitionConfig.COMMON.soulTorchTickDelay::get));

	public static final RegistryObject<Block> SOUL_WALL_TORCH = BLOCKS.register("soul_wall_torch", () -> new CustomWallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((state) -> {
		return 10;
	}).sound(SoundType.WOOD).dropsLike(SOUL_TORCH.get()), ParticleTypes.SOUL_FIRE_FLAME, () -> Blocks.SOUL_FIRE, IgnitionConfig.COMMON.enableSoulTorch::get, IgnitionConfig.COMMON.randomSoulTicking::get, IgnitionConfig.COMMON.soulTorchTickDelay::get));


	public static final RegistryObject<Block> CANDLE = BLOCKS.register("candle", () -> new CustomCandleBlock(BlockBehaviour.Properties.of(Material.DECORATION, MaterialColor.SAND).noOcclusion().strength(0.1F).sound(SoundType.CANDLE).lightLevel(CandleBlock.LIGHT_EMISSION)));
	public static final RegistryObject<Block> WHITE_CANDLE = BLOCKS.register("white_candle", () -> new CustomCandleBlock(BlockBehaviour.Properties.of(Material.DECORATION, MaterialColor.WOOL).noOcclusion().strength(0.1F).sound(SoundType.CANDLE).lightLevel(CandleBlock.LIGHT_EMISSION)));
	public static final RegistryObject<Block> ORANGE_CANDLE = BLOCKS.register("orange_candle", () -> new CustomCandleBlock(BlockBehaviour.Properties.of(Material.DECORATION, MaterialColor.COLOR_ORANGE).noOcclusion().strength(0.1F).sound(SoundType.CANDLE).lightLevel(CandleBlock.LIGHT_EMISSION)));
	public static final RegistryObject<Block> MAGENTA_CANDLE = BLOCKS.register("magenta_candle", () -> new CustomCandleBlock(BlockBehaviour.Properties.of(Material.DECORATION, MaterialColor.COLOR_MAGENTA).noOcclusion().strength(0.1F).sound(SoundType.CANDLE).lightLevel(CandleBlock.LIGHT_EMISSION)));
	public static final RegistryObject<Block> LIGHT_BLUE_CANDLE = BLOCKS.register("light_blue_candle", () -> new CustomCandleBlock(BlockBehaviour.Properties.of(Material.DECORATION, MaterialColor.COLOR_LIGHT_BLUE).noOcclusion().strength(0.1F).sound(SoundType.CANDLE).lightLevel(CandleBlock.LIGHT_EMISSION)));
	public static final RegistryObject<Block> YELLOW_CANDLE = BLOCKS.register("yellow_candle", () -> new CustomCandleBlock(BlockBehaviour.Properties.of(Material.DECORATION, MaterialColor.COLOR_YELLOW).noOcclusion().strength(0.1F).sound(SoundType.CANDLE).lightLevel(CandleBlock.LIGHT_EMISSION)));
	public static final RegistryObject<Block> LIME_CANDLE = BLOCKS.register("lime_candle", () -> new CustomCandleBlock(BlockBehaviour.Properties.of(Material.DECORATION, MaterialColor.COLOR_LIGHT_GREEN).noOcclusion().strength(0.1F).sound(SoundType.CANDLE).lightLevel(CandleBlock.LIGHT_EMISSION)));
	public static final RegistryObject<Block> PINK_CANDLE = BLOCKS.register("pink_candle", () -> new CustomCandleBlock(BlockBehaviour.Properties.of(Material.DECORATION, MaterialColor.COLOR_PINK).noOcclusion().strength(0.1F).sound(SoundType.CANDLE).lightLevel(CandleBlock.LIGHT_EMISSION)));
	public static final RegistryObject<Block> GRAY_CANDLE = BLOCKS.register("gray_candle", () -> new CustomCandleBlock(BlockBehaviour.Properties.of(Material.DECORATION, MaterialColor.COLOR_GRAY).noOcclusion().strength(0.1F).sound(SoundType.CANDLE).lightLevel(CandleBlock.LIGHT_EMISSION)));
	public static final RegistryObject<Block> LIGHT_GRAY_CANDLE = BLOCKS.register("light_gray_candle", () -> new CustomCandleBlock(BlockBehaviour.Properties.of(Material.DECORATION, MaterialColor.COLOR_LIGHT_GRAY).noOcclusion().strength(0.1F).sound(SoundType.CANDLE).lightLevel(CandleBlock.LIGHT_EMISSION)));
	public static final RegistryObject<Block> CYAN_CANDLE = BLOCKS.register("cyan_candle", () -> new CustomCandleBlock(BlockBehaviour.Properties.of(Material.DECORATION, MaterialColor.COLOR_CYAN).noOcclusion().strength(0.1F).sound(SoundType.CANDLE).lightLevel(CandleBlock.LIGHT_EMISSION)));
	public static final RegistryObject<Block> PURPLE_CANDLE = BLOCKS.register("purple_candle", () -> new CustomCandleBlock(BlockBehaviour.Properties.of(Material.DECORATION, MaterialColor.COLOR_PURPLE).noOcclusion().strength(0.1F).sound(SoundType.CANDLE).lightLevel(CandleBlock.LIGHT_EMISSION)));
	public static final RegistryObject<Block> BLUE_CANDLE = BLOCKS.register("blue_candle", () -> new CustomCandleBlock(BlockBehaviour.Properties.of(Material.DECORATION, MaterialColor.COLOR_BLUE).noOcclusion().strength(0.1F).sound(SoundType.CANDLE).lightLevel(CandleBlock.LIGHT_EMISSION)));
	public static final RegistryObject<Block> BROWN_CANDLE = BLOCKS.register("brown_candle", () -> new CustomCandleBlock(BlockBehaviour.Properties.of(Material.DECORATION, MaterialColor.COLOR_BROWN).noOcclusion().strength(0.1F).sound(SoundType.CANDLE).lightLevel(CandleBlock.LIGHT_EMISSION)));
	public static final RegistryObject<Block> GREEN_CANDLE = BLOCKS.register("green_candle", () -> new CustomCandleBlock(BlockBehaviour.Properties.of(Material.DECORATION, MaterialColor.COLOR_GREEN).noOcclusion().strength(0.1F).sound(SoundType.CANDLE).lightLevel(CandleBlock.LIGHT_EMISSION)));
	public static final RegistryObject<Block> RED_CANDLE = BLOCKS.register("red_candle", () -> new CustomCandleBlock(BlockBehaviour.Properties.of(Material.DECORATION, MaterialColor.COLOR_RED).noOcclusion().strength(0.1F).sound(SoundType.CANDLE).lightLevel(CandleBlock.LIGHT_EMISSION)));
	public static final RegistryObject<Block> BLACK_CANDLE = BLOCKS.register("black_candle", () -> new CustomCandleBlock(BlockBehaviour.Properties.of(Material.DECORATION, MaterialColor.COLOR_BLACK).noOcclusion().strength(0.1F).sound(SoundType.CANDLE).lightLevel(CandleBlock.LIGHT_EMISSION)));
	public static final RegistryObject<Block> CANDLE_CAKE = BLOCKS.register("candle_cake", () -> new CustomCandleCakeBlock(CANDLE.get(), BlockBehaviour.Properties.copy(Blocks.CAKE).lightLevel((state) -> {
		return state.getValue(BlockStateProperties.LIT) ? 3 : 0;
	})));
	public static final RegistryObject<Block> WHITE_CANDLE_CAKE = BLOCKS.register("white_candle_cake", () -> new CustomCandleCakeBlock(WHITE_CANDLE.get(), BlockBehaviour.Properties.copy(CANDLE_CAKE.get())));
	public static final RegistryObject<Block> ORANGE_CANDLE_CAKE = BLOCKS.register("orange_candle_cake", () -> new CustomCandleCakeBlock(ORANGE_CANDLE.get(), BlockBehaviour.Properties.copy(CANDLE_CAKE.get())));
	public static final RegistryObject<Block> MAGENTA_CANDLE_CAKE = BLOCKS.register("magenta_candle_cake", () -> new CustomCandleCakeBlock(MAGENTA_CANDLE.get(), BlockBehaviour.Properties.copy(CANDLE_CAKE.get())));
	public static final RegistryObject<Block> LIGHT_BLUE_CANDLE_CAKE = BLOCKS.register("light_blue_candle_cake", () -> new CustomCandleCakeBlock(LIGHT_BLUE_CANDLE.get(), BlockBehaviour.Properties.copy(CANDLE_CAKE.get())));
	public static final RegistryObject<Block> YELLOW_CANDLE_CAKE = BLOCKS.register("yellow_candle_cake", () -> new CustomCandleCakeBlock(YELLOW_CANDLE.get(), BlockBehaviour.Properties.copy(CANDLE_CAKE.get())));
	public static final RegistryObject<Block> LIME_CANDLE_CAKE = BLOCKS.register("lime_candle_cake", () -> new CustomCandleCakeBlock(LIME_CANDLE.get(), BlockBehaviour.Properties.copy(CANDLE_CAKE.get())));
	public static final RegistryObject<Block> PINK_CANDLE_CAKE = BLOCKS.register("pink_candle_cake", () -> new CustomCandleCakeBlock(PINK_CANDLE.get(), BlockBehaviour.Properties.copy(CANDLE_CAKE.get())));
	public static final RegistryObject<Block> GRAY_CANDLE_CAKE = BLOCKS.register("gray_candle_cake", () -> new CustomCandleCakeBlock(GRAY_CANDLE.get(), BlockBehaviour.Properties.copy(CANDLE_CAKE.get())));
	public static final RegistryObject<Block> LIGHT_GRAY_CANDLE_CAKE = BLOCKS.register("light_gray_candle_cake", () -> new CustomCandleCakeBlock(LIGHT_GRAY_CANDLE.get(), BlockBehaviour.Properties.copy(CANDLE_CAKE.get())));
	public static final RegistryObject<Block> CYAN_CANDLE_CAKE = BLOCKS.register("cyan_candle_cake", () -> new CustomCandleCakeBlock(CYAN_CANDLE.get(), BlockBehaviour.Properties.copy(CANDLE_CAKE.get())));
	public static final RegistryObject<Block> PURPLE_CANDLE_CAKE = BLOCKS.register("purple_candle_cake", () -> new CustomCandleCakeBlock(PURPLE_CANDLE.get(), BlockBehaviour.Properties.copy(CANDLE_CAKE.get())));
	public static final RegistryObject<Block> BLUE_CANDLE_CAKE = BLOCKS.register("blue_candle_cake", () -> new CustomCandleCakeBlock(BLUE_CANDLE.get(), BlockBehaviour.Properties.copy(CANDLE_CAKE.get())));
	public static final RegistryObject<Block> BROWN_CANDLE_CAKE = BLOCKS.register("brown_candle_cake", () -> new CustomCandleCakeBlock(BROWN_CANDLE.get(), BlockBehaviour.Properties.copy(CANDLE_CAKE.get())));
	public static final RegistryObject<Block> GREEN_CANDLE_CAKE = BLOCKS.register("green_candle_cake", () -> new CustomCandleCakeBlock(GREEN_CANDLE.get(), BlockBehaviour.Properties.copy(CANDLE_CAKE.get())));
	public static final RegistryObject<Block> RED_CANDLE_CAKE = BLOCKS.register("red_candle_cake", () -> new CustomCandleCakeBlock(RED_CANDLE.get(), BlockBehaviour.Properties.copy(CANDLE_CAKE.get())));
	public static final RegistryObject<Block> BLACK_CANDLE_CAKE = BLOCKS.register("black_candle_cake", () -> new CustomCandleCakeBlock(BLACK_CANDLE.get(), BlockBehaviour.Properties.copy(CANDLE_CAKE.get())));

	public static final RegistryObject<Item> TORCH_ITEM = ITEMS.register("torch", () -> new StandingAndWallBlockItem(TORCH.get(), WALL_TORCH.get(), (new Item.Properties()).tab(CreativeModeTab.TAB_DECORATIONS)));
	public static final RegistryObject<Item> SOUL_TORCH_ITEM = ITEMS.register("soul_torch", () -> new StandingAndWallBlockItem(SOUL_TORCH.get(), SOUL_WALL_TORCH.get(), (new Item.Properties()).tab(CreativeModeTab.TAB_DECORATIONS)));
	public static final RegistryObject<Item> CANDLE_ITEM = ITEMS.register("candle", () -> new BlockItem(CANDLE.get(), (new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS))));
	public static final RegistryObject<Item> WHITE_CANDLE_ITEM = ITEMS.register("white_candle", () -> new BlockItem(WHITE_CANDLE.get(), (new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS))));
	public static final RegistryObject<Item> ORANGE_CANDLE_ITEM = ITEMS.register("orange_candle", () -> new BlockItem(ORANGE_CANDLE.get(), (new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS))));
	public static final RegistryObject<Item> MAGENTA_CANDLE_ITEM = ITEMS.register("magenta_candle", () -> new BlockItem(MAGENTA_CANDLE.get(), (new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS))));
	public static final RegistryObject<Item> LIGHT_BLUE_CANDLE_ITEM = ITEMS.register("light_blue_candle", () -> new BlockItem(LIGHT_BLUE_CANDLE.get(), (new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS))));
	public static final RegistryObject<Item> YELLOW_CANDLE_ITEM = ITEMS.register("yellow_candle", () -> new BlockItem(YELLOW_CANDLE.get(), (new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS))));
	public static final RegistryObject<Item> LIME_CANDLE_ITEM = ITEMS.register("lime_candle", () -> new BlockItem(LIME_CANDLE.get(), (new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS))));
	public static final RegistryObject<Item> PINK_CANDLE_ITEM = ITEMS.register("pink_candle", () -> new BlockItem(PINK_CANDLE.get(), (new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS))));
	public static final RegistryObject<Item> GRAY_CANDLE_ITEM = ITEMS.register("gray_candle", () -> new BlockItem(GRAY_CANDLE.get(), (new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS))));
	public static final RegistryObject<Item> LIGHT_GRAY_CANDLE_ITEM = ITEMS.register("light_gray_candle", () -> new BlockItem(LIGHT_GRAY_CANDLE.get(), (new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS))));
	public static final RegistryObject<Item> CYAN_CANDLE_ITEM = ITEMS.register("cyan_candle", () -> new BlockItem(CYAN_CANDLE.get(), (new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS))));
	public static final RegistryObject<Item> PURPLE_CANDLE_ITEM = ITEMS.register("purple_candle", () -> new BlockItem(PURPLE_CANDLE.get(), (new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS))));
	public static final RegistryObject<Item> BLUE_CANDLE_ITEM = ITEMS.register("blue_candle", () -> new BlockItem(BLUE_CANDLE.get(), (new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS))));
	public static final RegistryObject<Item> BROWN_CANDLE_ITEM = ITEMS.register("brown_candle", () -> new BlockItem(BROWN_CANDLE.get(), (new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS))));
	public static final RegistryObject<Item> GREEN_CANDLE_ITEM = ITEMS.register("green_candle", () -> new BlockItem(GREEN_CANDLE.get(), (new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS))));
	public static final RegistryObject<Item> RED_CANDLE_ITEM = ITEMS.register("red_candle", () -> new BlockItem(RED_CANDLE.get(), (new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS))));
	public static final RegistryObject<Item> BLACK_CANDLE_ITEM = ITEMS.register("black_candle", () -> new BlockItem(BLACK_CANDLE.get(), (new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS))));
}
