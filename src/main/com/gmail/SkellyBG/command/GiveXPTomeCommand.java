package main.com.gmail.SkellyBG.command;

import main.com.gmail.SkellyBG.ExperienceTome;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveXPTomeCommand implements CommandExecutor {
    public GiveXPTomeCommand() {

    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 0) {
            commandSender.sendMessage("Usage: /givebook (experience)");
            return false;
        }

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            try {
                int experienceValue = Integer.parseInt(strings[0]);
                if (experienceValue < 0) {
                    player.sendMessage("First argument must be greater or equal to 0!");
                    return false;
                }
                ExperienceTome experienceTome = ExperienceTome.getXPTome(experienceValue);
                player.getInventory().addItem(experienceTome);
            } catch (NumberFormatException e) {
                player.sendMessage("First argument must be a number!");
                return false;
            }
        }
        return true;
    }
}
