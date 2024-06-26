package org.itiszakk.bettertools.options.impl;

import net.minecraft.text.Text;
import org.itiszakk.bettertools.options.Option;

public class EnumOption<T extends Enum<T>> implements Option<T> {

    private final Text key;
    private final T defaultValue;
    private final Class<T> enumClass;
    private T value;

    private EnumOption(Text key, T defaultValue) {
        this.key = key;
        this.defaultValue = defaultValue;
        this.enumClass = defaultValue.getDeclaringClass();
        this.value = defaultValue;
    }

    public static <T extends Enum<T>> EnumOption<T> of(String key, T defaultValue) {
        return new EnumOption<>(Text.translatable(key), defaultValue);
    }

    @Override
    public Text getKey() {
        return key;
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

    public Class<T> getEnumClass() {
        return enumClass;
    }
}
