package me.michaelkrauty.CarbonMC.commands;

import me.michaelkrauty.CarbonMC.Main;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;

/**
 * Created on 7/25/2014.
 *
 * @author michaelkrauty
 */
public class Test implements CommandExecutor {

	private static Main main;

	public Test(Main main) {
		this.main = main;
	}

	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String commandLabel, String[] args) {
		if (!(sender instanceof Player))
			return true;
		Player player = (Player) sender;
		// new CustomEntitySkeleton(player.getLocation());

		Pig pig = (Pig) player.getWorld().spawnEntity(player.getLocation(), EntityType.PIG);
		pig.setCustomName("Bore");
		pig.setCustomNameVisible(true);
		pig.setTarget(player);

		return true;
	}
}