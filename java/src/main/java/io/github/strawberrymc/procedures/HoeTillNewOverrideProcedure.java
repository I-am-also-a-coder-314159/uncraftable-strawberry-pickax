package io.github.strawberrymc.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.eventbus.api.Event;

import io.github.strawberrymc.init.StrawberrymcModBlocks;

public class CustomHoeTillProcedure {
    public static void execute(Event event, LevelAccessor world, double x, double y, double z, LivingEntity entity) {
        if (world == null || entity == null) return;
        
        BlockPos targetPos = BlockPos.containing(x, y, z);
        net.minecraft.world.level.block.Block targetBlock = world.getBlockState(targetPos).getBlock();
        ItemStack mainHandItem = entity.getMainHandItem();
        
        // 1. Check if the item used is any type of Hoe
        if (mainHandItem.getItem() instanceof HoeItem) {
            if (targetBlock == Blocks.DIRT || targetBlock == Blocks.GRASS_BLOCK || targetBlock == Blocks.DIRT_PATH) {
                
                // 3. Ensure the block directly above is empty (cannot till dirt with a block on top)
                if (world.isEmptyBlock(targetPos.above())) {
                    
                    // 4. Cancel the vanilla tilling event so vanilla farmland isn't placed
                    if (event != null && event.isCancelable()) {
                        event.setCanceled(true);
                    }
                    
                    // 5. Place your Custom Farmland Clone
                    world.setBlock(targetPos, StrawberrymcModBlocks.CUSTOM_FARMLAND.get().defaultBlockState(), 3);
                    
                    // 6. Play the standard tilling sound
                    if (!world.isClientSide()) {
                        world.playSound(null, targetPos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                    }
                    
                    // 7. Damage the hoe by 1 point (if not in Creative mode)
                    if (entity instanceof Player player && !player.getAbilities().instabuild) {
                        mainHandItem.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(player.getUsedItemHand()));
                        // Swing the player's arm for visual feedback
                        player.swing(player.getUsedItemHand(), true);
                    }
                }
            }
        }
    }
}