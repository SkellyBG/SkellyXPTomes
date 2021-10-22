package main.com.gmail.SkellyBG;

import main.com.gmail.SkellyBG.command.GiveXPTomeCommand;
import main.com.gmail.SkellyBG.command.TomeShopCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class SkellyXPTomes extends JavaPlugin {
    @Override
    public void onEnable() {
        loadCommand();
        loadListener();
    }

    private void loadCommand() {
        this.getCommand("givexptome").setExecutor(new GiveXPTomeCommand());
        this.getCommand("tomeshop").setExecutor(new TomeShopCommand());
    }

    private void loadListener() {
        this.getServer().getPluginManager().registerEvents(new BookActivateEventHandler(), this);
        this.getServer().getPluginManager().registerEvents(ExperienceTomeGUI.getExperienceTomeGUI(), this);
    }

    @Override
    public void onDisable() {

    }
}
