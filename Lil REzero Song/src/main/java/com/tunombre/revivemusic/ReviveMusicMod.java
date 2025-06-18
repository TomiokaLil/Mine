package com.tunombre.revivemusic;

import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@Mod("revive_music")
@EventBusSubscriber
public class ReviveMusicMod {
    public ReviveMusicMod() {
        NetworkHandler.register(); // Registrar canal de red
    }

    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        if (!event.getEntity().level.isClientSide()) {
            NetworkHandler.sendMusicPacket((net.minecraft.server.level.ServerPlayer) event.getEntity());
        }
    }
}