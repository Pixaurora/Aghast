package net.pixaurora.aghast.mixin.common;

import java.util.ArrayList;
import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.Entity;
import net.minecraft.entity.living.mob.GhastEntity;
import net.minecraft.world.World;
import net.pixaurora.aghast.TracksGhasts;

@Mixin(World.class)
public class WorldMixin implements TracksGhasts {
	private final List<GhastEntity> ghasts = new ArrayList<>();

	@Override
	public List<GhastEntity> aghast$ghasts() {
		return this.ghasts;
	}

	@Inject( method = "onEntityAdded", at = @At("HEAD"))
	public void entityAdded(Entity entity, CallbackInfo callbackInfo) {
		if (entity instanceof GhastEntity) {
			ghasts.add((GhastEntity) entity);
		}
	}

	@Inject( method = "onEntityRemoved", at = @At("HEAD"))
	public void entityRemoved(Entity entity, CallbackInfo callbackInfo) {
		if (entity instanceof GhastEntity) {
			ghasts.remove((GhastEntity) entity);
		}
	}
}
