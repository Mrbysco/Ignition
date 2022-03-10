package com.mrbysco.ignition.registry;

import com.mrbysco.ignition.blocks.CustomTorchBlock;
import com.mrbysco.ignition.blocks.CustomWallTorchBlock;
import com.mrbysco.ignition.config.IgnitionConfig;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class IgnitionRegistry {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "minecraft");
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "minecraft");

	public static final RegistryObject<Block> TORCH = BLOCKS.register("torch", () -> new CustomTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION)
			.noCollission().instabreak().lightLevel((state) -> {
				return 14;
			}).sound(SoundType.WOOD), ParticleTypes.FLAME, () -> Blocks.FIRE, IgnitionConfig.COMMON.enableTorch::get,
				IgnitionConfig.COMMON.randomTicking::get, IgnitionConfig.COMMON.torchTickDelay::get));

	public static final RegistryObject<Block> WALL_TORCH = BLOCKS.register("wall_torch", () -> new CustomWallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION)
			.noCollission().instabreak().lightLevel((state) -> {
				return 14;
			}).sound(SoundType.WOOD).dropsLike(TORCH.get()), ParticleTypes.FLAME, () -> Blocks.FIRE, IgnitionConfig.COMMON.enableTorch::get,
				IgnitionConfig.COMMON.randomTicking::get, IgnitionConfig.COMMON.torchTickDelay::get));

	public static final RegistryObject<Block> SOUL_TORCH = BLOCKS.register("soul_torch", () -> new CustomTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION)
			.noCollission().instabreak().lightLevel((state) -> {
				return 10;
			}).sound(SoundType.WOOD), ParticleTypes.SOUL_FIRE_FLAME, () -> Blocks.SOUL_FIRE, IgnitionConfig.COMMON.enableSoulTorch::get,
				IgnitionConfig.COMMON.randomSoulTicking::get, IgnitionConfig.COMMON.soulTorchTickDelay::get));

	public static final RegistryObject<Block> WALL_SOUL_TORCH = BLOCKS.register("wall_soul_torch", () -> new CustomWallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION)
			.noCollission().instabreak().lightLevel((state) -> {
				return 10;
			}).sound(SoundType.WOOD).dropsLike(SOUL_TORCH.get()), ParticleTypes.SOUL_FIRE_FLAME, () -> Blocks.SOUL_FIRE, IgnitionConfig.COMMON.enableSoulTorch::get,
				IgnitionConfig.COMMON.randomSoulTicking::get, IgnitionConfig.COMMON.soulTorchTickDelay::get));

	public static final RegistryObject<Item> TORCH_ITEM = ITEMS.register("torch", () -> new StandingAndWallBlockItem(TORCH.get(), WALL_TORCH.get(),
			(new Item.Properties()).tab(CreativeModeTab.TAB_DECORATIONS)));

	public static final RegistryObject<Item> SOUL_TORCH_ITEM = ITEMS.register("soul_torch", () -> new StandingAndWallBlockItem(SOUL_TORCH.get(), WALL_SOUL_TORCH.get(),
			(new Item.Properties()).tab(CreativeModeTab.TAB_DECORATIONS)));
}
