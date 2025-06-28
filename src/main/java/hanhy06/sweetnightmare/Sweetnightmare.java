package hanhy06.sweetnightmare;

import hanhy06.sweetnightmare.component.ModComponents;
import hanhy06.sweetnightmare.item.ModItemGroups;
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

		ModComponents.registerModComponents();

		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();

//		CatmullRom catmullRom = new CatmullRom(
//				List.of(
//						new Vec3d(100,100,100),
//						new Vec3d(110,110,100),
//						new Vec3d(120,120,100),
//						new Vec3d(130,130,100)
//				)
//		);
//
//		WorldRenderEvents.AFTER_ENTITIES.register(
//				worldRenderContext -> {
//					ClientWorld world =  worldRenderContext.world();
//					RenderTickCounter tickCounter = worldRenderContext.tickCounter();
//
//					Vec3d vec3d = catmullRom.interpolation(tickCounter.getDynamicDeltaTicks());
//					world.addParticleClient(
//							ParticleTypes.FLAME,
//							vec3d.x,
//							vec3d.y,
//							vec3d.z,
//							0,
//							0,
//							0
//					);
//				}
//		);
	}
}