package hanhy06.sweetnightmare.world.dimension;

import hanhy06.sweetnightmare.Sweetnightmare;
import hanhy06.sweetnightmare.component.ModComponents;
import hanhy06.sweetnightmare.item.ModItems;
import net.fabricmc.fabric.api.entity.event.v1.EntitySleepEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;

import java.util.Optional;
import java.util.OptionalLong;
import java.util.Set;

public class NightmareDimension {
    public static final Identifier NIGHTMARE_ID = Identifier.of(Sweetnightmare.MOD_ID,"nightmare");

    public static final RegistryKey<DimensionOptions> NIGHTMARE_KEY = RegistryKey.of(
            RegistryKeys.DIMENSION,NIGHTMARE_ID
    );
    public static final RegistryKey<World> NIGHTMARE_LEVEL_KEY = RegistryKey.of(
            RegistryKeys.WORLD, NIGHTMARE_ID
    );
    public static final RegistryKey<DimensionType> NIGHTMARE_TYPE_KEY = RegistryKey.of(
            RegistryKeys.DIMENSION_TYPE, Identifier.of(Sweetnightmare.MOD_ID,"nightmare_type")
    );

    public static void bootstrapType(Registerable<DimensionType> dimensionTypeRegisterable) {
        dimensionTypeRegisterable.register(
                NIGHTMARE_TYPE_KEY,
                new DimensionType(
                        OptionalLong.of(15000),
                        true,
                        false,
                        false,
                        false,
                        1.0,
                        false,
                        false,
                        0,
                        256,
                        256,
                        BlockTags.INFINIBURN_OVERWORLD,
                        DimensionTypes.OVERWORLD_ID,
                        0.0F,
                        Optional.empty(),
                        new DimensionType.MonsterSettings(false, true, UniformIntProvider.create(0, 7), 0)
                )
        );
    }

    public static void nightmareDimensionRegister(){
        Sweetnightmare.LOGGER.info("register nightmare dimension");
    }
}
