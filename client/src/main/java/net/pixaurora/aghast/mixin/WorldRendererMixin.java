package net.pixaurora.aghast.mixin;

import java.util.Optional;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.render.world.WorldRenderer;
import net.minecraft.entity.living.player.PlayerEntity;
import net.minecraft.world.World;
import net.pixaurora.aghast.AghastEvent;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {
	@Shadow
	private World world;

	@Inject( method = "doEvent", at = @At("HEAD") )
	public void aghast$handleGhastShot(PlayerEntity source, int type, int x, int y, int z, int data, CallbackInfo ci) {
		Optional<AghastEvent> event = AghastEvent.byEvent(type);

		if (event.isPresent()) {
			this.world.playSound(x, y, z, event.get().soundPath(), 10.0F, 1.0F);
		}
	}
}
