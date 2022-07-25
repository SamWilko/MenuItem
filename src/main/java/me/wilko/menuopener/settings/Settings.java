package me.wilko.menuopener.settings;

import org.bukkit.Material;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.remain.CompMaterial;
import org.mineacademy.fo.settings.SimpleSettings;

import java.util.List;

public final class Settings extends SimpleSettings {

	public static String COMMAND_LABEL;

	private static void init() {
		setPathPrefix(null);

		COMMAND_LABEL = getString("Command_Label");
	}

	public static class Messages {

		public static String NO_PERMISSION;
		public static String INVALID_SYNTAX;
		public static String ERROR;
		public static String TOGGLE_ON;
		public static String TOGGLE_OFF;

		private static void init() {

			setPathPrefix("Messages");

			NO_PERMISSION = getString("No_Permission");
			INVALID_SYNTAX = getString("Invalid_Syntax");
			ERROR = getString("Error");
			TOGGLE_ON = getString("Toggle_On");
			TOGGLE_OFF = getString("Toggle_Off");
		}
	}

	public static class Item {

		public static CompMaterial MATERIAL;
		public static String NAME;
		public static List<String> LORE;
		public static Boolean GLOWING;
		public static String RIGHT_CLICK_COMMAND;
		public static String INVENTORY_CLICK_COMMAND;

		private static void init() {
			setPathPrefix("Item");

			String materialName = getString("Material");

			try {
				Material material = Material.valueOf(materialName);
				MATERIAL = CompMaterial.fromMaterial(material);

			} catch (IllegalArgumentException ex) {

				Common.warning("Material '" + materialName + "' is invalid! Setting to default value of 'NETHER_STAR'");

				MATERIAL = CompMaterial.NETHER_STAR;
			}

			NAME = getString("Name");

			LORE = getStringList("Lore");

			GLOWING = getBoolean("Glowing");

			RIGHT_CLICK_COMMAND = getString("Right_Click_Command");

			INVENTORY_CLICK_COMMAND = getString("Inventory_Click_Command");
		}
	}
}
