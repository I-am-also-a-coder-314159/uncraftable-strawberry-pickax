package io.github.strawberrymc.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.GameType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;

import io.github.strawberrymc.init.StrawberrymcModItems;
import io.github.strawberrymc.init.StrawberrymcModBlocks;

public class SulfurFertilizeFarmlandProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.FARMLAND) {
			if (world instanceof ServerLevel _level)
				_level.sendParticles(ParticleTypes.COMPOSTER, x, y, z, 5, 1, 1, 1, 1);
			world.setBlock(BlockPos.containing(x, y, z), StrawberrymcModBlocks.FERTILE_FARMLAND.get().defaultBlockState(), 3);
			if (!(entity instanceof Player _plr4 && _plr4.gameMode() == GameType.SURVIVAL)) {
				if (entity instanceof Player _player) {
					ItemStack _stktoremove = new ItemStack(StrawberrymcModItems.SULFUR_SLAG.get());
					_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
				}
			}
		}
	}
}