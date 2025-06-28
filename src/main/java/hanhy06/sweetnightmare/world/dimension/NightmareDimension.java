package hanhy06.sweetnightmare.world.dimension;

import hanhy06.sweetnightmare.Sweetnightmare;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;

import java.util.Optional;
import java.util.OptionalLong;

public class NightmareDimension {
    public static final Identifier NIGHTMARE_ID = Identifier.of(Sweetnightmare.MOD_ID,"nightmare");
    public static final RegistryKey<DimensionOptions> NIGHTMARE_DIMENSION_KEY = RegistryKey.of(
            RegistryKeys.DIMENSION, Identifier.of(Sweetnightmare.MOD_ID,"nightmare")
    );
    public static final RegistryKey<DimensionType> NIGHTMARE_DIMENSION_TYPE = RegistryKey.of(
            RegistryKeys.DIMENSION_TYPE, Identifier.of(Sweetnightmare.MOD_ID,"nightmare_type")
    );
    public static final RegistryKey<World> NIGHTMARE_DIMENSION_LEVEL_KEY = RegistryKey.of(
            RegistryKeys.WORLD, Identifier.of(Sweetnightmare.MOD_ID,"nightmare")
    );

    public static void bootstrap(Registerable<DimensionType> dimensionTypeRegisterable) {
        dimensionTypeRegisterable.register(
                NIGHTMARE_DIMENSION_TYPE,
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
                        NIGHTMARE_ID,
                        0.0F,
                        Optional.empty(),
                        new DimensionType.MonsterSettings(false, true, UniformIntProvider.create(0, 7), 0)
                )
        );
    }
}
