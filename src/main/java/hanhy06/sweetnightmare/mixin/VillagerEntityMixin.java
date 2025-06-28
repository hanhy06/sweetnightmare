package hanhy06.sweetnightmare.mixin;

import hanhy06.sweetnightmare.component.ModComponents;
import hanhy06.sweetnightmare.item.ModItems;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.CustomModelDataComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(VillagerEntity.class)
public class VillagerEntityMixin {

    @Unique
    private static final int MAX_CANDY = 10;
    @Unique
    private static final float TRICK_CHANCE = 0.2f;

    @Inject(at = @At("HEAD"), method = "interactMob", cancellable = true)
    private void interactMob(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        VillagerEntity self = (VillagerEntity) (Object) this;
        World world = player.getWorld();
        ItemStack itemStack = player.getStackInHand(hand);

        if (world.isClient() || !itemStack.isOf(ModItems.CANDY_BUCKET)) {
            return;
        }

        if (self.isBaby()) {
            player.sendMessage(Text.translatable("item.sweetnightmare.candy_bucket_use_baby"), false);
            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_VILLAGER_NO, SoundCategory.PLAYERS, 1.0F, 1.0F);
            cir.setReturnValue(ActionResult.SUCCESS);
            return;
        }

        int candyCount = itemStack.getOrDefault(ModComponents.CANDY_COUNT, 0);

        if (candyCount >= MAX_CANDY) {
            player.sendMessage(Text.translatable("item.sweetnightmare.candy_bucket_use_full"), false);
            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_VILLAGER_NO, SoundCategory.PLAYERS, 1.0F, 1.0F);
            cir.setReturnValue(ActionResult.SUCCESS);
            return;
        }

        if (world.random.nextFloat() < TRICK_CHANCE) {
            handleTrick(player,world);
        } else {
            handleTreat(player, itemStack, candyCount, world);
        }

        cir.setReturnValue(ActionResult.SUCCESS);
    }

    @Unique
    private void handleTrick(PlayerEntity player,World world) {
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100));
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 100));
        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.AMBIENT_CAVE, SoundCategory.PLAYERS, 1.0F, 1.0F);
        player.sendMessage(Text.translatable("item.sweetnightmare.candy_bucket_use_fail"), false);
    }

    @Unique
    private void handleTreat(PlayerEntity player, ItemStack stack, int currentCandy, World world) {
        int candyGained = world.random.nextInt(3) + 1;
        int newCandyCount = Math.min(currentCandy + candyGained, MAX_CANDY);

        stack.set(ModComponents.CANDY_COUNT, newCandyCount);
        updateCustomModelData(stack, newCandyCount);

        player.sendMessage(Text.translatable("item.sweetnightmare.candy_bucket_use_adult"), false);
        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BLOCK_AZALEA_LEAVES_STEP, SoundCategory.PLAYERS, 1.0F, 1.0F);
    }

    @Unique
    private void updateCustomModelData(ItemStack stack, int candyCount) {
        CustomModelDataComponent component;
        if (candyCount >= MAX_CANDY) {
            component = new CustomModelDataComponent(List.of(), List.of(), List.of("full"), List.of());
        } else if (candyCount >= 5) {
            component = new CustomModelDataComponent(List.of(), List.of(), List.of("half"), List.of());
        } else if (candyCount > 0) {
            component = new CustomModelDataComponent(List.of(), List.of(), List.of("low"), List.of());
        } else {
            component = new CustomModelDataComponent(List.of(), List.of(), List.of("default"), List.of());
        }
        stack.set(DataComponentTypes.CUSTOM_MODEL_DATA, component);
    }
}