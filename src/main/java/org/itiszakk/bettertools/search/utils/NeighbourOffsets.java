package org.itiszakk.bettertools.search.utils;

/**
 * Utility class to store arrays of offsets for x, y, and z dimensions.
 */
public final class NeighbourOffsets {

    private final int[] x;
    private final int[] y;
    private final int[] z;

    private NeighbourOffsets(int[] x, int[] y, int[] z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Create NeighbourOffsets from ranges of x, y, and z dimensions.
     *
     * @param x range of x-axis values
     * @param y range of y-axis values
     * @param z range of z-axis values
     * @return NeighbourOffsets object initialized with arrays of offsets
     */
    public static NeighbourOffsets of(Range x, Range y, Range z) {
        return new NeighbourOffsets(x.toArray(), y.toArray(), z.toArray());
    }

    public int[] getX() {
        return x;
    }

    public int[] getY() {
        return y;
    }

    public int[] getZ() {
        return z;
    }
}