package me.wilko.menuopener.model;

import me.wilko.menuopener.settings.Settings;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.menu.model.ItemCreator;
import org.mineacademy.fo.remain.CompMetadata;

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

	public static void giveTo(Player player) {
		player.getInventory().setItem(8, get());
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
