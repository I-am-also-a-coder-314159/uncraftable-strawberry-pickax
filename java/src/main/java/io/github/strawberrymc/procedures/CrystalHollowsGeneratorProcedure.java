package io.github.strawberrymc.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;

public class CrystalHollowsGeneratorProcedure {
    public static boolean execute(LevelAccessor world, double x, double z) {
        if (world == null) return false;

        // 1. Define the registry key for your custom cave biome
        // Ensure "crystal_hollows" matches the exact registry name of your biome
        ResourceKey<net.minecraft.world.level.biome.Biome> crystalHollowsKey = 
            ResourceKey.create(Registries.BIOME, ResourceLocation.parse("strawberrymc:crystal_hollows")));

        // 2. Scan underground Y-levels where your cave typically generates
        // Adjust the -60 and 40 based on your biome's generation height parameters
        for (int testY = -60; testY < 40; testY += 8) {
            BlockPos checkPos = BlockPos.containing(x, testY, z);
            
            // 3. If CrystalHollows is found directly below, return true to allow surface generation
            if (world.getBiome(checkPos).is(crystalHollowsKey)) {
                return true;
            }
        }
        
        return false; // Blocks generation if the cave is not found
    }
}