package org.itiszakk.woodcutter.event;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents.After;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.itiszakk.woodcutter.util.ContinuousBlocks;
import org.jetbrains.annotations.Nullable;

public class WoodCutterEvent implements After {

    @Override
    public void afterBlockBreak(World world,
                                PlayerEntity player,
                                BlockPos pos,
                                BlockState state,
                                @Nullable BlockEntity blockEntity) {
        if (!canBreakLogs(player, state)) {
            return;
        }

        ContinuousBlocks treeBlocks = new ContinuousBlocks(world, pos, Direction.UP, this::checkBlockState);
        treeBlocks.forEach(p -> world.breakBlock(p, true));
    }

    private boolean canBreakLogs(PlayerEntity player, BlockState state) {
        return checkBlockState(state) && checkPlayer(player);
    }

    private boolean checkPlayer(PlayerEntity player) {
        return !player.isInCreativeMode() && !player.isSneaking() && player.getMainHandStack().isIn(ItemTags.AXES);
    }

    private boolean checkBlockState(BlockState state) {
        return state.isIn(BlockTags.LOGS);
    }
}