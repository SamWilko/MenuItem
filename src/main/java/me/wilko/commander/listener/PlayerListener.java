package me.wilko.commander.listener;

import me.wilko.commander.model.CommanderItem;
import me.wilko.commander.settings.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.annotation.AutoRegister;
import org.mineacademy.fo.remain.CompMetadata;

import java.util.List;

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
				CommanderItem.runFor(event.getPlayer(), CommanderItem.ExecuteType.RIGHT_CLICK);
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
			CommanderItem.runFor(player, CommanderItem.ExecuteType.INVENTORY_CLICK);
		}
	}

	@EventHandler
	public void onThrow(PlayerDropItemEvent event) {

		ItemStack dropped = event.getItemDrop().getItemStack();

		// Prevents the player dropping the menu item
		if (CompMetadata.hasMetadata(dropped, "ismenuitem"))
			event.setCancelled(true);

	}

	@EventHandler
	public void onRespawn(PlayerRespawnEvent event) {
		Common.runLater(1, () -> {

			Player player = event.getPlayer();

			if (PlayerData.get(player).isToggled() && !CommanderItem.checkHas(player))
				CommanderItem.give(player, false);
		});
	}

	@EventHandler
	public void onDeath(PlayerDeathEvent event) {

		if (CommanderItem.checkHas(event.getEntity())) {

			List<ItemStack> drops = event.getDrops();
			int removeIndex = 0;

			for (int i = 0; i < drops.size(); i++) {

				if (CompMetadata.hasMetadata(drops.get(i), "ismenuitem"))
					removeIndex = i;
			}

			event.getDrops().remove(removeIndex);
		}
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {

		Player player = event.getPlayer();

		PlayerData data = PlayerData.get(player.getPlayer());

		// If the player has the item toggled but they don't have it (new players)
		if (data.isToggled() && !CommanderItem.checkHas(player)) {

			// Make sure the slot is free
			if (CommanderItem.checkFree(player))
				CommanderItem.give(player, false);
		}
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		PlayerData.remove(event.getPlayer());
	}
}
