package org.itiszakk.bettertools.options.impl;

import net.minecraft.text.Text;
import org.itiszakk.bettertools.options.Option;

public class BooleanOption implements Option<Boolean> {

    private final Text key;
    private final Boolean defaultValue;
    private Boolean value;

    private BooleanOption(Text key, Boolean defaultValue) {
        this.key = key;
        this.defaultValue = defaultValue;
        this.value = defaultValue;
    }

    public static BooleanOption of(String key, Boolean defaultValue) {
        return new BooleanOption(Text.translatable(key), defaultValue);
    }

    @Override
    public Text getKey() {
        return key;
    }

    @Override
    public Boolean getDefaultValue() {
        return defaultValue;
    }

    @Override
    public Boolean getValue() {
        return value;
    }

    @Override
    public void setValue(Boolean value) {
        this.value = value;
    }
}
