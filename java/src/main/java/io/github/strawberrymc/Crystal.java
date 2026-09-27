package io.github.strawberrymc;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import io.github.strawberrymc.init.StrawberrymcModBlocks;
import io.github.strawberrymc.block.DendriteCrystalBlock;
import io.github.strawberrymc.block.GarnetCrystalBlock;

import java.util.Random;
import java.util.List;
import java.util.function.Supplier;

public class Crystal extends Feature<NoneFeatureConfiguration> {
    public static final DeferredRegister<Feature<?>> FEATURES = 
        DeferredRegister.create(Registries.FEATURE, "strawberrymc");

    public static final Supplier<Feature<NoneFeatureConfiguration>> CRYSTAL = 
        FEATURES.register("crystal", () -> new Crystal(NoneFeatureConfiguration.CODEC));
    public Crystal(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }
    
    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();

        Random random = new Random();
        List<Block> types = List.of(
            StrawberrymcModBlocks.DENDRITE_CRYSTAL.get(),
            StrawberrymcModBlocks.GARNET_CRYSTAL.get(),
            StrawberrymcModBlocks.CRYSTALLINE_CALCITE.get(),
            Blocks.AMETHYST_BLOCK
        );
        Block type = types.get(random.nextInt(types.size()));
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
                        level.setBlock(targetPos, type.defaultBlockState(), 2);
                        generated = true;
                    }
                }
            }
        }

        Direction[] sideDirections = {
            Direction.NORTH,
            Direction.SOUTH,
            Direction.WEST,
            Direction.EAST
        };

        for (int x = 0; x < crystalShape.length; x++) {
            for (int z = 0; z < crystalShape[x].length; z++) {
                int height = crystalShape[x][z];
                if (height <= 0 || random.nextInt(3) != 0) {
                    continue;
                }

                int y = random.nextInt(height);
                Direction side = sideDirections[random.nextInt(sideDirections.length)];
                BlockPos sidePos = origin.offset(x - 1, y, z - 1).relative(side);
                if (!level.isEmptyBlock(sidePos)) {
                    continue;
                }

                Block sideType = types.get(random.nextInt(types.size()));
                var sideState = sideType.defaultBlockState();
                if (sideType == StrawberrymcModBlocks.DENDRITE_CRYSTAL.get()) {
                    sideState = sideState.setValue(DendriteCrystalBlock.FACING, side.getOpposite());
                } else if (sideType == StrawberrymcModBlocks.GARNET_CRYSTAL.get()) {
                    sideState = sideState.setValue(GarnetCrystalBlock.FACING, side.getOpposite());
                }

                level.setBlock(sidePos, sideState, 2);
                generated = true;
            }
        }

        return generated;
    }

    public static void init(IEventBus eventBus) {
        FEATURES.register(eventBus);
    }
}
