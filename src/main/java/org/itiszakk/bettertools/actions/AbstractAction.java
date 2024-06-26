package org.itiszakk.bettertools.actions;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public abstract class AbstractAction implements Action {

    protected final World world;
    protected final PlayerEntity player;
    protected final BlockPos pos;
    protected final BlockState state;

    protected AbstractAction(World world, PlayerEntity player, BlockPos pos, BlockState state) {
        this.world = world;
        this.player = player;
        this.pos = pos;
        this.state = state;
    }

    @Override
    public void execute() {
        if (!check()) {
            return;
        }

        action();
    }

    /**
     * Checks whether the action should be performed
     *
     * @return true if the action should be performed, false otherwise
     */
    protected abstract boolean check();

    /**
     * Performs the main action logic.
     */
    protected abstract void action();
}
