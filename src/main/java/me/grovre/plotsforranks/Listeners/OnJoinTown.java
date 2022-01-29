package me.grovre.plotsforranks.Listeners;

import com.palmergames.bukkit.towny.event.TownAddResidentEvent;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import me.grovre.plotsforranks.PlotsForRanks;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class OnJoinTown implements Listener {

    private Player player;
    private Resident resident;
    private Town town;

    @EventHandler
    public void onPlayerJoinTown(TownAddResidentEvent event) {

        this.resident = event.getResident();
        this.player = resident.getPlayer();
        this.town = event.getTown();

        // Adds the amount of bonus blocks to a town equal to a player's rank
        town.addBonusBlocks(PlotsForRanks.getPlayerBonusBlocks(player));

    }
}
