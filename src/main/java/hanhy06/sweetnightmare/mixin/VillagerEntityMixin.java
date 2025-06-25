package hanhy06.sweetnightmare.mixin;

import hanhy06.sweetnightmare.component.ModComponents;
import hanhy06.sweetnightmare.item.ModItems;
import net.minecraft.component.ComponentType;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.awt.*;

@Mixin(VillagerEntity.class)
public class VillagerEntityMixin {
    @Inject(at = @At("HEAD"), method = "interactMob", cancellable = true)
    private void interactMob(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir){
        VillagerEntity self = (VillagerEntity)(Object)this;
        ItemStack itemStack = player.getStackInHand(hand);

        if (itemStack.isOf(ModItems.CANDY_BUCKET) && !player.getWorld().isClient) {
            int candyCount = itemStack.getOrDefault(ModComponents.CANDY_COUNT, 0);

            if (!self.isBaby()) {
                if (candyCount <10) {
                    int temp  = Math.max((candyCount + (int)(Math.random() * 3) + 1), 10);

                    player.sendMessage(Text.of("Trick or Treat!"), false);
                    itemStack.set(ModComponents.CANDY_COUNT, temp);
                    itemStack.setDamage(temp);
                } else {
                    player.sendMessage(Text.of("Hey! You're not supposed to take the whole bowl!"), false);
                }
                cir.setReturnValue(ActionResult.SUCCESS);
            } else {
                player.sendMessage(Text.of("야 나도 애거든! 뭘 바라는거야"), false);
                cir.setReturnValue(ActionResult.SUCCESS);
            }
        }
    }
}
