package org.itiszakk.bettertools.options.impl;

import org.itiszakk.bettertools.options.AbstractOption;

public class BooleanOption extends AbstractOption<Boolean> {

    protected BooleanOption(Builder builder) {
        super(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends AbstractOption.Builder<Boolean, Builder> {

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public BooleanOption build() {
            return new BooleanOption(this);
        }
    }
}