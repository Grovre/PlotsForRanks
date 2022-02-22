package me.grovre.plotsforranks;

import com.palmergames.bukkit.towny.object.Resident;
import me.grovre.plotsforranks.Listeners.OnJoinTown;
import me.grovre.plotsforranks.Listeners.OnLeaveTown;
import me.grovre.plotsforranks.Listeners.OnRankUp;
import me.grovre.plotsforranks.Listeners.OnServerJoin;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import sh.okx.rankup.RankupPlugin;
import sh.okx.rankup.ranks.Rank;
import sh.okx.rankup.ranks.RankElement;

import java.util.List;

public final class PlotsForRanks extends JavaPlugin {

    public static PlotsForRanks plugin;

    public static PlotsForRanks getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        Keys.loadKeys();
        getServer().getPluginManager().registerEvents(new OnJoinTown(), this);
        getServer().getPluginManager().registerEvents(new OnLeaveTown(), this);
        getServer().getPluginManager().registerEvents(new OnRankUp(), this);
        getServer().getPluginManager().registerEvents(new OnServerJoin(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("\n\n\nPlotsForRanks says goodbye :(\n\n\n");
    }

    public static RankupPlugin getRankupPlugin() {
        return RankupPlugin.getPlugin(RankupPlugin.class);
    }

    public static int getPlayerBonusBlocks(Rank rank) {
        RankupPlugin rankup = getRankupPlugin();
        List<RankElement<Rank>> rankList = rankup.getRankups().getTree().asList();
        int rankValue = 1;
        for (int i = 0; i < rankList.size(); i++) {
            Rank rankFromList = rankList.get(i).getRank();
            if (rankFromList.equals(rank)) {
                rankValue = i;
                break;
            }
        }
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
        RankupPlugin plugin = getRankupPlugin();
        return plugin.getRankups().getRankByPlayer(player);
    }

    public static void setHasContributedToTownBefore(Player player, boolean hasContributed) {
        player.getPersistentDataContainer().set(Keys.contributedToTownKey, PersistentDataType.INTEGER, hasContributed ? 1 : 0);
    }

    public static boolean hasContributedToTownBefore(Player player) {
        Integer hasContributed = player.getPersistentDataContainer().get(Keys.contributedToTownKey, PersistentDataType.INTEGER);
        if(hasContributed == null) return false;
        return hasContributed == 1;
    }
}
