package org.itiszakk.bettertools.options.impl;

import org.itiszakk.bettertools.options.AbstractOption;

public class EnumOption<T extends Enum<T>> extends AbstractOption<T> {

    private final Class<T> enumClass;

    protected EnumOption(Builder<T> builder) {
        super(builder);
        this.enumClass = builder.enumClass;
    }

    public Class<T> getEnumClass() {
        return enumClass;
    }

    public static <T extends Enum<T>> Builder<T> builder(Class<T> enumClass) {
        return new Builder<>(enumClass);
    }

    public static class Builder<T extends Enum<T>> extends AbstractOption.Builder<T, Builder<T>> {

        private final Class<T> enumClass;

        public Builder(Class<T> enumClass) {
            this.enumClass = enumClass;
        }

        @Override
        protected Builder<T> self() {
            return this;
        }

        @Override
        public EnumOption<T> build() {
            return new EnumOption<>(this);
        }
    }
}
