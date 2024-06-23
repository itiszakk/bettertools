package org.itiszakk.bettertools;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import org.itiszakk.bettertools.events.BetterToolsEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BetterTools implements ModInitializer {

    public static final String MOD_ID = "bettertools";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing");

        PlayerBlockBreakEvents.AFTER.register(new BetterToolsEvent());
    }
}