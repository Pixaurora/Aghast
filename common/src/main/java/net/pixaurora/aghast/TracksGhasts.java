package net.pixaurora.aghast;

import java.util.List;

import net.minecraft.entity.living.mob.GhastEntity;

public interface TracksGhasts {
	public default List<GhastEntity> aghast$ghasts() {
		throw new RuntimeException("No definition of aghast$ghasts could be found!");
	}
}
