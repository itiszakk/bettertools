package org.itiszakk.bettertools.options;

public interface Option<T> {

    String getKey();

    String getTooltip();

    T getDefaultValue();

    T getValue();

    void setValue(T value);
}