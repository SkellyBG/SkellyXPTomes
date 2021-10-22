package main.com.gmail.SkellyBG;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class ExperienceTome extends ItemStack {
    private final int experienceValue;

    public ExperienceTome(int experienceValue) {
        super(Material.WRITTEN_BOOK, 1);
        this.experienceValue = experienceValue;

        ItemMeta XPTomeMeta = this.getItemMeta();
        NamespacedKey key = new NamespacedKey(SkellyXPTomes.getPlugin(SkellyXPTomes.class), "xp_tome_data");
        XPTomeMeta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, experienceValue);

        XPTomeMeta.setDisplayName(ChatColor.GREEN + "Experience Tome");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GOLD + "Right click to gain " + experienceValue + " experience.");
        XPTomeMeta.setLore(lore);
        this.setItemMeta(XPTomeMeta);
    }


    public static ExperienceTome getXPTome(int experienceValue) {
        return new ExperienceTome(experienceValue);
    }

    public int getExperienceValue() {
        return experienceValue;
    }
}
