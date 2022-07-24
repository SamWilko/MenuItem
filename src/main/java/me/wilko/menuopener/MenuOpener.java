package me.wilko.menuopener;

import me.wilko.menuopener.command.ToggleCommand;
import me.wilko.menuopener.settings.Settings;
import org.mineacademy.fo.plugin.SimplePlugin;

public final class MenuOpener extends SimplePlugin {

	@Override
	protected void onPluginStart() {
		registerCommand(new ToggleCommand(Settings.COMMAND_LABEL));
	}

	@Override
	protected void onReloadablesStart() {

	}

	public static MenuOpener getInstance() {
		return (MenuOpener) SimplePlugin.getInstance();
	}
}
