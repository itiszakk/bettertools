package org.itiszakk.bettertools.config;

import com.terraformersmc.modmenu.config.option.BooleanConfigOption;
import com.terraformersmc.modmenu.config.option.EnumConfigOption;
import net.minecraft.client.option.SimpleOption;
import org.itiszakk.bettertools.BetterTools;

import java.util.List;

public class ChoppingActionConfig {

    private static final String PREFIX = BetterTools.MOD_ID + ".chopping";

    public static final BooleanConfigOption ENABLE = new BooleanConfigOption(
            PREFIX + ".enable",
            true
    );

    public static final EnumConfigOption<CutMode> MODE = new EnumConfigOption<>(
            PREFIX + ".mode",
            CutMode.REALISTIC
    );

    public static final BooleanConfigOption USE_IN_CREATIVE = new BooleanConfigOption(
            PREFIX + ".use_in_creative",
            false
    );

    public static final BooleanConfigOption USE_WHILE_SNEAKING = new BooleanConfigOption(
            PREFIX + ".use_while_sneaking",
            false
    );

    public static final BooleanConfigOption CUT_LEAVES = new BooleanConfigOption(
            PREFIX + ".cut_leaves",
            true
    );

    private static final List<SimpleOption<?>> OPTIONS = List.of(
            ENABLE.asOption(),
            MODE.asOption(),
            USE_IN_CREATIVE.asOption(),
            USE_WHILE_SNEAKING.asOption(),
            CUT_LEAVES.asOption()
    );

    public static List<SimpleOption<?>> asOptions() {
        return OPTIONS;
    }
}
