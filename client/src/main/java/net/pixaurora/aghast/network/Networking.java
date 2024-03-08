package net.pixaurora.aghast.network;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.living.mob.GhastEntity;
import net.ornithemc.osl.entrypoints.api.client.ClientModInitializer;
import net.ornithemc.osl.networking.api.client.ClientPlayNetworking;
import net.pixaurora.aghast.AghastConstants;

public class Networking implements ClientModInitializer {
	private void updateGhast(GhastEntity ghast, AghastCooldownInfo info) {
		ghast.shootingCooldown = info.shootingCooldown();
		ghast.attackCooldown = info.attackCooldown();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initClient() {
		ClientPlayNetworking.registerListener(AghastConstants.PACKET_CHANNEL, AghastCooldownPayload::new, (client, handler, payload) -> {
			for (Entity entity : (List<Entity>) client.world.globalEntities) {
				for (AghastCooldownInfo info : payload.cooldowns()) {
					if (info.ghastNetworkingID() == entity.networkId && entity instanceof GhastEntity) {
						this.updateGhast((GhastEntity) entity, info);
					}
				}
			}

			return true;
		});
	}

}
