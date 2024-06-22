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
        if (skip()) {
            return;
        }

        action();
    }

    /**
     * Skip enhanced action
     *
     * @return true if skipped, else otherwise
     */
    protected abstract boolean skip();

    /**
     * Execute enhanced action
     */
    protected abstract void action();
}
