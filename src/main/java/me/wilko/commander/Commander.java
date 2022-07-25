package me.wilko.commander;

import me.wilko.commander.command.ToggleCommand;
import me.wilko.commander.settings.Settings;
import org.mineacademy.fo.Messenger;
import org.mineacademy.fo.plugin.SimplePlugin;

public final class Commander extends SimplePlugin {

	@Override
	protected void onPluginStart() {
		Messenger.ENABLED = false;

		registerCommand(new ToggleCommand(Settings.COMMAND_LABEL));
	}

	@Override
	protected void onReloadablesStart() {

	}

	public static Commander getInstance() {
		return (Commander) SimplePlugin.getInstance();
	}
}
