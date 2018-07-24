package io.minimum.minecraft.velocity.protocol.netty;

import io.minimum.minecraft.velocity.protocol.ProtocolConstants;
import io.netty.channel.Channel;

public class MinecraftPipelineUtils {
    public static void strapPipeline(Channel ch) {
        ch.pipeline().addLast("legacy-ping-decode", new LegacyPingDecoder());
        ch.pipeline().addLast("frame-decoder", new MinecraftVarintFrameDecoder());
        ch.pipeline().addLast("legacy-ping-encode", LegacyPingEncoder.INSTANCE);
        ch.pipeline().addLast("frame-encoder", MinecraftVarintLengthEncoder.INSTANCE);
        ch.pipeline().addLast("minecraft-decoder", new MinecraftDecoder(ProtocolConstants.Direction.TO_SERVER));
        ch.pipeline().addLast("minecraft-encoder", new MinecraftEncoder(ProtocolConstants.Direction.TO_CLIENT));
    }
}