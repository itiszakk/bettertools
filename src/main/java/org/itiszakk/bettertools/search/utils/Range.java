package org.itiszakk.bettertools.search.utils;

import java.util.stream.IntStream;

/**
 * Represents a range of integers from 'min' to 'max' (inclusive).
 */
public class Range {

    private final int min;
    private final int max;

    public Range(int min, int max) {
        this.min = min;
        this.max = max;
    }

    /**
     * Converts the range to an array of integers containing all values from 'min' to 'max'.
     *
     * @return an array representation of the range
     */
    public int[] toArray() {
        return IntStream.rangeClosed(min, max).toArray();
    }
}
