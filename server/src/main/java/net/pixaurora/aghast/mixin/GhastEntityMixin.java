package net.pixaurora.aghast.mixin;

import java.util.Optional;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.entity.Entity;
import net.minecraft.entity.living.mob.GhastEntity;
import net.minecraft.world.World;
import net.pixaurora.aghast.AghastEvent;

@Mixin(GhastEntity.class)
public class GhastEntityMixin {
	@Redirect(
		method = "tickDespawn",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/World;playSound(Lnet/minecraft/entity/Entity;Ljava/lang/String;FF)V"
		)
	)
	public void aghast$sendFireballEvent(World world, Entity shootingGhast, String soundName, float volume, float pitch) {
		Optional<AghastEvent> event = AghastEvent.bySound(soundName);

		if (event.isPresent()) {
			world.doEvent(
				event.get().id(),
				(int) shootingGhast.x,
				(int) shootingGhast.y,
				(int) shootingGhast.z,
				0
			);
		} else {
			world.playSound(shootingGhast, soundName, volume, pitch);
		}
	}
}
