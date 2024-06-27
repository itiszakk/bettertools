package org.itiszakk.bettertools.configuration;

import org.itiszakk.bettertools.BetterTools;
import org.itiszakk.bettertools.options.Option;
import org.itiszakk.bettertools.options.impl.BooleanOption;
import org.itiszakk.bettertools.options.impl.EnumOption;

import java.util.Collection;
import java.util.List;

public final class ChoppingConfiguration {

    public static final String CATEGORY = BetterTools.MOD_ID + ".chopping";

    public static BooleanOption ENABLE = BooleanOption.builder()
            .withKey(CATEGORY + ".enable")
            .withDefaultValue(true)
            .build();

    public static BooleanOption USE_IN_CREATIVE = BooleanOption.builder()
            .withKey(CATEGORY + ".use_in_creative")
            .withDefaultValue(false)
            .build();

    public static BooleanOption USE_WHILE_SNEAKING = BooleanOption.builder()
            .withKey(CATEGORY + ".use_while_sneaking")
            .withDefaultValue(false)
            .build();

    public static BooleanOption CUT_LEAVES = BooleanOption.builder()
            .withKey(CATEGORY + ".cut_leaves")
            .withDefaultValue(true)
            .build();

    public static EnumOption<CutMode> MODE = EnumOption.builder(CutMode.class)
            .withKey(CATEGORY + ".mode")
            .withDefaultValue(CutMode.REALISTIC)
            .build();

    public static final Collection<Option<?>> OPTIONS = List.of(
            ENABLE,
            USE_IN_CREATIVE,
            USE_WHILE_SNEAKING,
            CUT_LEAVES,
            MODE
    );

    public enum CutMode {
        COMPLETE,
        REALISTIC
    }
}
