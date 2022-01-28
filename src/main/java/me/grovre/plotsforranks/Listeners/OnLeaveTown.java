package me.grovre.plotsforranks.Listeners;

import com.palmergames.bukkit.towny.event.TownRemoveResidentEvent;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import me.grovre.plotsforranks.PlotsForRanks;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import sh.okx.rankup.ranks.Rank;

import java.util.UUID;

public class OnLeaveTown implements Listener {

    private Player player;
    private Resident resident;
    private UUID uuid;
    private Town town;
    private Rank playerRank;

    @EventHandler
    public void onPlayerLeaveTown(TownRemoveResidentEvent event) {

        this.resident = event.getResident();
        this.player = resident.getPlayer();
        this.uuid = player.getUniqueId();
        this.town = event.getTown();
        this.playerRank = PlotsForRanks.getPlayerRank(player);

        int bonusBlockCount = PlotsForRanks.getPlayerBonusBlocks(playerRank);
        town.addBonusBlocks(bonusBlockCount * -1);

    }
}
