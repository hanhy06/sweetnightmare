package hanhy06.sweetnightmare.item.function;

import hanhy06.sweetnightmare.component.ModComponents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.CustomModelDataComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CandyBucketItem extends Item {
    public CandyBucketItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return stack.getOrDefault(ModComponents.CANDY_COUNT, 0) < 10;
    }

    @Override
    public int getItemBarStep(ItemStack stack) {
        return stack.getOrDefault(ModComponents.CANDY_COUNT,0);
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        return 0xC774AE;
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot) {
        int value = stack.getOrDefault(ModComponents.CANDY_COUNT,0);

        CustomModelDataComponent component =  new CustomModelDataComponent(
                List.of(), List.of(), List.of("default"), List.of()
        );;
        if (1<=value && value<5){
            component = new CustomModelDataComponent(
                    List.of(), List.of(), List.of("low"), List.of()
            );
        } else if (5<=value && value<9) {
            component = new CustomModelDataComponent(
                    List.of(), List.of(), List.of("half"), List.of()
            );
        } else if (value == 10) {
            component = new CustomModelDataComponent(
                    List.of(), List.of(), List.of("full"), List.of()
            );
        }
        stack.set(DataComponentTypes.CUSTOM_MODEL_DATA, component);

        super.inventoryTick(stack, world, entity, slot);
    }
}
