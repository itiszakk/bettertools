package org.itiszakk.bettertools.search;

import net.minecraft.util.math.BlockPos;

public interface NeighbourValidator {

    /**
     * Method to validate a neighbor based on the current position and its parent.
     *
     * @param current the current position to validate
     * @param parent  the parent position from which the current position was reached
     * @return true if the neighbor is valid, false otherwise
     */
    boolean validate(BlockPos current, BlockPos parent);
}
