package org.itiszakk.bettertools.actions;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.itiszakk.bettertools.actions.chopping.ChoppingAction;
import org.itiszakk.bettertools.actions.mining.MiningAction;

public class ActionFactory {

    private ActionFactory() {
    }

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
