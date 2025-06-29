package hanhy06.sweetnightmare.mixin;

import hanhy06.sweetnightmare.Sweetnightmare;
import hanhy06.sweetnightmare.component.ModComponents;
import hanhy06.sweetnightmare.item.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Set;

import static hanhy06.sweetnightmare.world.dimension.NightmareDimension.NIGHTMARE_LEVEL_KEY;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {
    @Shadow @Final private MinecraftServer server;

    @Inject(at = @At("RETURN"), method = "wakeUp")
    public void wakeUp(boolean skipSleepTimer, boolean updateSleepingPlayers, CallbackInfo ci) {
        ServerPlayerEntity serverPlayer = (ServerPlayerEntity) (Object) this;

        for (ItemStack itemStack : serverPlayer.getInventory().getMainStacks()) {
            boolean isCandyBucket = itemStack.isOf(ModItems.CANDY_BUCKET);
            int candyCount = itemStack.getOrDefault(ModComponents.CANDY_COUNT, 0);

            if (isCandyBucket && candyCount >= 10) {
                this.server.execute(() -> {
                    ServerWorld nightmareWorld = server.getWorld(NIGHTMARE_LEVEL_KEY);

                    if (nightmareWorld == null) {
                        Sweetnightmare.LOGGER.error("Nightmare dimension not found! Teleportation cancelled.");
                        return;
                    }

                    serverPlayer.teleport(
                            nightmareWorld,
                            serverPlayer.getX(),
                            serverPlayer.getY(),
                            serverPlayer.getZ(),
                            Set.of(),
                            serverPlayer.getYaw(),
                            serverPlayer.getPitch(),
                            true
                    );
                });

                return;
            }
        }
    }
}