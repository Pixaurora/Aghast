package net.pixaurora.aghast;

import java.util.Optional;

public enum AghastEvent {
	CHARGING("mob.ghast.charge", 1007),
	FIREBALL_SHOT("mob.ghast.fireball", 1008);

	private final String soundPath;
	private final int id;

	public static Optional<AghastEvent> bySound(String soundPath) {
		for (AghastEvent event : AghastEvent.values()) {
			if (event.soundPath == soundPath) {
				return Optional.of(event);
			}
		}

		return Optional.empty();
	}

	public static Optional<AghastEvent> byEvent(int id) {
		for (AghastEvent event : AghastEvent.values()) {
			if (event.id == id) {
				return Optional.of(event);
			}
		}

		return Optional.empty();
	}

	private AghastEvent(String associatedNoise, int id) {
		this.soundPath = associatedNoise;
		this.id = id;
	}

	public String soundPath() {
		return this.soundPath;
	}

	public int id() {
		return this.id;
	}
}
