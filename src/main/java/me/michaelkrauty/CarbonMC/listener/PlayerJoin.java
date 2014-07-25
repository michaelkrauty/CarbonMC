package me.michaelkrauty.CarbonMC.listener;

import me.michaelkrauty.CarbonMC.Main;
import me.michaelkrauty.CarbonMC.objects.User;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created on 7/25/2014.
 *
 * @author michaelkrauty
 */
public class PlayerJoin {

	public PlayerJoin(Main main, PlayerJoinEvent event) {
		final Main finalMain = main;
		User user = new User(main, event.getPlayer());
		if (!user.pvpEnabled())
			event.getPlayer().sendMessage(ChatColor.GRAY + "You have PVP disabled! Enable it by using " + ChatColor.GREEN + "/pvp" + ChatColor.GRAY + ".");

		final OfflinePlayer offlinePlayer = main.getServer().getOfflinePlayer(event.getPlayer().getUniqueId());
		double ecoBalance = main.economy.getBalance(offlinePlayer);
		double sqlBalance = main.sql.getBalance(event.getPlayer().getUniqueId());
		if (ecoBalance != sqlBalance) {
			final double difference = sqlBalance - ecoBalance;
			main.getServer().getScheduler().scheduleAsyncDelayedTask(main, new Runnable() {
				public void run() {
					finalMain.economy.depositPlayer(offlinePlayer, difference);
				}
			});
		}
	}
}
