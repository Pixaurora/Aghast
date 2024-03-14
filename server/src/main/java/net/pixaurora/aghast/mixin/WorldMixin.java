package net.pixaurora.aghast.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.living.mob.GhastEntity;
import net.minecraft.world.World;
import net.ornithemc.osl.networking.api.server.ServerPlayNetworking;
import net.pixaurora.aghast.AghastConstants;
import net.pixaurora.aghast.network.AghastCooldownInfo;
import net.pixaurora.aghast.network.AghastCooldownPayload;

@Mixin(World.class)
public class WorldMixin {
	@Inject( method = "tick", at = @At("HEAD") )
	public void sendGhastDataToClients(CallbackInfo callbackInfo) {
		AghastCooldownPayload payload = new AghastCooldownPayload();

		World world = (World)(Object) this;

		for (GhastEntity ghast : world.aghast$ghasts()) {
			payload.add(new AghastCooldownInfo(ghast.networkId, ghast.shootingCooldown, ghast.attackCooldown));
		}

		ServerPlayNetworking.send(AghastConstants.PACKET_CHANNEL, payload);
	}
}
