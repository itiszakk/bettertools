package org.itiszakk.bettertools.gui;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.text.Text;
import org.itiszakk.bettertools.BetterTools;
import org.itiszakk.bettertools.actions.chopping.ChoppingActionConfig;

public class BetterToolsMenu implements ModMenuApi {

    private static final String MENU_TITLE = BetterTools.MOD_ID + ".menu.title";

    @Override
    public ConfigScreenFactory<Screen> getModConfigScreenFactory() {
        return BetterToolsOptionsScreen::new;
    }

    private static class BetterToolsOptionsScreen extends GameOptionsScreen {

        public BetterToolsOptionsScreen(Screen screen) {
            super(screen, MinecraftClient.getInstance().options, Text.translatable(MENU_TITLE));
        }

        @Override
        protected void addOptions() {
            if (this.body == null) {
                return;
            }

            ChoppingActionConfig.asOptions()
                    .forEach(this.body::addSingleOptionEntry);
        }
    }
}
