package net.pixaurora.aghast;

import java.util.HashMap;
import java.util.Map;

public class AghastMod {
	public static final Map<String, Integer> ghastSoundToEvent = new HashMap<>();

	static {
		ghastSoundToEvent.put("mob.ghast.charge", 1007);
		ghastSoundToEvent.put("mob.ghast.fireball", 1008);
	}
}
