package org.itiszakk.bettertools.event;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents.After;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.itiszakk.bettertools.actions.Action;
import org.itiszakk.bettertools.actions.ActionFactory;
import org.jetbrains.annotations.Nullable;

public class BetterToolsEvent implements After {

    @Override
    public void afterBlockBreak(World world,
                                PlayerEntity player,
                                BlockPos pos,
                                BlockState state,
                                @Nullable BlockEntity blockEntity) {

        Action action = ActionFactory.getAction(world, player, pos, state);
        action.execute();
    }
}