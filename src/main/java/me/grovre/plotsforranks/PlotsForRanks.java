package me.grovre.plotsforranks;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class PlotsForRanks extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("\nHello world, PlotsForRanks 0.1 loading...");

        getServer().getPluginManager().registerEvents(new BreakBlock(), this);
        // getServer().getPluginManager().registerEvents(new RankUp(), this);

        System.out.println("Loading complete.\n");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("\n\n\nPlotsForRanks says goodbye :(\n\n\n");
    }
}
