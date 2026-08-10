package me.telltellito.nVnE.handler;

import me.telltellito.nVnE.NVnE;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class SpawnerHandler implements Listener {

    public SpawnerHandler(NVnE plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onSpawnerPlace(BlockPlaceEvent event) {
        Block block = event.getBlock();
        ItemStack item = event.getItemInHand();

        if (block.getType() != Material.STRUCTURE_VOID) {
            return;
        }

        EntityType entityType = EntityType.COW; // Default

        ItemMeta meta = item.getItemMeta();

        if (meta != null && meta.hasLore()) {

            List<String> lore = meta.getLore();

            if (lore != null && !lore.isEmpty()) {

                String entityName = ChatColor.stripColor(lore.get(0));

                try {
                    entityType = EntityType.valueOf(entityName.toUpperCase());
                } catch (IllegalArgumentException ignored) {
                    // Invalid entity type, use COW
                }
            }
        }

        Bukkit.getLogger().info("ready to make sv into spawner");

        block.setType(Material.SPAWNER);

        Bukkit.getLogger().info("made sv into spawner");

        CreatureSpawner spawner = (CreatureSpawner) block.getState();
        spawner.setSpawnedType(entityType);
        spawner.update();
    }

    @EventHandler
    public void onSpawnerBreak(BlockDamageEvent event) {
        Block block = event.getBlock();

        if (block.getType() != Material.SPAWNER) {
            return;
        }

        CreatureSpawner spawner = (CreatureSpawner) block.getState();

        // Get what the spawner spawns
        EntityType entityType = spawner.getSpawnedType();

        // Remove the spawner
        block.setType(Material.AIR);

        // Create the Structure Void item
        ItemStack item = new ItemStack(Material.STRUCTURE_VOID, 1);

        ItemMeta meta = item.getItemMeta();

        if (meta != null) {

            // Always name the item "Spawner"
            meta.setDisplayName(ChatColor.AQUA + "Spawner");

            // Store the entity type in the lore
            meta.setLore(List.of(
                    ChatColor.GREEN + entityType.name()
            ));

            item.setItemMeta(meta);
        }

        // Give the player the spawner
        event.getPlayer().getInventory().addItem(item);
    }
}
