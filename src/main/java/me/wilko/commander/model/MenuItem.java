package me.wilko.commander.model;

import me.wilko.commander.settings.Settings;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.menu.model.ItemCreator;
import org.mineacademy.fo.remain.CompMetadata;
import org.mineacademy.fo.remain.CompSound;

public class MenuItem {

	private static ItemStack get() {
		ItemStack item = ItemCreator.of(
						Settings.Item.MATERIAL,
						Settings.Item.NAME,
						Settings.Item.LORE
				).glow(Settings.Item.GLOWING)
				.makeMenuTool();

		item = CompMetadata.setMetadata(item, "ismenuitem", "yes");

		return item;
	}

	public static String give(Player player) {
		return give(player, true);
	}

	public static String give(Player player, boolean playSound) {

		// Give the item
		player.getInventory().setItem(8, get());

		if (playSound)
			CompSound.ITEM_PICKUP.play(player);

		return Settings.Messages.TOGGLE_ON;
	}


	public static String remove(Player player) {

		// Remove the item
		player.getInventory().setItem(8, null);
		CompSound.ITEM_PICKUP.play(player, 1f, 0.5f);

		return Settings.Messages.TOGGLE_OFF;
	}

	public static boolean checkHas(Player player) {

		ItemStack item = player.getInventory().getItem(8);

		if (item == null)
			return false;

		return CompMetadata.hasMetadata(item, "ismenuitem");
	}

	public static boolean checkFree(Player player) {
		return player.getInventory().getItem(8) == null;
	}

	public static void runFor(Player player, ExecuteType type) {

		Common.dispatchCommandAsPlayer(player,
				type == ExecuteType.RIGHT_CLICK ? Settings.Item.RIGHT_CLICK_COMMAND : Settings.Item.INVENTORY_CLICK_COMMAND);
	}

	public enum ExecuteType {
		RIGHT_CLICK,
		INVENTORY_CLICK
	}

}
