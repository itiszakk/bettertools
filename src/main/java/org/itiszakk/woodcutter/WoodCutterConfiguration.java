package org.itiszakk.woodcutter;

import com.terraformersmc.modmenu.config.option.BooleanConfigOption;
import net.minecraft.client.option.SimpleOption;

import java.util.List;

public class WoodCutterConfiguration {

    public static final BooleanConfigOption MOD_ENABLED = new BooleanConfigOption(
            WoodCutter.MOD_ID + ".enabled",
            true
    );

    public static final BooleanConfigOption ENABLED_IN_CREATIVE = new BooleanConfigOption(
            WoodCutter.MOD_ID + ".enabled_in_creative",
            false
    );

    public static final BooleanConfigOption ENABLED_WHILE_SNEAKING = new BooleanConfigOption(
            WoodCutter.MOD_ID + ".enabled_while_sneaking",
            false
    );

    private static final List<SimpleOption<?>> OPTIONS = List.of(
            MOD_ENABLED.asOption(),
            ENABLED_IN_CREATIVE.asOption(),
            ENABLED_WHILE_SNEAKING.asOption()
    );

    public static List<SimpleOption<?>> asOptions() {
        return OPTIONS;
    }
}
