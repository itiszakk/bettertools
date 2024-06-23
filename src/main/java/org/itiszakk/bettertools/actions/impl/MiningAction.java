package org.itiszakk.bettertools.actions.impl;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.itiszakk.bettertools.actions.AbstractAction;

public class MiningAction extends AbstractAction {

    public MiningAction(World world, PlayerEntity player, BlockPos pos, BlockState state) {
        super(world, player, pos, state);
    }

    @Override
    protected boolean skip() {
        return false;
    }

    @Override
    protected void action() {

    }
}
