package io.github.strawberrymc.procedures;

import net.neoforged.neoforge.event.level.block.CropGrowEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import javax.annotation.Nullable;

import io.github.strawberrymc.init.StrawberrymcModBlocks;

@EventBusSubscriber
public class PoorFarmlandPropertiesProcedure {
	@SubscribeEvent
	public static void onCropGrowPre(CropGrowEvent.Pre event) {
		execute(event, event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ());
	}

	public static void execute(LevelAccessor world, double x, double y, double z) {
		execute(null, world, x, y, z);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z) {
		if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == StrawberrymcModBlocks.POOR_FARMLAND.get()) {
			if (Math.random() < 0.6) {
				if (event instanceof CropGrowEvent.Pre _event)
					_event.setResult(CropGrowEvent.Pre.Result.DO_NOT_GROW);
			}
		}
	}
}