package me.telltellito.nVnE.handler;

import me.telltellito.nVnE.NVnE;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ShopHandler implements Listener,  CommandExecutor {
    private String invName = "Shop";



    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().equals(invName)) {
            return;
        }

        Player player = (Player) event.getWhoClicked();
        int slot = event.getSlot();

        if (slot == 11) {
            ItemStack item = new ItemStack(Material.STRUCTURE_VOID);
            ItemMeta meta = item.getItemMeta();

            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bSpawner"));
            meta.setLore(Collections.singletonList(
                    ChatColor.translateAlternateColorCodes('&', "&aCOW")
            ));

            item.setItemMeta(meta);

            player.getInventory().addItem(item);
        }
        if (slot == 12) {
            ItemStack item = new ItemStack(Material.STRUCTURE_VOID);
            ItemMeta meta = item.getItemMeta();

            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bSpawner"));
            meta.setLore(Collections.singletonList(
                    ChatColor.translateAlternateColorCodes('&', "&aZOMBIE")
            ));

            item.setItemMeta(meta);

            player.getInventory().addItem(item);
        }
        if (slot == 13) {
            ItemStack item = new ItemStack(Material.STRUCTURE_VOID);
            ItemMeta meta = item.getItemMeta();

            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bSpawner"));
            meta.setLore(Collections.singletonList(
                    ChatColor.translateAlternateColorCodes('&', "&aCREEPER")
            ));

            item.setItemMeta(meta);

            player.getInventory().addItem(item);
        }
        if (slot == 14) {
            ItemStack item = new ItemStack(Material.STRUCTURE_VOID);
            ItemMeta meta = item.getItemMeta();

            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bSpawner"));
            meta.setLore(Collections.singletonList(
                    ChatColor.translateAlternateColorCodes('&', "&aSKELETON")
            ));

            item.setItemMeta(meta);

            player.getInventory().addItem(item);
        }
        if (slot == 15) {
            ItemStack item = new ItemStack(Material.STRUCTURE_VOID);
            ItemMeta meta = item.getItemMeta();

            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bSpawner"));
            meta.setLore(Collections.singletonList(
                    ChatColor.translateAlternateColorCodes('&', "&aIRON_GOLEM")
            ));

            item.setItemMeta(meta);

            player.getInventory().addItem(item);
        }


        event.setCancelled(true);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String [] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("only players can send this command");
            return true;
        }

        Player player = (Player) sender;

        Inventory inv = Bukkit.createInventory(player, 9 * 3, invName);

        inv.setItem(11, getItem(new ItemStack(Material.STRUCTURE_VOID), "&bSpawner", "&aCOW"));
        inv.setItem(12, getItem(new ItemStack(Material.STRUCTURE_VOID), "&bSpawner", "&aZOMBIE"));
        inv.setItem(13, getItem(new ItemStack(Material.STRUCTURE_VOID), "&bSpawner", "&aCREEPER"));
        inv.setItem(14, getItem(new ItemStack(Material.STRUCTURE_VOID), "&bSpawner", "&aSKELETON"));
        inv.setItem(15, getItem(new ItemStack(Material.STRUCTURE_VOID), "&bSpawner", "&aIRON_GOLEM"));

        player.openInventory(inv);

        return true;
    }

    private ItemStack getItem(ItemStack item, String name, String ... lore) {
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));

        List<String> lores = new ArrayList<>();
        for (String s : lore) {
            lores.add(ChatColor.translateAlternateColorCodes('&', s));
        }

        meta.setLore(lores);

        item.setItemMeta(meta);
        return item;
    }
}
