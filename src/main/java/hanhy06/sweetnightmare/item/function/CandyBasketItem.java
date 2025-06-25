package hanhy06.sweetnightmare.item.function;

import hanhy06.sweetnightmare.component.ModComponents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CandyBasketItem extends Item {
    public CandyBasketItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return true;
    }

    @Override
    public int getItemBarStep(ItemStack stack) {
        return stack.get(ModComponents.CANDY_COUNT);
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        return 0xC774AE;
    }
}
