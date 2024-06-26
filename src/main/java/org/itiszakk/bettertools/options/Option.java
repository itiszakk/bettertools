package org.itiszakk.bettertools.options;

import net.minecraft.text.Text;

public interface Option<T> {

    Text getKey();

    T getDefaultValue();

    T getValue();

    void setValue(T value);
}