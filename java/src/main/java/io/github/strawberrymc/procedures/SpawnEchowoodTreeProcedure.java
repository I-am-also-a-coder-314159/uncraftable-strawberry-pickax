package io.github.strawberrymc.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import io.github.strawberrymc.init.StrawberrymcModBlocks;

public class SpawnEchowoodTreeProcedure {
    public static void execute(LevelAccessor world, double x, double y, double z) {
        RandomSource random = world.getRandom();
        
        if (world.getBlockState(BlockPos.containing(x, y - 1, z)).getBlock() != Blocks.END_STONE) {
            return;
        }

        int height = 10 + random.nextInt(5); 
        BlockState log = StrawberrymcModBlocks.ECHOWOOD_LOG.get().defaultBlockState();
        BlockState leaves = StrawberrymcModBlocks.ECHOWOOD_LEAVES.get().defaultBlockState();
        
        if (leaves.hasProperty(BlockStateProperties.PERSISTENT)) {
            leaves = leaves.setValue(BlockStateProperties.PERSISTENT, true);
        }

        int startX = (int) x;
        int startY = (int) y;
        int startZ = (int) z;

        for (int i = 0; i < height; i++) {
            world.setBlock(BlockPos.containing(startX, startY + i, startZ), log, 3);
            world.setBlock(BlockPos.containing(startX + 1, startY + i, startZ), log, 3);
            world.setBlock(BlockPos.containing(startX, startY + i, startZ + 1), log, 3);
            world.setBlock(BlockPos.containing(startX + 1, startY + i, startZ + 1), log, 3);
        }

        int canopyTop = startY + height;
        int canopyBottom = canopyTop - 4 - random.nextInt(2);

        for (int cy = canopyBottom; cy <= canopyTop + 1; cy++) {
            int radius = (cy >= canopyTop) ? 2 : (cy == canopyBottom) ? 2 : 4; 
            for (int cx = startX - radius; cx <= startX + 1 + radius; cx++) {
                for (int cz = startZ - radius; cz <= startZ + 1 + radius; cz++) {
                    boolean xEdge = (cx == startX - radius || cx == startX + 1 + radius);
                    boolean zEdge = (cz == startZ - radius || cz == startZ + 1 + radius);
                    if (xEdge && zEdge && random.nextFloat() < 0.6f) continue;
                    
                    BlockPos leafPos = BlockPos.containing(cx, cy, cz);
                    if (world.isEmptyBlock(leafPos)) {
                        world.setBlock(leafPos, leaves, 3);
                    }
                }
            }
        }

        int numBranches = 4 + random.nextInt(3);
        for (int b = 0; b < numBranches; b++) {
            int bx = startX + random.nextInt(2); 
            int bz = startZ + random.nextInt(2);
            int by = canopyBottom + random.nextInt(3); 

            int dx = random.nextInt(3) - 1;
            int dz = random.nextInt(3) - 1;
            if (dx == 0 && dz == 0) continue;

            int branchLen = 3 + random.nextInt(3);
            for (int l = 1; l <= branchLen; l++) {
                int stepY = by + (l / 2); 
                BlockPos branchPos = BlockPos.containing(bx + (dx * l), stepY, bz + (dz * l));
                world.setBlock(branchPos, log, 3);
                
                if (l == branchLen) {
                    for (int lx = -1; lx <= 1; lx++) {
                        for (int lz = -1; lz <= 1; lz++) {
                            for (int ly = -1; ly <= 1; ly++) {
                                if (Math.abs(lx) + Math.abs(lz) + Math.abs(ly) > 2) continue;
                                BlockPos tipLeafPos = branchPos.offset(lx, ly, lz);
                                if (world.isEmptyBlock(tipLeafPos)) {
                                    world.setBlock(tipLeafPos, leaves, 3);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}