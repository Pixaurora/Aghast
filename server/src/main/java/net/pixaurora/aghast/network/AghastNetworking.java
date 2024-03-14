package net.pixaurora.aghast.network;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.entity.living.player.ServerPlayerEntity;
import net.ornithemc.osl.entrypoints.api.ModInitializer;
import net.ornithemc.osl.networking.api.server.ServerConnectionEvents;
import net.ornithemc.osl.networking.api.server.ServerPlayNetworking;
import net.pixaurora.aghast.AghastConstants;

public class AghastNetworking implements ModInitializer {
	/**
	 * A list of players who use the mod currently.
	 *
	 * Used to keep track of whether ghast info payloads should be generated.
	 */
	private static final List<ServerPlayerEntity> SUPPORTED_PLAYERS = new ArrayList<>();

	private static void onPlayerReady(MinecraftServer server, ServerPlayerEntity player) {
		if (ServerPlayNetworking.canSend(player, AghastConstants.PACKET_CHANNEL)) {
			SUPPORTED_PLAYERS.add(player);
		}
	}

	private static void onPlayerExit(MinecraftServer server, ServerPlayerEntity player) {
		SUPPORTED_PLAYERS.remove(player);
	}

	@Override
	public void init() {
		ServerConnectionEvents.PLAY_READY.register(AghastNetworking::onPlayerReady);;
		ServerConnectionEvents.DISCONNECT.register(AghastNetworking::onPlayerExit);
	}

	public static boolean shouldCreatePackets() {
		return SUPPORTED_PLAYERS.size() > 0;
	}
}
