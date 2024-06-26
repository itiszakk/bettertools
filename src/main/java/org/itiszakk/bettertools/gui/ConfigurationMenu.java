package org.itiszakk.bettertools.gui;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.gui.entries.BooleanListEntry;
import me.shedaniel.clothconfig2.gui.entries.EnumListEntry;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.itiszakk.bettertools.BetterTools;
import org.itiszakk.bettertools.configuration.ChoppingConfiguration;
import org.itiszakk.bettertools.options.Option;
import org.itiszakk.bettertools.options.impl.BooleanOption;
import org.itiszakk.bettertools.options.impl.EnumOption;

import java.util.Collection;
import java.util.Objects;

public class ConfigurationMenu implements ModMenuApi {

    private static final String MENU_TITLE = BetterTools.MOD_ID + ".menu.title";

    @Override
    public ConfigScreenFactory<Screen> getModConfigScreenFactory() {
        return this::buildConfigurationMenu;
    }

    private Screen buildConfigurationMenu(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.translatable(MENU_TITLE));

        buildCategory(ChoppingConfiguration.CATEGORY, ChoppingConfiguration.OPTIONS, builder);

        return builder.build();
    }

    private void buildCategory(String key, Collection<Option<?>> options, ConfigBuilder builder) {
        ConfigCategory category = builder.getOrCreateCategory(Text.translatable(key));
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        options.stream()
                .map(option -> buildEntry(option, entryBuilder))
                .filter(Objects::nonNull)
                .forEach(category::addEntry);
    }

    private AbstractConfigListEntry<?> buildEntry(Option<?> option, ConfigEntryBuilder builder) {
        if (option instanceof BooleanOption booleanOption) {
            return buildBooleanEntry(booleanOption, builder);
        }

        if (option instanceof EnumOption<?> enumOption) {
            return buildEnumEntry(enumOption, builder);
        }

        return null;
    }

    private BooleanListEntry buildBooleanEntry(BooleanOption option, ConfigEntryBuilder builder) {
        return builder.startBooleanToggle(option.getKey(), option.getValue())
                .setDefaultValue(option.getDefaultValue())
                .setSaveConsumer(option::setValue)
                .build();
    }

    private <T extends Enum<T>> EnumListEntry<T> buildEnumEntry(EnumOption<T> option, ConfigEntryBuilder builder) {
        return builder.startEnumSelector(option.getKey(), option.getEnumClass(), option.getValue())
                .setDefaultValue(option.getDefaultValue())
                .setSaveConsumer(option::setValue)
                .build();
    }
}