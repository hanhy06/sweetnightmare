package hanhy06.sweetnightmare.item.function;

import hanhy06.sweetnightmare.component.ModComponents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CandyBucketItem extends Item {
    public CandyBucketItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return true;
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        return 0xC774AE;
    }
}
