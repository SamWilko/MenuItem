package me.wilko.menuopener.command;

import me.wilko.menuopener.Settings.Settings;
import me.wilko.menuopener.model.MenuItem;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.command.SimpleCommand;
import org.mineacademy.fo.remain.CompMetadata;
import org.mineacademy.fo.remain.CompSound;

public class ToggleCommand extends SimpleCommand {

	public ToggleCommand(String label) {
		super(label);
	}

	@Override
	protected void onCommand() {

		ItemStack itemAtSlot = getPlayer().getInventory().getItem(8);

		if (itemAtSlot == null) {

			MenuItem.giveTo(getPlayer());
			CompSound.ITEM_PICKUP.play(getPlayer());

		} else {

			// check if the item is a menu item
			if (CompMetadata.hasMetadata(itemAtSlot, "ismenuitem")) {

				// Takes it away
				getPlayer().getInventory().setItem(8, null);
				CompSound.ITEM_PICKUP.play(getPlayer(), 1f, 0.5f);

			} else {

				Common.tellNoPrefix(getPlayer(), Settings.ERROR_MESSAGE);
				CompSound.VILLAGER_NO.play(getPlayer());

			}
		}
	}
}
