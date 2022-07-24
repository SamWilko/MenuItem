package me.wilko.menuopener.listener;

import me.wilko.menuopener.model.MenuItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.annotation.AutoRegister;
import org.mineacademy.fo.remain.CompMetadata;

@AutoRegister
public final class PlayerListener implements Listener {

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {

		ItemStack holding = event.getItem();

		// Checks that they are holding an item
		if (holding == null)
			return;

		// Checks that they are holding the menu item
		if (CompMetadata.hasMetadata(holding, "ismenuitem")) {

			// Cancel the event interaction
			event.setCancelled(true);

			// Prevents the code running twice and checks that they are right clicking with the item
			if (event.getHand() == EquipmentSlot.HAND
					&& (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) {

				// Right click command execution
				MenuItem.runFor(event.getPlayer(), MenuItem.ExecuteType.RIGHT_CLICK);
			}
		}
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {

		if (!(event.getWhoClicked() instanceof Player))
			return;

		Player player = (Player) event.getWhoClicked();

		ItemStack clickedItem = event.getCurrentItem();

		// Checks they didn't click an empty slot
		if (clickedItem == null)
			return;

		// Checks that the item they clicked is a menu item
		if (CompMetadata.hasMetadata(clickedItem, "ismenuitem")) {

			// Cancel the event interaction so that they can't move it around in their inventory
			event.setCancelled(true);

			// Executes the command if they simply left click the item
			MenuItem.runFor(player, MenuItem.ExecuteType.INVENTORY_CLICK);
		}
	}

	@EventHandler
	public void onThrow(PlayerDropItemEvent event) {

		ItemStack dropped = event.getItemDrop().getItemStack();

		// Prevents the player dropping the menu item
		if (CompMetadata.hasMetadata(dropped, "ismenuitem"))
			event.setCancelled(true);
	}
}
