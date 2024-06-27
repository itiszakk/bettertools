package org.itiszakk.bettertools.gui;

import com.google.common.collect.ImmutableMap;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.gui.entries.BooleanListEntry;
import me.shedaniel.clothconfig2.gui.entries.EnumListEntry;
import me.shedaniel.clothconfig2.gui.entries.IntegerListEntry;
import me.shedaniel.clothconfig2.gui.entries.IntegerSliderEntry;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.apache.commons.lang3.StringUtils;
import org.itiszakk.bettertools.BetterTools;
import org.itiszakk.bettertools.configuration.ChoppingConfiguration;
import org.itiszakk.bettertools.configuration.MiningConfiguration;
import org.itiszakk.bettertools.options.Option;
import org.itiszakk.bettertools.options.impl.BooleanOption;
import org.itiszakk.bettertools.options.impl.EnumOption;
import org.itiszakk.bettertools.options.impl.IntegerOption;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

public class ConfigurationMenu implements ModMenuApi {

    private static final String MENU_TITLE = BetterTools.MOD_ID + ".menu.title";

    private static final Map<String, Collection<Option<?>>> OPTIONS_BY_CATEGORY = ImmutableMap.of(
            ChoppingConfiguration.CATEGORY, ChoppingConfiguration.OPTIONS,
            MiningConfiguration.CATEGORY, MiningConfiguration.OPTIONS
    );

    @Override
    public ConfigScreenFactory<Screen> getModConfigScreenFactory() {
        return screen -> {

            ConfigBuilder builder = ConfigBuilder.create()
                    .setParentScreen(screen)
                    .setTitle(Text.translatable(MENU_TITLE));

            OPTIONS_BY_CATEGORY.forEach((key, options) ->
                    buildCategory(key, options, builder, builder.entryBuilder()));

            return builder.build();
        };
    }

    private void buildCategory(String key,
                               Collection<Option<?>> options,
                               ConfigBuilder configBuilder,
                               ConfigEntryBuilder entryBuilder) {
        ConfigCategory category = configBuilder.getOrCreateCategory(Text.translatable(key));

        options.stream()
                .map(option -> buildEntry(option, entryBuilder))
                .filter(Objects::nonNull)
                .forEach(category::addEntry);
    }

    private AbstractConfigListEntry<?> buildEntry(Option<?> option, ConfigEntryBuilder builder) {
        if (option instanceof BooleanOption booleanOption) {
            return buildBooleanToggle(booleanOption, builder);
        }

        if (option instanceof IntegerOption integerOption) {
            return integerOption.isSlider()
                    ? buildIntegerSlider(integerOption, builder)
                    : buildIntegerField(integerOption, builder);
        }

        if (option instanceof EnumOption<?> enumOption) {
            return buildEnumEntry(enumOption, builder);
        }

        return null;
    }

    private BooleanListEntry buildBooleanToggle(BooleanOption option, ConfigEntryBuilder builder) {
        return builder.startBooleanToggle(Text.translatable(option.getKey()), option.getValue())
                .setDefaultValue(option.getDefaultValue())
                .setTooltip(getTooltipOrEmpty(option))
                .setSaveConsumer(option::setValue)
                .build();
    }

    private IntegerListEntry buildIntegerField(IntegerOption option, ConfigEntryBuilder builder) {
        return builder.startIntField(Text.translatable(option.getKey()), option.getValue())
                .setDefaultValue(option.getDefaultValue())
                .setTooltip(getTooltipOrEmpty(option))
                .setSaveConsumer(option::setValue)
                .setTooltip()
                .build();
    }

    private IntegerSliderEntry buildIntegerSlider(IntegerOption option, ConfigEntryBuilder builder) {
        return builder.startIntSlider(Text.translatable(option.getKey()), option.getValue(), option.getMin(), option.getMax())
                .setDefaultValue(option.getDefaultValue())
                .setTooltip(getTooltipOrEmpty(option))
                .setSaveConsumer(option::setValue)
                .build();
    }

    private <T extends Enum<T>> EnumListEntry<T> buildEnumEntry(EnumOption<T> option, ConfigEntryBuilder builder) {
        return builder.startEnumSelector(Text.translatable(option.getKey()), option.getEnumClass(), option.getValue())
                .setDefaultValue(option.getDefaultValue())
                .setTooltip(getTooltipOrEmpty(option))
                .setSaveConsumer(option::setValue)
                .setEnumNameProvider(value -> getEnumKey(option, value))
                .build();
    }

    private Text getTooltipOrEmpty(Option<?> option) {
        return StringUtils.isNotBlank(option.getTooltip())
                ? Text.translatable(option.getTooltip())
                : Text.empty();
    }

    private Text getEnumKey(EnumOption<?> option, Enum<?> value) {
        return Text.translatable(option.getKey() + "." + value.name().toLowerCase());
    }
}