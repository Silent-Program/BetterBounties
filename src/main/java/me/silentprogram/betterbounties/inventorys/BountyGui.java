package me.silentprogram.betterbounties.inventorys;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.PaginatedPane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import me.silentprogram.betterbounties.BetterBounties;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;

import java.util.ArrayList;
import java.util.List;

public class BountyGui {
	BetterBounties plugin;
	
	public BountyGui(BetterBounties plugin) {
		this.plugin = plugin;
	}
	
	public ChestGui createGui(Player guiPlr) {
		ChestGui gui = new ChestGui(6, "Spawners");
		
		//Create a pages pane to add to the gui
		PaginatedPane pages = new PaginatedPane(0, 0, 9, 5);
		List<ItemStack> inventoryItemList = new ArrayList<>();
		//for (BountyPlayer i : dataConfig.getBounties()) {
		//	inventoryItemList.add(i.getBountyItem());
		//}
		//Add these items to the pages
		pages.populateWithItemStacks(inventoryItemList);
		//Set click functionality
		pages.setOnClick(event -> {
			event.setCancelled(true);
			if (event.getAction() != InventoryAction.PICKUP_ALL) return;
			Player plr = (Player) event.getWhoClicked();
			ItemStack itemStack = event.getCurrentItem();
			createGui(guiPlr).show(plr);
		});
		//Add pages to the gui
		gui.addPane(pages);
		
		//Set actions for when the player clicks on his/her own inventory
		gui.setOnBottomClick(event -> {
			event.setCancelled(true);
			if (event.getAction() != InventoryAction.PICKUP_ALL) return;
			if (event.getCurrentItem() == null || event.getCurrentItem().getType() != Material.SPAWNER) return;
			ItemStack itemStack = event.getCurrentItem();
			if (!itemStack.hasItemMeta() || (!itemStack.hasItemMeta())) return;
			PersistentDataContainer itemData = itemStack.getItemMeta().getPersistentDataContainer();
			//if (!itemData.has(plugin.ENTITY_TYPE_KEY, PersistentDataType.STRING)) return;
			Player plr = (Player) event.getWhoClicked();
			createGui(guiPlr).show(plr);
		});
		//Create a new pane for the background to fill it with black stained glass
		OutlinePane background = new OutlinePane(0, 5, 9, 1);
		background.addItem(new GuiItem(new ItemStack(Material.BLACK_STAINED_GLASS_PANE)));
		background.setRepeat(true);
		background.setPriority(Pane.Priority.LOWEST);
		background.setOnClick(event -> event.setCancelled(true));
		
		gui.addPane(background);
		
		//Create a new pane for the navigation items
		StaticPane navigation = new StaticPane(0, 5, 9, 1);
		//Go back a page item
		ItemStack backItem = new ItemStack(Material.RED_WOOL);
		ItemMeta backItemMeta = backItem.getItemMeta();
		backItemMeta.setDisplayName("Last Page");
		backItem.setItemMeta(backItemMeta);
		navigation.addItem(new GuiItem(backItem, event -> {
			event.setCancelled(true);
			if (pages.getPage() > 0) {
				pages.setPage(pages.getPage() - 1);
				
				gui.update();
			}
		}), 0, 0);
		
		gui.addPane(navigation);
		return gui;
	}
}
