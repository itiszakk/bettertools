package org.itiszakk.bettertools.options;

import net.minecraft.text.Text;

public interface Option<T> {

    String getKey();

    String getTooltip();

    T getDefaultValue();

    T getValue();

    void setValue(T value);
}