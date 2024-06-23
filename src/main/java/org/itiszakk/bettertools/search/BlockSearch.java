package org.itiszakk.bettertools.search;

import net.minecraft.util.math.BlockPos;

import java.util.*;

public class BlockSearch {

    /**
     * Perform a breadth-first search (BFS) starting from a given position.
     *
     * @param start     the starting position for the search
     * @param provider  the provider for neighboring positions
     * @param validator the validator for neighboring positions
     * @return a set of positions that were visited during the search
     */
    public static Set<BlockPos> bfs(BlockPos start,
                                    NeighboursProvider provider,
                                    NeighbourValidator validator) {
        Queue<BlockPos> queue = new LinkedList<>();
        Set<BlockPos> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            BlockPos current = queue.poll();

            List<BlockPos> neighbours = provider.neighbours(current);

            for (BlockPos neighbour : neighbours) {
                if (!visited.contains(neighbour) && validator.validate(neighbour, current)) {
                    visited.add(neighbour);
                    queue.add(neighbour);
                }
            }
        }

        return visited;
    }
}
