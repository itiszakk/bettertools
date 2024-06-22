package org.itiszakk.woodcutter.util;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import org.itiszakk.woodcutter.config.CutMode;
import org.itiszakk.woodcutter.config.WoodCutterConfiguration;

import java.util.*;
import java.util.stream.IntStream;

public class Tree {

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
    private final BlockPos start;
    private final Block target;
    private final List<BlockPos> logs;
    private final List<BlockPos> leaves;

    public Tree(World world, BlockPos start, Block target) {
        this.world = world;
        this.start = start;
        this.target = target;
        this.logs = new ArrayList<>();
        this.leaves = new ArrayList<>();

        identify();
    }

    public int destroy() {
        int amount = 0;

        for (BlockPos log : logs) {
            amount += world.breakBlock(log, true) ? 1 : 0;
        }

        for (BlockPos leaf : leaves) {
            world.breakBlock(leaf, true);
        }

        return amount;
    }

    private void identify() {
        Queue<BlockPos> queue = new LinkedList<>();
        Set<BlockPos> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            BlockPos current = queue.poll();

            List<BlockPos> neighbours = getNeighbours(current);

            for (BlockPos neighbour : neighbours) {
                if (!visited.contains(neighbour) && allow(neighbour, current)) {
                    visited.add(neighbour);
                    queue.add(neighbour);
                }
            }
        }

        for (BlockPos pos : visited) {
            BlockState state = world.getBlockState(pos);

            if (state.isIn(BlockTags.LOGS)) {
                logs.add(pos);
            }

            if (state.isIn(BlockTags.LEAVES)) {
                leaves.add(pos);
            }
        }
    }

    private List<BlockPos> getNeighbours(BlockPos current) {
        return switch (WoodCutterConfiguration.CUT_MODE.getValue()) {
            case COMPLETE -> getNeighbours(current, NeighbourOffsets.COMPLETE);
            case REALISTIC -> getNeighbours(current, NeighbourOffsets.REALISTIC);
        };
    }

    private List<BlockPos> getNeighbours(BlockPos current, NeighbourOffsets offsets) {
        List<BlockPos> neighbours = new ArrayList<>();

        for (int dx : offsets.dxRange) {
            for (int dy : offsets.dyRange) {
                for (int dz : offsets.dzRange) {

                    if (dx == 0 && dy == 0 && dz == 0) {
                        continue;
                    }

                    BlockPos next = current.add(new Vec3i(dx, dy, dz));
                    neighbours.add(next);
                }
            }
        }

        return neighbours;
    }

    private boolean allow(BlockPos current, BlockPos parent) {
        BlockState currentState = world.getBlockState(current);
        BlockState parentState = world.getBlockState(parent);

        if (currentState.isIn(BlockTags.LOGS)) {
            return allowLog(currentState, parentState);
        }

        if (WoodCutterConfiguration.CUT_LEAVES.getValue() && currentState.isIn(BlockTags.LEAVES)) {
            return allowLeaf(currentState);
        }

        return false;
    }

    private boolean allowLog(BlockState current, BlockState parent) {
        return current.isOf(target) && checkLogParent(parent);
    }

    private boolean checkLogParent(BlockState parent) {
        return switch (WoodCutterConfiguration.CUT_MODE.getValue()) {
            case COMPLETE -> true;
            case REALISTIC -> !parent.isIn(BlockTags.LEAVES);
        };
    }

    private boolean allowLeaf(BlockState current) {
        return current.isOf(LEAVES_BY_LOG.get(target));
    }

    private record NeighbourOffsets(int[] dxRange, int[] dyRange, int[] dzRange) {

        public static final NeighbourOffsets COMPLETE = new NeighbourOffsets(
                IntStream.rangeClosed(-1, 1).toArray(),
                IntStream.rangeClosed(-1, 1).toArray(),
                IntStream.rangeClosed(-1, 1).toArray()
        );

        public static final NeighbourOffsets REALISTIC = new NeighbourOffsets(
                IntStream.rangeClosed(-1, 1).toArray(),
                IntStream.rangeClosed(0, 1).toArray(),
                IntStream.rangeClosed(-1, 1).toArray()
        );
    }
}