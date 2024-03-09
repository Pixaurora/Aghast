package net.pixaurora.aghast.mixin;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.Entity;
import net.minecraft.entity.living.mob.GhastEntity;
import net.minecraft.world.World;
import net.ornithemc.osl.networking.api.server.ServerPlayNetworking;
import net.pixaurora.aghast.AghastConstants;
import net.pixaurora.aghast.network.AghastCooldownInfo;
import net.pixaurora.aghast.network.AghastCooldownPayload;

@Mixin(World.class)
public class WorldMixin {
	@Shadow
	public List<Entity> entities;

	@Inject( method = "tick", at = @At("HEAD") )
	public void sendGhastDataToClients(CallbackInfo callbackInfo) {
		AghastCooldownPayload payload = new AghastCooldownPayload();

		for (Entity entity : this.entities) {
			if (entity instanceof GhastEntity) {
				GhastEntity ghast = (GhastEntity) entity;

				payload.add(new AghastCooldownInfo(ghast.networkId, ghast.shootingCooldown, ghast.attackCooldown));
			}
		}

		ServerPlayNetworking.send(AghastConstants.PACKET_CHANNEL, payload);
	}
}
