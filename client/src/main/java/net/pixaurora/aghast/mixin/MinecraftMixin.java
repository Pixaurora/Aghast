package net.pixaurora.aghast.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.Minecraft;
import net.pixaurora.aghast.AghastMod;

@Mixin(Minecraft.class)
public class MinecraftMixin {
	@Inject(method = "main", remap = false, at = @At("HEAD"))
	public static void exampleMod$onInit(CallbackInfo ci) {
		AghastMod.LOGGER.info("This line is printed by a ghast mod mixin!");
	}
}