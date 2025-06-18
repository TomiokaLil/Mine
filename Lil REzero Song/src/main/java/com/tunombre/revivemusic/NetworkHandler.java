package com.tunombre.revivemusic;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class NetworkHandler {
    private static final String PROTOCOL = "1";
    private static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
        new ResourceLocation("revive_music", "main"),
        () -> PROTOCOL, PROTOCOL::equals, PROTOCOL::equals
    );

    private static int packetId = 0;

    public static void register() {
        CHANNEL.registerMessage(packetId++, MusicPacket.class, MusicPacket::encode, MusicPacket::decode, MusicPacket::handle);
    }

    public static void sendMusicPacket(net.minecraft.server.level.ServerPlayer player) {
        CHANNEL.sendTo(new MusicPacket(), player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
    }

    public static class MusicPacket {
        public MusicPacket() {}

        public static void encode(MusicPacket msg, FriendlyByteBuf buf) {}
        public static MusicPacket decode(FriendlyByteBuf buf) {
            return new MusicPacket();
        }

        public static void handle(MusicPacket msg, Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                net.minecraft.client.Minecraft mc = net.minecraft.client.Minecraft.getInstance();
                SoundEvent customSound = ForgeRegistries.SOUND_EVENTS.getVal                mc.level.playSound(mc.player, mc.player.blockPosition(), customSound, SoundSource.MUSIC, 1.0F, 1.0F);                mc.level.playSound(mc.player, mc.player.blockPosition(), customSound, SoundSource.MUSIC, 1.0F, 1.0F);                mc.level.playSound(mc.player, mc.player.blockPosition(), customSound, SoundSource.MUSIC, 1.0F, 1.0F);                mc.level.playSound(mc.player, mc.player.blockPosition(), customSound, SoundSource.MUSIC, 1.0F, 1.0F);                mc.level.playSound(mc.player, mc.player.blockPosition(), customSound, SoundSource.MUSIC, 1.0F, 1.0F);ue(new ResourceLocation("revive_music", "revive_song"));
                if (customSound != null && mc.level != null) {
                    mc.player.playSound(customSound, SoundSource.MUSIC, 1.0F, 1.0F);
                }
            });
            ctx.get().setPacketHandled(true);
        }
    }
}