package main.com.gmail.SkellyBG;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class BookActivateEventHandler implements Listener {
    public BookActivateEventHandler() {

    }

    @EventHandler
    public void onXPTomeActivate(PlayerInteractEvent interactEvent) {
        ItemStack item = interactEvent.getItem();
        if (item != null && item.getType().equals(Material.WRITTEN_BOOK)
                && (interactEvent.getAction() == Action.RIGHT_CLICK_AIR
                || interactEvent.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            NamespacedKey key = new NamespacedKey(SkellyXPTomes.getPlugin(SkellyXPTomes.class), "xp_tome_data");
            if (item.getItemMeta().getPersistentDataContainer().has(key, PersistentDataType.INTEGER)) {
                Player player = interactEvent.getPlayer();
                interactEvent.setCancelled(true);
                int experienceValue = item.getItemMeta().getPersistentDataContainer().get(key, PersistentDataType.INTEGER);
                player.giveExp(experienceValue);
                item.setAmount(item.getAmount() - 1);
                player.sendMessage(ChatColor.GREEN + "You have absorbed the knowledge of this tome." +
                        ChatColor.GOLD + " (+" + experienceValue + " experience.)");
            }
        }
    }
}

