package me.michaelkrauty.CarbonMC.listener;

import me.michaelkrauty.CarbonMC.Main;
import me.michaelkrauty.CarbonMC.objects.User;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.UUID;

/**
 * Created on 7/25/2014.
 *
 * @author michaelkrauty
 */
public class PlayerQuit {

	public PlayerQuit(Main main, PlayerQuitEvent event) {
		final Main finalMain = main;
		Player player = event.getPlayer();
		User user = main.users.get(player);
		if (user.inCombat()) {
			Pig pig = (Pig) player.getWorld().spawnEntity(player.getLocation(), EntityType.PIG);
			pig.setCustomName("COMBAT LOGGER: " + player.getName());
			pig.setCustomNameVisible(true);
			pig.setRemoveWhenFarAway(false);
			pig.setMetadata("inplaceofplayer", new FixedMetadataValue(main, player.getUniqueId().toString()));
			main.uuidPigHashMap.put(player.getUniqueId(), pig);
			main.combatLoggers.put(pig, player.getInventory());
			main.combatLoggersArmor.put(pig, player.getInventory().getArmorContents());
			main.getServer().broadcastMessage(ChatColor.RED + "COMBAT LOGGER: " + player.getName());
			final Pig finalPig = pig;
			final Player finalPlayer = player;
			main.getServer().getScheduler().scheduleSyncDelayedTask(main, new Runnable() {
				public void run() {
					if (finalPig != null) {
						Pig pig = finalMain.uuidPigHashMap.get(finalPlayer.getUniqueId());
						finalMain.combatLoggers.remove(pig);
						finalMain.combatLoggersArmor.remove(pig);
						pig.remove();
					}
				}
			}, 200);
		}
		user.save();
		main.users.remove(player);
		final UUID uuid = event.getPlayer().getUniqueId();
		main.getServer().getScheduler().scheduleAsyncDelayedTask(main, new Runnable() {
			public void run() {
				finalMain.sql.setBalance(uuid, finalMain.economy.getBalance(finalMain.getServer().getOfflinePlayer(uuid)));
			}
		});
	}
}
