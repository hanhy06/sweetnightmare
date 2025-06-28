package hanhy06.sweetnightmare.component;

import com.mojang.serialization.Codec;
import hanhy06.sweetnightmare.Sweetnightmare;
import net.minecraft.component.ComponentType;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.UnaryOperator;

public class ModComponents {
    public static final ComponentType<Integer> CANDY_COUNT = register("candy_count",builder -> builder.codec(Codec.INT).packetCodec(PacketCodecs.VAR_INT).cache());

    private static <T> ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(
                Registries.DATA_COMPONENT_TYPE,
                Identifier.of(Sweetnightmare.MOD_ID, name),
                builderOperator.apply(ComponentType.builder()).build()
        );
    }

    public static void registerModComponents() {
        Sweetnightmare.LOGGER.info("register mod component");
    }
}
