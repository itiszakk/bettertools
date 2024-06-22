package org.itiszakk.bettertools.actions;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DefaultAction extends AbstractAction {

    protected DefaultAction(World world, PlayerEntity player, BlockPos pos, BlockState state) {
        super(world, player, pos, state);
    }

    @Override
    protected boolean skip() {
        return true;
    }

    @Override
    protected void action() {

    }
}
