package org.itiszakk.bettertools.options.impl;

import org.itiszakk.bettertools.options.AbstractOption;

public class IntegerOption extends AbstractOption<Integer> {

    private final int min;
    private final int max;

    protected IntegerOption(Builder builder) {
        super(builder);
        this.min = builder.min;
        this.max = builder.max;
    }

    public static Builder builder() {
        return new Builder();
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public boolean isSlider() {
        return min < max;
    }

    public static class Builder extends AbstractOption.Builder<Integer, Builder> {

        private int min;
        private int max;

        public Builder withMin(int min) {
            this.min = min;
            return self();
        }

        public Builder withMax(int max) {
            this.max = max;
            return self();
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public IntegerOption build() {
            return new IntegerOption(this);
        }
    }
}
