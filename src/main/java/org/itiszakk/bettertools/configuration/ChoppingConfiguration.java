package org.itiszakk.bettertools.configuration;

import org.itiszakk.bettertools.BetterTools;
import org.itiszakk.bettertools.options.impl.BooleanOption;
import org.itiszakk.bettertools.options.impl.EnumOption;
import org.itiszakk.bettertools.options.Option;

import java.util.Collection;
import java.util.List;

public final class ChoppingConfiguration {

    public static final String CATEGORY = BetterTools.MOD_ID + ".chopping";

    public static BooleanOption ENABLE = BooleanOption.of(CATEGORY + ".enable", true);
    public static BooleanOption USE_IN_CREATIVE = BooleanOption.of(CATEGORY + ".use_in_creative", false);
    public static BooleanOption USE_WHILE_SNEAKING = BooleanOption.of(CATEGORY + ".use_while_sneaking", false);
    public static BooleanOption CUT_LEAVES = BooleanOption.of(CATEGORY + ".cut_leaves", true);
    public static EnumOption<CutMode> MODE = EnumOption.of(CATEGORY + ".mode", CutMode.REALISTIC);

    public static final Collection<Option<?>> OPTIONS = List.of(
            ENABLE,
            USE_IN_CREATIVE,
            USE_WHILE_SNEAKING,
            CUT_LEAVES,
            MODE);

    public enum CutMode {
        COMPLETE,
        REALISTIC
    }
}
