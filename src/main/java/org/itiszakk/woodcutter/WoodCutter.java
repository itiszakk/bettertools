package org.itiszakk.woodcutter;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WoodCutter implements ModInitializer {

	public static final String ID = "woodcutter";

    public static final Logger LOGGER = LoggerFactory.getLogger(ID);

	@Override
	public void onInitialize() {

		LOGGER.info("Initializing ");
	}
}