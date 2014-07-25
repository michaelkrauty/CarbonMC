package me.michaelkrauty.CarbonMC;

import me.michaelkrauty.CarbonMC.listener.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created on 7/20/2014.
 *
 * @author michaelkrauty
 */
public class Listener implements org.bukkit.event.Listener {

	private static Main main;

	public Listener(Main main) {
		this.main = main;
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		new EntityDamageByEntity(main, event);
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		new PlayerJoin(main, event);
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		new PlayerQuit(main, event);
	}

	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
		new EntityDeath(main, event);
	}

	@EventHandler
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
		new PlayerCommandPreprocess(main, event);
	}

	@EventHandler
	public void onCreatureSpawn(CreatureSpawnEvent event) {
		new CreatureSpawn(main, event);
	}
}
