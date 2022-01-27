package me.grovre.plotsforranks;

import com.palmergames.bukkit.towny.event.TownRemoveResidentEvent;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import sh.okx.rankup.ranks.RankTree;

import java.util.UUID;

public class LeaveTown implements Listener {

    private Player player;
    private Resident resident;
    private UUID uuid;
    private Town town;

    @EventHandler
    public void onPlayerLeaveTown(TownRemoveResidentEvent event) {

        this.resident = event.getResident();
        this.player = resident.getPlayer();
        this.uuid = player.getUniqueId();
        this.town = event.getTown();

        int bonusBlockCount = PlotsForRanks.getPlayerBonusBlocks()

    }
}
