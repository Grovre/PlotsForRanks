package me.grovre.plotsforranks;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.TownyUniverse;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import sh.okx.rankup.events.PlayerRankupEvent;
import sh.okx.rankup.ranks.Rank;
import sh.okx.rankup.ranks.RankElement;

import java.util.UUID;

public class RankUp implements Listener {

    private Player player;
    private UUID uuid;
    private Resident resident;

    @EventHandler
    public void onPlayerRankup(PlayerRankupEvent event) {
        this.player = event.getPlayer();
        this.uuid = player.getUniqueId();
        this.resident = TownyUniverse.getInstance().getResident(uuid);

        Town town = TownyAPI.getInstance().getResidentTownOrNull(resident);

        String rankString = event.getRank().getRank().getRank();


        System.out.println(event.getRank().getRank().getRank());

        if(town == null) return;
        town.addBonusBlocks(1000);
    }

}
