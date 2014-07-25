package me.michaelkrauty.CarbonMC.listener;

import me.michaelkrauty.CarbonMC.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Pig;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.io.File;

/**
 * Created on 7/25/2014.
 *
 * @author michaelkrauty
 */
public class EntityDeath {

	public EntityDeath(Main main, EntityDeathEvent event) {
		if (event.getEntity().getType() == EntityType.PIG) {
			Pig pig = (Pig) event.getEntity();
			if (main.combatLoggers.get(pig) != null) {
				World world = event.getEntity().getWorld();
				Location loc = event.getEntity().getLocation();
				for (ItemStack i : main.combatLoggers.get(pig).getContents()) {
					if (i != null)
						world.dropItem(loc, i);
				}
				if (main.combatLoggersArmor.get(pig) != null) {
					for (ItemStack i : main.combatLoggersArmor.get(pig)) {
						if (i != null && i.getType() != Material.AIR)
							world.dropItem(loc, i);
					}
				}
				try {
					File userFile = new File(main.getDataFolder() + "/userdata/" + pig.getMetadata("inplaceofplayer").get(0).asString() + ".yml");
					YamlConfiguration yaml = new YamlConfiguration();
					yaml.load(userFile);
					yaml.set("deadlogin", true);
					yaml.save(userFile);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
