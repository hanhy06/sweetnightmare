package hanhy06.sweetnightmare.mixin;

import hanhy06.sweetnightmare.component.ModComponents;
import hanhy06.sweetnightmare.item.ModItems;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(VillagerEntity.class)
public class VillagerEntityMixin {
    @Inject(at = @At("HEAD"), method = "interactMob", cancellable = true)
    private void interactMob(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir){
        VillagerEntity self = (VillagerEntity)(Object)this;
        ItemStack itemStack = player.getStackInHand(hand);

        if (!player.getWorld().isClient && itemStack.isOf(ModItems.CANDY_BUCKET) && !self.isBaby()){
            player.sendMessage(Text.of("Trick or Treat!"), false);

            itemStack.set(ModComponents.CANDY_COUNT,itemStack.get(ModComponents.CANDY_COUNT)+1);

            cir.setReturnValue(ActionResult.SUCCESS);
        }
    }
}