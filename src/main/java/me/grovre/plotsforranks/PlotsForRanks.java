package me.grovre.plotsforranks;

import com.palmergames.bukkit.towny.object.Resident;
import me.grovre.plotsforranks.Listeners.OnJoinTown;
import me.grovre.plotsforranks.Listeners.OnLeaveTown;
import me.grovre.plotsforranks.Listeners.OnRankUp;
import me.grovre.plotsforranks.Listeners.OnServerJoin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import sh.okx.rankup.RankupPlugin;
import sh.okx.rankup.ranks.Rank;

public final class PlotsForRanks extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("\nHello world, PlotsForRanks loading...");

        getServer().getPluginManager().registerEvents(new OnJoinTown(), this);
        getServer().getPluginManager().registerEvents(new OnLeaveTown(), this);
        getServer().getPluginManager().registerEvents(new OnRankUp(), this);
        getServer().getPluginManager().registerEvents(new OnServerJoin(), this);

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
        return rankValue - 1; // Assuming everybody starts at rank 1
    }

    public static int getPlayerBonusBlocks(Player player) {
        Rank rank = getPlayerRank(player);
        return getPlayerBonusBlocks(rank);
    }

    public static int getPlayerBonusBlocks(Resident resident) {
        Player player = resident.getPlayer();
        return getPlayerBonusBlocks(player);
    }

    public static Rank getPlayerRank(Player player) {
        RankupPlugin plugin = (RankupPlugin) Bukkit.getPluginManager().getPlugin("Rankup");
        if(plugin == null) player.sendMessage("RankupPlugin plugin in PlotsForRanks.java class getPlayerRank FAILED. Please contact JappaCheese or PoptartFromPluto");
        return plugin.getRankups().getRankByPlayer(player);
    }
}
