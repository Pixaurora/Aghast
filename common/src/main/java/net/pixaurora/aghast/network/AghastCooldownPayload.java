package net.pixaurora.aghast.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.ornithemc.osl.networking.api.CustomPayload;

public class AghastCooldownPayload implements CustomPayload {
	private List<AghastCooldownInfo> cooldowns;

	public AghastCooldownPayload() {
		this.cooldowns = new ArrayList<>();
	}

	@Override
	public void read(DataInputStream input) throws IOException {
		int numberOfGhasts = input.readInt();

		for (int i = 0; i < numberOfGhasts; i++) {
			int networkingID = input.readInt();
			int shootingCooldown = input.readInt();
			int attackCooldown = input.readInt();

			this.cooldowns.add(new AghastCooldownInfo(networkingID, shootingCooldown, attackCooldown));
		}
	}

	@Override
	public void write(DataOutputStream output) throws IOException {
		output.writeInt(this.cooldowns.size());

		for (AghastCooldownInfo cooldown : this.cooldowns) {
			output.writeInt(cooldown.ghastNetworkingID());
			output.writeInt(cooldown.shootingCooldown());
			output.writeInt(cooldown.attackCooldown());
		}
	}

	public List<AghastCooldownInfo> cooldowns() {
		return this.cooldowns;
	}

	public void add(AghastCooldownInfo cooldown) {
		this.cooldowns.add(cooldown);
	}
}
