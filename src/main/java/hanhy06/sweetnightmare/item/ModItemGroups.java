package hanhy06.sweetnightmare.item;

import hanhy06.sweetnightmare.Sweetnightmare;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final RegistryKey<ItemGroup> CUSTOM_ITEM_GROUP_KEY = RegistryKey.of(
            Registries.ITEM_GROUP.getKey(),
            Identifier.of(Sweetnightmare.MOD_ID, "item_group") // "item_group"이라는 이름의 아이템 그룹 식별자 생성
    );

    public static final ItemGroup CUSTOM_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.CANDY_BUCKET))
            .displayName(Text.translatable("item_groups.name"))
            .entries((enabledFeatures, entries) -> {
                entries.add(ModItems.CANDY_BUCKET);
            })
            .build();

    public static void registerItemGroups(){
        Registry.register(Registries.ITEM_GROUP, CUSTOM_ITEM_GROUP_KEY, CUSTOM_ITEM_GROUP);
        Sweetnightmare.LOGGER.info("register mod item groups");
    }
}
