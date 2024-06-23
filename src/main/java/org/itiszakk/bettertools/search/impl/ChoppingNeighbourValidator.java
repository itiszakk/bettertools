package org.itiszakk.bettertools.search.impl;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.itiszakk.bettertools.config.ChoppingActionConfig;
import org.itiszakk.bettertools.search.NeighbourValidator;

import java.util.Map;

public class ChoppingNeighbourValidator implements NeighbourValidator {

    private static final Map<Block, Block> LEAVES_BY_LOG = Map.of(
            Blocks.OAK_LOG, Blocks.OAK_LEAVES,
            Blocks.SPRUCE_LOG, Blocks.SPRUCE_LEAVES,
            Blocks.BIRCH_LOG, Blocks.BIRCH_LEAVES,
            Blocks.JUNGLE_LOG, Blocks.JUNGLE_LEAVES,
            Blocks.ACACIA_LOG, Blocks.ACACIA_LEAVES,
            Blocks.CHERRY_LOG, Blocks.CHERRY_LEAVES,
            Blocks.DARK_OAK_LOG, Blocks.DARK_OAK_LEAVES,
            Blocks.MANGROVE_LOG, Blocks.MANGROVE_LEAVES
    );

    private final World world;
    private final Block target;

    public ChoppingNeighbourValidator(World world, Block target) {
        this.world = world;
        this.target = target;
    }

    @Override
    public boolean validate(BlockPos current, BlockPos parent) {
        BlockState currentState = world.getBlockState(current);
        BlockState parentState = world.getBlockState(parent);

        if (currentState.isIn(BlockTags.LOGS)) {
            return allowLog(currentState, parentState);
        }

        if (ChoppingActionConfig.CUT_LEAVES.getValue() && currentState.isIn(BlockTags.LEAVES)) {
            return allowLeaf(currentState);
        }

        return false;
    }

    private boolean allowLog(BlockState current, BlockState parent) {
        return current.isOf(target) && checkLogParent(parent);
    }

    private boolean checkLogParent(BlockState parent) {
        return switch (ChoppingActionConfig.MODE.getValue()) {
            case COMPLETE -> true;
            case REALISTIC -> !parent.isIn(BlockTags.LEAVES);
        };
    }

    private boolean allowLeaf(BlockState current) {
        return current.isOf(LEAVES_BY_LOG.get(target));
    }
}
