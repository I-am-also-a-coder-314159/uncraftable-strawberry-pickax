package io.github.strawberrymc.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;

import io.github.strawberrymc.init.StrawberrymcModBlocks;

public class FarmlandDegradeProcedure {
    public static void execute(LevelAccessor world, double x, double y, double z) {
        if (world == null) return;
        
        BlockPos brokenBlockPos = BlockPos.containing(x, y, z);
        BlockState brokenBlockState = world.getBlockState(brokenBlockPos);

        // 1. Check if the broken block is categorized as a crop
        if (brokenBlockState.is(BlockTags.CROPS)) {
            
            // 2. Check the block directly below the crop
            BlockPos farmlandPos = BlockPos.containing(x, y - 1, z);
            BlockState farmlandState = world.getBlockState(farmlandPos);

            // 3. Verify it is your custom farmland block that can hold NBT data
            if (farmlandState.getBlock() == StrawberrymcModBlocks.CUSTOM_FARMLAND.get()) {
                BlockEntity blockEntity = world.getBlockEntity(farmlandPos);
                
                if (blockEntity != null) {
                    double currentHarvests = blockEntity.getPersistentData().getDouble("HarvestCount");
                    
                    if (currentHarvests >= 3) {
                        // Degrade to PoorFarmland
                        world.setBlock(farmlandPos, StrawberrymcModBlocks.POOR_FARMLAND.get().defaultBlockState(), 3);
                    } else {
                        // Increment harvest count
                        blockEntity.getPersistentData().putDouble("HarvestCount", currentHarvests + 1);
                    }
                }
            }
        }
    }
}