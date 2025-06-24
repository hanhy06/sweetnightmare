package hanhy06.sweetnightmare.item;

import hanhy06.sweetnightmare.entrypoint.Sweetnightmare;
import hanhy06.sweetnightmare.item.custom.CandyBasketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModItems {
    private static final Item CANDY_BASKET = registerModItem(
            "candy_basket",
                CandyBasketItem::new,
                new Item.Settings()
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
