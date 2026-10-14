package io.github.strawberrymc.procedures;

import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import io.github.strawberrymc.init.StrawberrymcModBlocks;

public class PoorFarmlandSlowdownProcedure {
    public static void execute(Event event, LevelAccessor world, double x, double y, double z) {
        if (world == null || event == null) return;
        
        // This trigger passes CropGrowEvent.Pre
        if (event instanceof BlockEvent.CropGrowEvent.Pre preEvent) {
            BlockPos soilPos = BlockPos.containing(x, y - 1, z);
            
            // Check if the soil is Poor Farmland
            if (world.getBlockState(soilPos).getBlock() == StrawberrymcModBlocks.POOR_FARMLAND.get()) {
                
                // 60% chance to deny this specific growth tick
                if (Math.random() < 0.60) {
                    preEvent.setResult(Result.DENY);
                }
            }
        }
    }
}