package org.itiszakk.bettertools.actions;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.itiszakk.bettertools.actions.impl.ChoppingAction;
import org.itiszakk.bettertools.actions.impl.DefaultAction;
import org.itiszakk.bettertools.actions.impl.MiningAction;


public class ActionFactory {

    private ActionFactory() {
    }

    /**
     * Builds an appropriate action based on the block state and player information.
     *
     * @param world  the world where the action is performed
     * @param player the player performing the action
     * @param pos    the position in the world where the action is targeted
     * @param state  the block state at the targeted position
     * @return an instance of Action
     */
    public static Action getAction(World world, PlayerEntity player, BlockPos pos, BlockState state) {
        if (isChopping(player, state)) {
            return new ChoppingAction(world, player, pos, state);
        }

        if (isMining(player, state)) {
            return new MiningAction(world, player, pos, state);
        }

        return new DefaultAction(world, player, pos, state);
    }

    private static boolean isChopping(PlayerEntity player, BlockState state) {
        return state.isIn(BlockTags.LOGS) && player.getMainHandStack().isIn(ItemTags.AXES);
    }

    private static boolean isMining(PlayerEntity player, BlockState state) {
        return state.isIn(BlockTags.PICKAXE_MINEABLE) && player.getMainHandStack().isIn(ItemTags.PICKAXES);
    }
}
