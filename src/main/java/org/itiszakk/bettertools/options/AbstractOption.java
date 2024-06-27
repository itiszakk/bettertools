package org.itiszakk.bettertools.options;

public abstract class AbstractOption<T> implements Option<T> {

    private final String key;
    private final String tooltip;
    private final T defaultValue;
    protected T value;

    protected AbstractOption(Builder<T, ?> builder) {
        this.key = builder.key;
        this.tooltip = builder.tooltip;
        this.defaultValue = builder.defaultValue;
        this.value = builder.defaultValue;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getTooltip() {
        return tooltip;
    }

    @Override
    public T getDefaultValue() {
        return defaultValue;
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public void setValue(T value) {
        this.value = value;
    }

    public static abstract class Builder<T, B extends Builder<T, B>> {
        protected String key;
        protected String tooltip;
        protected T defaultValue;

        public B withKey(String key) {
            this.key = key;
            return self();
        }

        public B withTooltip(String tooltip) {
            this.tooltip = tooltip;
            return self();
        }

        public B withDefaultValue(T defaultValue) {
            this.defaultValue = defaultValue;
            return self();
        }

        protected abstract B self();

        public abstract AbstractOption<T> build();
    }
}
