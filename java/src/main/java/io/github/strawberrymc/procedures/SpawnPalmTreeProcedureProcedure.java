package io.github.strawberrymc.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import io.github.strawberrymc.init.StrawberrymcModBlocks;

public class SpawnPalmTreeProcedureProcedure {
    public static void execute(LevelAccessor world, double x, double y, double z) {
        RandomSource random = world.getRandom();
        
        int height = 8 + random.nextInt(6);
        BlockState log = StrawberrymcModBlocks.PALM_LOG.get().defaultBlockState();
        
        BlockState leaves = StrawberrymcModBlocks.PALM_LEAVES.get().defaultBlockState();
        if (leaves.hasProperty(BlockStateProperties.PERSISTENT)) {
            leaves = leaves.setValue(BlockStateProperties.PERSISTENT, true);
        }

        int tiltX = random.nextInt(3) - 1;
        int tiltZ = random.nextInt(3) - 1;
        int currentX = (int) x;
        int currentZ = (int) z;

        // 1. Trunk (using standard logs, tilted naturally)
        for (int i = 0; i < height; i++) {
            world.setBlock(BlockPos.containing(currentX, y + i, currentZ), log, 3);
            if (i >= 3 && random.nextFloat() < 0.35f) {
                currentX += tiltX;
                currentZ += tiltZ;
            }
        }

        // 2. The Bulky Crown Core
        BlockPos topPos = BlockPos.containing(currentX, y + height - 1, currentZ);
        
        // Build a core that goes 2 blocks above the trunk
        world.setBlock(topPos.above(), leaves, 3);
        world.setBlock(topPos.above(2), leaves, 3);
        
        int[][] coreOffsets = {{1,0}, {-1,0}, {0,1}, {0,-1}, {1,1}, {-1,-1}, {1,-1}, {-1,1}};
        for (int[] off : coreOffsets) {
            world.setBlock(topPos.above().offset(off[0], 0, off[1]), leaves, 3);
            // Randomly thicken the upper half of the core to create a dome shape
            if (random.nextFloat() < 0.6f) {
                world.setBlock(topPos.above(2).offset(off[0], 0, off[1]), leaves, 3);
            }
        }

        // 3. Arching, Clumpy Fronds with Vertical Drops
        int[][] directions = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1}, 
            {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
        };

        for (int[] dir : directions) {
            int dx = dir[0];
            int dz = dir[1];
            
            boolean isDiag = (dx != 0 && dz != 0);
            // Diagonals are slightly shorter to keep an umbrella shape
            int frondLength = isDiag ? 2 + random.nextInt(2) : 3 + random.nextInt(2);
            
            int currentYOffset = 1;
            
            for (int step = 1; step <= frondLength; step++) {
                int px = dx * step;
                int pz = dz * step;
                
                // Arch upward slightly on the first step, then drop
                if (step == 1) {
                    currentYOffset = 1 + random.nextInt(2);
                } else if (step >= frondLength - 1) {
                    currentYOffset--;
                }
                
                BlockPos placePos = topPos.offset(px, currentYOffset, pz);
                world.setBlock(placePos, leaves, 3);
                
                // 50% chance to place a leaf directly below the path to make the branch look thick vertically
                if (random.nextFloat() < 0.5f) {
                    world.setBlock(placePos.below(), leaves, 3);
                }
                
                // 40% chance to widen the cardinal branches laterally so they look clumpy
                if (!isDiag && random.nextFloat() < 0.4f) {
                    world.setBlock(placePos.offset(dz, 0, dx), leaves, 3);
                }
                
                // At the tip of the frond, drop straight down 2 to 3 blocks
                if (step == frondLength) {
                    int hangDepth = 2 + random.nextInt(2);
                    for (int h = 1; h <= hangDepth; h++) {
                        world.setBlock(placePos.below(h), leaves, 3);
                        // Occasionally double the width of the hanging bits
                        if (random.nextFloat() < 0.3f) {
                            world.setBlock(placePos.below(h).offset(dz, 0, dx), leaves, 3);
                        }
                    }
                }
            }
        }
    }
}