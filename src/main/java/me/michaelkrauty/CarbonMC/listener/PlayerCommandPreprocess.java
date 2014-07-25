package me.michaelkrauty.CarbonMC.listener;

import me.michaelkrauty.CarbonMC.Main;
import org.bukkit.ChatColor;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

/**
 * Created on 7/25/2014.
 *
 * @author michaelkrauty
 */
public class PlayerCommandPreprocess {

	public PlayerCommandPreprocess(Main main, PlayerCommandPreprocessEvent event) {
		if (event.getPlayer() != null) {
			if (main.users.get(event.getPlayer()).inCombat()) {
				event.setCancelled(true);
				event.getPlayer().sendMessage(ChatColor.RED + "You can't use commands while in combat!");
			}
		}
	}
}
