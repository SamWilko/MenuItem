package me.wilko.menuopener.Settings;

import org.bukkit.Material;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.remain.CompMaterial;
import org.mineacademy.fo.settings.SimpleSettings;

import java.util.List;

public final class Settings extends SimpleSettings {

	public static String COMMAND_LABEL;
	public static String ERROR_MESSAGE;

	private static void init() {
		setPathPrefix(null);

		if (isSet("Command_Label")) {
			COMMAND_LABEL = getString("Command_Label");
		} else {
			Common.warning("Command_Label is not set. Setting to default value of 'togglemenu|tm'");
		}

		if (isSet("Error_Message")) {
			ERROR_MESSAGE = getString("Error_Message");
		} else {
			Common.warning("Error_Message is not set. Setting to default value.");
			ERROR_MESSAGE = "&cYou must free up space in your last hotbar slot!";
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

			if (isSet("Material")) {
				String materialName = getString("Material");

				try {

					Material material = Material.valueOf(materialName);
					MATERIAL = CompMaterial.fromMaterial(material);

				} catch (IllegalArgumentException ex) {

					Common.warning("Material '" + materialName + "' is invalid! Setting to default value of 'NETHER_STAR'");

					MATERIAL = CompMaterial.NETHER_STAR;
				}
			} else {
				MATERIAL = CompMaterial.NETHER_STAR;
			}

			if (isSet("Name"))
				NAME = getString("Name");
			else {
				Common.warning("Item.Name not set. Setting to default value of 'Menu Item'");
				NAME = "Menu Item";
			}

			if (isSet("Lore")) {
				LORE = getStringList("Lore");
			} else {
				Common.warning("Item.Lore not set. Setting to default value of null");
				LORE = null;
			}

			if (isSet("Glowing")) {
				GLOWING = getBoolean("Glowing");
			} else {
				Common.warning("Glowing not set. Setting to default value of true");
				GLOWING = true;
			}

			if (isSet("Right_Click_Command")) {
				RIGHT_CLICK_COMMAND = getString("Right_Click_Command");
			} else {
				Common.warning("Right_Click_Command not set. Setting to default value of null");
				RIGHT_CLICK_COMMAND = null;
			}

			if (isSet("Inventory_Click_Command")) {
				INVENTORY_CLICK_COMMAND = getString("Inventory_Click_Command");
			} else {
				Common.warning("Inventory_Click_Command not set. Setting to default value of null");
				INVENTORY_CLICK_COMMAND = null;
			}
		}

	}
}
