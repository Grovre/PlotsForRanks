package me.grovre.plotsforranks;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import sh.okx.rankup.events.PlayerRankupEvent;
import sh.okx.rankup.ranks.Rank;
import sh.okx.rankup.ranks.RankElement;

public class RankUp implements Listener {

    private Player player;

    @EventHandler
    public void onPlayerRankup(PlayerRankupEvent event) {
        this.player = event.getPlayer();
        Town town = TownyAPI.getInstance().getResidentTownOrNull(new Resident(player.getName()));

        String rankString = event.getRank().getRank().getRank();
        int rank = Integer.parseInt(rankString.substring(rankString.length()-1));
        System.out.println(event.getRank().getRank().getRank());

        if(town == null) return;
        town.addBonusBlocks(1000);
    }

    @EventHandler
    public void onBreakBlock(BlockBreakEvent event) {
        this.player = event.getPlayer();
        System.out.println("Block broken by " + player + " and printed through RankUp.java!!! LETS GOOOOOO");
        Town town = TownyAPI.getInstance().getResidentTownOrNull(new Resident(player.getName()));
        town.addBonusBlocks(1);
    }

}
