package me.michaelkrauty.CarbonMC;

import me.michaelkrauty.CarbonMC.commands.PVP;
import me.michaelkrauty.CarbonMC.commands.Test;
import me.michaelkrauty.CarbonMC.objects.User;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created on 7/25/2014.
 *
 * @author michaelkrauty
 */
public class Main extends JavaPlugin {

	public static Main main;

	public static Economy economy = null;

	public static Config config;
	public static SQL sql;

	public static HashMap<Player, User> users = new HashMap<Player, User>();

	public static HashMap<UUID, Pig> uuidPigHashMap = new HashMap<UUID, Pig>();

	public static HashMap<Pig, Inventory> combatLoggers = new HashMap<Pig, Inventory>();

	public static HashMap<Pig, ItemStack[]> combatLoggersArmor = new HashMap<Pig, ItemStack[]>();

	public void onEnable() {
		main = this;
		setupEconomy();
		getServer().getPluginManager().registerEvents(new Listener(this), this);
		getServer().getPluginCommand("pvp").setExecutor(new PVP(this));
		getServer().getPluginCommand("test").setExecutor(new Test(this));
		if (!getDataFolder().exists()) getDataFolder().mkdir();
		File userdata = new File(getDataFolder(), "userdata");
		if (!userdata.exists()) userdata.mkdir();
		for (Player player : getServer().getOnlinePlayers()) {
			users.put(player, new User(this, player));
		}
		config = new Config(this);
		sql = new SQL(config);
	}

	public void onDisable() {
		for (Player player : getServer().getOnlinePlayers()) {
			users.get(player).save();
			users.remove(player);
		}
		sql.closeConnection();
	}

	private boolean setupEconomy() {
		RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
		if (economyProvider != null) {
			economy = economyProvider.getProvider();
		}

		return (economy != null);
	}
}
