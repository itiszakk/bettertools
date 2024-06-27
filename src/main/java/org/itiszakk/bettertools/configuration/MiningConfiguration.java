package org.itiszakk.bettertools.configuration;

import org.itiszakk.bettertools.BetterTools;
import org.itiszakk.bettertools.options.Option;
import org.itiszakk.bettertools.options.impl.BooleanOption;
import org.itiszakk.bettertools.options.impl.IntegerOption;

import java.util.Collection;
import java.util.List;

public final class MiningConfiguration {

    public static final String CATEGORY = BetterTools.MOD_ID + ".mining";

    public static BooleanOption ENABLE = BooleanOption.builder()
            .withKey(CATEGORY + ".enable")
            .withDefaultValue(true)
            .build();

    public static IntegerOption WIDTH = IntegerOption.builder()
            .withKey(CATEGORY + ".width")
            .withDefaultValue(3)
            .withMin(1)
            .withMax(64)
            .build();

    public static IntegerOption HEIGHT = IntegerOption.builder()
            .withKey(CATEGORY + ".height")
            .withDefaultValue(3)
            .withMin(1)
            .withMax(64)
            .build();

    public static final Collection<Option<?>> OPTIONS = List.of(
            ENABLE,
            WIDTH,
            HEIGHT
    );
}
