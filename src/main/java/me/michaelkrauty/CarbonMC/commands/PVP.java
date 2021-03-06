package me.michaelkrauty.CarbonMC.commands;

import me.michaelkrauty.CarbonMC.Main;
import me.michaelkrauty.CarbonMC.objects.User;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created on 7/20/2014.
 *
 * @author michaelkrauty
 */
public class PVP implements CommandExecutor {

	private static Main main;

	public PVP(Main main) {
		this.main = main;
	}

	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String commandLabel, String[] args) {
		if (!(sender instanceof Player))
			return true;
		Player player = (Player) sender;
		User user = main.users.get(player);
		if (args.length == 0) {
			if (!user.pvpEnabled()) {
				user.setPVP(true);
				player.sendMessage(ChatColor.GRAY + "PVP Enabled!");
				return true;
			}
			sender.sendMessage(ChatColor.GRAY + "You can't disable PVP after you have enabled it!");
			return true;
		}
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("time")) {
				if (user.getCombatTime() == 0)
					player.sendMessage(ChatColor.LIGHT_PURPLE + "You're not in combat!");
				else
					player.sendMessage(ChatColor.RED + "You are in combat for another " + ChatColor.DARK_RED + user.getCombatTime() / 20 + ChatColor.RED + " seconds.");
			}
		}
		return true;
	}
}
