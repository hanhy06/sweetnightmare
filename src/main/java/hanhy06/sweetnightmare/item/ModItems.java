package hanhy06.sweetnightmare.item;

import hanhy06.sweetnightmare.component.ModComponents;
import hanhy06.sweetnightmare.entrypoint.Sweetnightmare;
import hanhy06.sweetnightmare.item.custom.CandyBucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModItems {
    public static final Item CANDY_BUCKET = registerModItem(
            "candy_bucket",
                CandyBucketItem::new,
                new Item.Settings()
                        .maxCount(1)
                        .maxDamage(10)
                        .component(ModComponents.CANDY_COUNT,0)
            );

    private static Item registerModItem(String name, Function<Item.Settings, Item> factory, Item.Settings settings) {
        Identifier id = Identifier.of(Sweetnightmare.MOD_ID, name);
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, id);
        
        return Items.register(key, factory, settings);
    }

    public static void registerModItems() {
        Sweetnightmare.LOGGER.info("register mod items");
    }
}
