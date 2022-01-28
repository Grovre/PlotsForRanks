package me.grovre.plotsforranks;

import me.grovre.plotsforranks.Listeners.OnJoinTown;
import me.grovre.plotsforranks.Listeners.OnLeaveTown;
import me.grovre.plotsforranks.Listeners.OnRankUp;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import sh.okx.rankup.RankupPlugin;
import sh.okx.rankup.ranks.Rank;

public final class PlotsForRanks extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("\nHello world, PlotsForRanks 0.1 loading...");

        getServer().getPluginManager().registerEvents(new OnJoinTown(), this);
        getServer().getPluginManager().registerEvents(new OnLeaveTown(), this);
        getServer().getPluginManager().registerEvents(new OnRankUp(), this);

        System.out.println("Loading complete.\n");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("\n\n\nPlotsForRanks says goodbye :(\n\n\n");
    }

    public static int getPlayerBonusBlocks(Rank rank) {
        String rankString = rank.getRank();
        int rankValue = Integer.parseInt(rankString.substring(rankString.length()-1));
        if(rankValue <= 0) rankValue = 10;
        return rankValue;
    }

    public static Rank getPlayerRank(Player player) {
        RankupPlugin plugin = (RankupPlugin) Bukkit.getPluginManager().getPlugin("Rankup");
        if(plugin == null) player.sendMessage("RankupPlugin plugin in GetPlayerRank.java FAILED");
        return plugin.getRankups().getRankByPlayer(player);
    }
}
