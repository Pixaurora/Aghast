package net.pixaurora.aghast.network;

/*
 * Cooldown info about when the Ghast will next shoot, used for animating the attack!
 */
public class AghastCooldownInfo {
	private final int ghastNetworkingID;

	private final int shootingCooldown;
	private final int attackCooldown;

	public AghastCooldownInfo(int ghastNetworkingID, int shootingCooldown, int attackCooldown) {
		this.ghastNetworkingID = ghastNetworkingID;
		this.shootingCooldown = shootingCooldown;
		this.attackCooldown = attackCooldown;
	}

	public int ghastNetworkingID() {
		return this.ghastNetworkingID;
	}

	public int shootingCooldown() {
		return this.shootingCooldown;
	}

	public int attackCooldown() {
		return this.attackCooldown;
	}
}
