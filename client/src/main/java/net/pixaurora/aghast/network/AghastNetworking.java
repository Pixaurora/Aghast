package net.pixaurora.aghast.network;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.network.handler.ClientNetworkHandler;
import net.minecraft.entity.living.mob.GhastEntity;
import net.ornithemc.osl.entrypoints.api.ModInitializer;
import net.ornithemc.osl.networking.api.client.ClientPlayNetworking;
import net.pixaurora.aghast.AghastConstants;

public class AghastNetworking implements ModInitializer, ClientPlayNetworking.PayloadListener<AghastCooldownPayload> {
	private void updateGhast(GhastEntity ghast, AghastCooldownInfo info) {
		ghast.shootingCooldown = info.shootingCooldown();
		ghast.attackCooldown = info.attackCooldown();
	}

	@Override
	public boolean handle(Minecraft client, ClientNetworkHandler handler, AghastCooldownPayload payload) throws IOException {
		if (client.world == null) {
			return false;
		}

		for (GhastEntity ghast : client.world.aghast$ghasts()) {
			for (AghastCooldownInfo info : payload.cooldowns()) {
				if (info.ghastNetworkingID() == ghast.networkId) {
					this.updateGhast(ghast, info);
				}
			}
		}

		return true;
	}

	@Override
	public void init() {
		ClientPlayNetworking.registerListener(AghastConstants.PACKET_CHANNEL, AghastCooldownPayload::new, this);
	}

}
