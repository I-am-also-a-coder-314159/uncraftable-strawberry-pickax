package io.github.strawberrymc;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Random;
import java.util.List;
import java.util.function.Supplier;

public class Crystal extends Feature<NoneFeatureConfiguration> {
    public static final DeferredRegister<Feature<?>> FEATURES = 
        DeferredRegister.create(Registries.FEATURE, "strawberrymc");

    public static final Supplier<Feature<NoneFeatureConfiguration>> ABSOLUTE_FEATURE = 
        FEATURES.register("absolute_feature", () -> new Crystal(NoneFeatureConfiguration.CODEC));
    public Crystal(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }
    
	List<Block> type = List.of();
    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();

        Random random = new Random();
        int centerHeight = random.nextInt(4, 7);

        int[][] crystalShape = new int[3][3];

        for (int x = 0; x < crystalShape.length; x++) {
            for (int z = 0; z < crystalShape[x].length; z++) {
                boolean isCenter = x == 1 && z == 1;
                boolean isEdge = x == 1 || z == 1;

                if (isCenter) {
                    crystalShape[x][z] = centerHeight;
                } else if (isEdge) {
                    crystalShape[x][z] = random.nextInt(3, centerHeight);
                } else {
                    crystalShape[x][z] = random.nextInt(0, 2);
                }
            }
        }

        boolean generated = false;

        for (int x = 0; x < crystalShape.length; x++) {
            for (int z = 0; z < crystalShape[x].length; z++) {
                int height = crystalShape[x][z];
                if (height <= 0) {
                    continue;
                }

                for (int y = 0; y < height; y++) {
                    BlockPos targetPos = origin.offset(x - 1, y, z - 1);
                    if (level.isEmptyBlock(targetPos)) {
                        level.setBlock(targetPos, Blocks.DIAMOND_BLOCK.defaultBlockState(), 2);
                        generated = true;
                    }
                }
            }
        }

        return generated;
    }

    public static void init(IEventBus eventBus) {
        FEATURES.register(eventBus);
    }
}
