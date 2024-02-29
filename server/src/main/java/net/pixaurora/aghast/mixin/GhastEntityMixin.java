package net.pixaurora.aghast.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.entity.Entity;
import net.minecraft.entity.living.mob.GhastEntity;
import net.minecraft.world.World;
import net.pixaurora.aghast.AghastMod;

@Mixin(GhastEntity.class)
public class GhastEntityMixin {
	@Redirect(
		method = "tickDespawn",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/World;playSound(Lnet/minecraft/entity/Entity;Ljava/lang/String;FF)V"
		)
	)
	public void aghast$warnPlayerOfFireball(World world, Entity shootingGhast, String soundName, float volume, float pitch) {
		world.doEvent(
			AghastMod.ghastSoundToEvent.get(soundName).intValue(),
			(int) shootingGhast.x,
			(int) shootingGhast.y,
			(int) shootingGhast.z,
			0
		);
	}
}
