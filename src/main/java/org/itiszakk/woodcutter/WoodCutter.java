package org.itiszakk.woodcutter;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import org.itiszakk.woodcutter.event.WoodCutterEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WoodCutter implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger(WoodCutterConstants.MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing");

        PlayerBlockBreakEvents.AFTER.register(new WoodCutterEvent());
    }
}