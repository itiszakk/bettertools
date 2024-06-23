package org.itiszakk.bettertools.actions.impl;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.itiszakk.bettertools.actions.AbstractAction;

/**
 * Default action implementation that does nothing.
 */
public class DefaultAction extends AbstractAction {

    public DefaultAction(World world, PlayerEntity player, BlockPos pos, BlockState state) {
        super(world, player, pos, state);
    }

    /**
     * Always returns true to skip this action.
     *
     * @return true to skip this action
     */
    @Override
    protected boolean skip() {
        return true;
    }

    /**
     * Placeholder for action logic.
     * This method does nothing since the action is skipped.
     */
    @Override
    protected void action() {

    }
}
