package org.itiszakk.woodcutter.config;

import com.terraformersmc.modmenu.config.option.BooleanConfigOption;
import com.terraformersmc.modmenu.config.option.EnumConfigOption;
import net.minecraft.client.option.SimpleOption;
import org.itiszakk.woodcutter.WoodCutter;

import java.util.List;

public class WoodCutterConfiguration {

    public static final BooleanConfigOption ENABLE = new BooleanConfigOption(
            WoodCutter.MOD_ID + ".enable",
            true
    );

    public static final BooleanConfigOption USE_IN_CREATIVE = new BooleanConfigOption(
            WoodCutter.MOD_ID + ".use_in_creative",
            false
    );

    public static final BooleanConfigOption USE_WHILE_SNEAKING = new BooleanConfigOption(
            WoodCutter.MOD_ID + ".use_while_sneaking",
            false
    );

    public static final BooleanConfigOption CUT_LEAVES = new BooleanConfigOption(
            WoodCutter.MOD_ID + ".cut_leaves",
            true
    );

    public static final EnumConfigOption<CutMode> CUT_MODE = new EnumConfigOption<>(
            WoodCutter.MOD_ID + ".cut_mode",
            CutMode.REALISTIC
    );

    private static final List<SimpleOption<?>> OPTIONS = List.of(
            ENABLE.asOption(),
            USE_IN_CREATIVE.asOption(),
            USE_WHILE_SNEAKING.asOption(),
            CUT_LEAVES.asOption(),
            CUT_MODE.asOption()
    );

    public static List<SimpleOption<?>> asOptions() {
        return OPTIONS;
    }
}
