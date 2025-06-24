package hanhy06.sweetnightmare.entrypoint;

import hanhy06.sweetnightmare.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sweetnightmare implements ModInitializer {
	public static final String MOD_ID = "sweetnightmare";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info(MOD_ID+"mod load");

		ModItems.registerModItems();
	}
}