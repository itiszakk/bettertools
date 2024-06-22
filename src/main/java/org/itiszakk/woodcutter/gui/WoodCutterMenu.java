package org.itiszakk.woodcutter.gui;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.text.Text;
import org.itiszakk.woodcutter.WoodCutter;
import org.itiszakk.woodcutter.config.WoodCutterConfiguration;

public class WoodCutterMenu implements ModMenuApi {

    private static final String MENU_TITLE = WoodCutter.MOD_ID + ".menu.title";

    @Override
    public ConfigScreenFactory<Screen> getModConfigScreenFactory() {
        return WoodCutterOptionsScreen::new;
    }

    private static class WoodCutterOptionsScreen extends GameOptionsScreen {

        public WoodCutterOptionsScreen(Screen screen) {
            super(screen, MinecraftClient.getInstance().options, Text.translatable(MENU_TITLE));
        }

        @Override
        protected void addOptions() {
            if (this.body == null) {
                return;
            }

            WoodCutterConfiguration.asOptions()
                    .forEach(this.body::addSingleOptionEntry);
        }
    }
}
