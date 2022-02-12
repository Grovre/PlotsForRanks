package me.grovre.plotsforranks.Listeners;

import com.palmergames.bukkit.towny.event.TownAddResidentEvent;
import com.palmergames.bukkit.towny.object.Town;
import me.grovre.plotsforranks.PlotsForRanks;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class OnJoinTown implements Listener {

    @EventHandler
    public void onPlayerJoinTown(TownAddResidentEvent event) {

        Player player = event.getResident().getPlayer();
        Town town = event.getTown();

        // Adds the amount of bonus blocks to a town equal to a player's rank
        town.addBonusBlocks(PlotsForRanks.getPlayerBonusBlocks(player));

    }
}
