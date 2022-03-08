package com.mrbysco.ignition.registry;

import com.mrbysco.ignition.blocks.CustomTorchBlock;
import com.mrbysco.ignition.blocks.CustomWallTorchBlock;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class IgnitionRegistry {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "minecraft");
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "minecraft");

	public static final RegistryObject<Block> CUSTOM_TORCH = BLOCKS.register("torch", () -> new CustomTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION)
			.noCollission().instabreak().lightLevel((state) -> {
				return 14;
			}).sound(SoundType.WOOD), ParticleTypes.FLAME));

	public static final RegistryObject<Block> CUSTOM_WALL_TORCH = BLOCKS.register("wall_torch", () -> new CustomWallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION)
			.noCollission().instabreak().lightLevel((state) -> {
				return 14;
			}).sound(SoundType.WOOD).dropsLike(CUSTOM_TORCH.get()), ParticleTypes.FLAME));

	public static final RegistryObject<Item> CUSTOM_TORCH_ITEM = ITEMS.register("torch", () -> new StandingAndWallBlockItem(CUSTOM_TORCH.get(), CUSTOM_WALL_TORCH.get(),
			(new Item.Properties()).tab(CreativeModeTab.TAB_DECORATIONS)));
}
