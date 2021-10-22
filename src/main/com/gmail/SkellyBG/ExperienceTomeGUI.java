package main.com.gmail.SkellyBG;

import main.com.gmail.SkellyBG.command.ExperiencePointCalculation;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ExperienceTomeGUI implements Listener {
    private Inventory inventory;
    private Map<Integer, Integer> slotExperienceValue;
    private static final ExperienceTomeGUI EXPERIENCE_TOME_GUI = new ExperienceTomeGUI();

    private ExperienceTomeGUI() {
        slotExperienceValue = new HashMap<>();
        slotExperienceValue.put(10, 10);
        slotExperienceValue.put(12, 100);
        slotExperienceValue.put(14, 1000);
        slotExperienceValue.put(16, 10000);

        ItemStack fillerItem = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        if (fillerItem.getType() != Material.AIR) {
            ItemMeta fillerItemMeta = fillerItem.getItemMeta();
            fillerItemMeta.setDisplayName("");
            fillerItem.setItemMeta(fillerItemMeta);
        }

        this.inventory = Bukkit.createInventory(null, 27, "Experience Tome Shop!");
        for (int i = 0; i < inventory.getSize(); i++) {
            if (slotExperienceValue.containsKey(i)) {
                inventory.setItem(i, new ExperienceTome(slotExperienceValue.get(i)));
            } else {
                inventory.setItem(i, fillerItem);
            }
        }
    }

    public static ExperienceTomeGUI getExperienceTomeGUI() {
        return EXPERIENCE_TOME_GUI;
    }

    public void displayShopGUI(Player player) {
        player.openInventory(this.inventory);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (Objects.equals(event.getClickedInventory(), EXPERIENCE_TOME_GUI.inventory)) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            if (slotExperienceValue.containsKey(event.getSlot())) {
                int experienceValue = slotExperienceValue.get(event.getSlot());
                if (ExperiencePointCalculation.getPlayerExp(player) < experienceValue) {
                    player.sendMessage(ChatColor.RED + "You do not have enough experience!");
                } else {
                    ExperiencePointCalculation.changePlayerExp(player, -1 * experienceValue);
                    player.getInventory().addItem(new ExperienceTome(experienceValue));
                    player.sendMessage(ChatColor.GREEN + "You have imbued this tome with your knowledge!");
                }
            }
        }
    }
}
