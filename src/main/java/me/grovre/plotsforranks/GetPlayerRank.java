package me.grovre.plotsforranks;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import sh.okx.rankup.RankupPlugin;
import sh.okx.rankup.ranks.Rank;
import sh.okx.rankup.ranks.RankList;
import sh.okx.rankup.ranks.Rankups;

interface GetPlayerRank extends Player {
    public static Rank getPlayerRank(Player player) {
        Plugin plugin = JavaPlugin.getPlugin(sh.okx.rankup.RankupPlugin.class);
        
    }
}
