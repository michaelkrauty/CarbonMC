package me.michaelkrauty.CarbonMC.listener;

import me.michaelkrauty.CarbonMC.Main;
import me.michaelkrauty.CarbonMC.objects.User;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created on 7/25/2014.
 *
 * @author michaelkrauty
 */
public class EntityDamageByEntity {

	public EntityDamageByEntity(Main main, EntityDamageByEntityEvent event) {
		if (!event.isCancelled()) {
			Player target;
			Player damager;
			if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
				damager = (Player) event.getDamager();
				target = (Player) event.getEntity();
			} else return;
			User targetUser = main.users.get(target);
			User damagerUser = main.users.get(damager);

			if (!targetUser.pvpEnabled()) {
				event.setCancelled(true);
				damager.sendMessage(ChatColor.GRAY + target.getName() + " has PVP disabled!");
				target.sendMessage(ChatColor.GRAY + damager.getName() + " tried to hit you, but you have PVP disabled. Enable it by using " + ChatColor.GREEN + "/pvp" + ChatColor.GRAY + ".");
				return;
			}
			if (!damagerUser.pvpEnabled()) {
				event.setCancelled(true);
				damager.sendMessage(ChatColor.GRAY + "You have PVP disabled! Enable it by using " + ChatColor.GREEN + "/pvp" + ChatColor.GRAY + ".");
				target.sendMessage(ChatColor.GRAY + damager.getName() + " tried to hit you, but they have PVP disabled.");
				return;
			}
			boolean targetCombat = targetUser.inCombat();
			boolean damagerCombat = damagerUser.inCombat();
			targetUser.pvpEvent();
			damagerUser.pvpEvent();
			if (!targetCombat) {
				target.sendMessage(ChatColor.LIGHT_PURPLE + "You are in combat for the next " + targetUser.getCombatTime() / 20 + " seconds. DO NOT LOG OUT.");
			}
			if (!damagerCombat) {
				damager.sendMessage(ChatColor.LIGHT_PURPLE + "You are in combat for the next " + damagerUser.getCombatTime() / 20 + " seconds. DO NOT LOG OUT.");
			}
		}
		if (event.getDamager().hasMetadata("venom")) {
			if (event.getEntity() instanceof Player) {
				Player player = (Player) event.getEntity();
				player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 200, 1));
			}
		}
	}
}
