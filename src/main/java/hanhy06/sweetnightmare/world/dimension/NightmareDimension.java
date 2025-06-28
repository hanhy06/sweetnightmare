package hanhy06.sweetnightmare.world.dimension;

import hanhy06.sweetnightmare.entrypoint.Sweetnightmare;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;

import java.util.Optional;
import java.util.OptionalLong;

public class NightmareDimension {
    public static RegistryKey<DimensionOptions> NIGHTMARE_KEY = RegistryKey.of(
            RegistryKeys.DIMENSION, Identifier.of(Sweetnightmare.MOD_ID,"nightmare")
    );
    public static RegistryKey<World> NIGHTMARE_LEVEL_KEY = RegistryKey.of(
            RegistryKeys.WORLD, Identifier.of(Sweetnightmare.MOD_ID,"nightmare")
    );
    public static RegistryKey<DimensionType> NIGHTMARE_DIM_TYPE = RegistryKey.of(
            RegistryKeys.DIMENSION_TYPE, Identifier.of(Sweetnightmare.MOD_ID,"nightmare_type")
    );

    public static void bootstrapType(Registerable<DimensionType> context) {
        context.register(NIGHTMARE_DIM_TYPE, new DimensionType(
                OptionalLong.of(15000),//밤
                true, // 하늘광원 존재 여부 (스카이라이트 없음)
                false, // 천장 여부 (천장 없음)
                false, // 초고온 차원 여부
                true, // 자연적 차원 여부
                1.0, // 좌표 스케일 비율
                false, // 침대 사용 가능 여부
                false, // 리스폰 앵커 사용 가능 여부
                0, // 최소 Y 좌표
                256, // 높이
                256, // 논리적 높이
                BlockTags.INFINIBURN_OVERWORLD, // 무한 화염 태그
                DimensionTypes.OVERWORLD_ID, // 효과 위치 (예: 안개, 하늘 색)
                0.3f, // 주변 광량
                Optional.empty(),//하늘 높이
                new DimensionType.MonsterSettings(
                        true,
                        false,
                        UniformIntProvider.create(0, 0),
                        0
                ))
        );
    }

}
