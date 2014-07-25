package me.michaelkrauty.CarbonMC.listener;

import me.michaelkrauty.CarbonMC.Main;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Zombie;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

/**
 * Created on 7/25/2014.
 *
 * @author michaelkrauty
 */
public class CreatureSpawn {

	public CreatureSpawn(Main main, CreatureSpawnEvent event) {
		if (!event.getEntity().hasMetadata("CarbonCreepers")) {
			if (event.getEntityType() == EntityType.ZOMBIE) {
				if (new Random().nextInt(14) == 0) {
					Zombie zombie = (Zombie) event.getEntity();
					zombie.setMaxHealth(250);
					zombie.setHealth(250);
					zombie.setCustomName("Boss Zombie");
					zombie.setCustomNameVisible(true);
					return;
				}
				if (new Random().nextInt(6) == 0) {
					Zombie zombie = (Zombie) event.getEntity();
					zombie.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 72000, 2));
					zombie.setCustomName("Runner Zombie");
					zombie.setCustomNameVisible(true);
					return;
				}
			}

			if (event.getEntityType() == EntityType.WITCH) {
				/*
				if (new Random().nextInt(2) == 0) {
					Witch witch = (Witch) event.getEntity();
					witch.setMetadata("CarbonCreepers", new FixedMetadataValue(main, true));
					for (int i = 1; i < 5; i++) {
						Witch w = (Witch) witch.getWorld().spawnEntity(witch.getLocation(), EntityType.WITCH);
						w.setMetadata("CarbonCreepers", new FixedMetadataValue(main, true));
					}
					main.getLogger().info("Spawned witch group.");
					return;
				}
				*/
			}

			if (event.getEntityType() == EntityType.SPIDER) {
				if (new Random().nextInt(9) == 0) {
					Spider spider = (Spider) event.getEntity();
					spider.getWorld().spawnEntity(spider.getLocation(), EntityType.CAVE_SPIDER).getUniqueId();
					spider.remove();
					return;
				}
				if (new Random().nextInt(19) == 0) {
					Spider spider = (Spider) event.getEntity();
					spider.setMaxHealth(13);
					spider.setHealth(13);
					spider.setCustomName("Venomous Spider");
					spider.setCustomNameVisible(true);
					spider.setMetadata("venom", new FixedMetadataValue(main, true));
					return;
				}
			}

			if (event.getEntityType() == EntityType.PIG) {
				if (new Random().nextInt(14) == 0) {
					Pig pig = (Pig) event.getEntity();
					// TODO: hostile pigs and what not
				}
			}
		}
	}
}
