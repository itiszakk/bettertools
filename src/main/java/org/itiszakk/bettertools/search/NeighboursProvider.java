package org.itiszakk.bettertools.search;

import net.minecraft.util.math.BlockPos;

import java.util.List;

public interface NeighboursProvider {

    /**
     * Method to retrieve the neighboring positions for a given position.
     *
     * @param pos the position for which to retrieve the neighbors
     * @return a list of neighboring positions
     */
    List<BlockPos> neighbours(BlockPos pos);
}
