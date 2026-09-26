package io.github.strawberrymc.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.Identifier;
import net.minecraft.core.BlockPos;

import io.github.strawberrymc.init.StrawberrymcModBlocks;

public class GlowrootGenerationProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).is(BlockTags.create(Identifier.parse("minecraft:base_stone_overworld")))) {
			world.setBlock(BlockPos.containing(x, y - 1, z), StrawberrymcModBlocks.GLOWROOT_TUBER_STONE.get().defaultBlockState(), 3);
			world.setBlock(BlockPos.containing(x, y, z), StrawberrymcModBlocks.GLOWROOT.get().defaultBlockState(), 3);
		}
	}
}