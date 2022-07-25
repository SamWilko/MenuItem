package me.wilko.commander.settings;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.mineacademy.fo.settings.YamlConfig;

import java.util.HashMap;
import java.util.Map;

public class PlayerData extends YamlConfig {

	private static Map<Player, PlayerData> PLAYER_DATA = new HashMap<>();

	@Getter
	private boolean toggled;

	private PlayerData(Player player) {
		loadConfiguration(NO_DEFAULT, "players/" + player.getUniqueId() + ".yml");
	}

	@Override
	protected void onLoad() {
		this.toggled = getBoolean("toggled", true);
	}

	public void setToggled(boolean toggled) {
		this.toggled = toggled;

		this.save();
	}

	@Override
	protected void onSave() {
		set("toggled", toggled);
	}

	public static PlayerData get(Player player) {

		if (PLAYER_DATA.containsKey(player))
			return PLAYER_DATA.get(player);

		PlayerData data = new PlayerData(player);
		PLAYER_DATA.put(player, data);

		return data;
	}

	public static void remove(Player player) {
		PLAYER_DATA.remove(player);
	}
}
