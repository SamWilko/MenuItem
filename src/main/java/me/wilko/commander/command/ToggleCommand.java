package me.wilko.commander.command;

import me.wilko.commander.model.MenuItem;
import me.wilko.commander.settings.PlayerData;
import me.wilko.commander.settings.Settings;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.command.SimpleCommand;

public class ToggleCommand extends SimpleCommand {

	public ToggleCommand(String label) {
		super(label);

		setAutoHandleHelp(false);
		setPermission("menuitem.toggle");
		setPermissionMessage(Settings.Messages.NO_PERMISSION);
	}

	@Override
	protected void onCommand() {

		if (args.length > 0) {
			Common.tellNoPrefix(getPlayer(), Settings.Messages.INVALID_SYNTAX);
			return;
		}

		PlayerData data = PlayerData.get(getPlayer());

		// Checks if the player has the menu item
		if (MenuItem.checkHas(getPlayer())) {

			// Removes it and toggles it off in their file
			Common.tellNoPrefix(getPlayer(), MenuItem.remove(getPlayer()));
			data.setToggled(false);

		} else {

			// Checks if the slot is empty
			if (MenuItem.checkFree(getPlayer())) {

				// Gives them the item and toggles it on in their file
				Common.tellNoPrefix(getPlayer(), MenuItem.give(getPlayer()));
				data.setToggled(true);

			} else {

				// Sends them an error and don't alter the toggle in their file
				Common.tellNoPrefix(getPlayer(), Settings.Messages.ERROR);
			}
		}
	}
}
