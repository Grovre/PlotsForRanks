package me.grovre.plotsforranks.Listeners;

import com.palmergames.bukkit.towny.event.TownRemoveResidentEvent;
import com.palmergames.bukkit.towny.object.Town;
import me.grovre.plotsforranks.PlotsForRanks;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class OnLeaveTown implements Listener {

    @EventHandler
    public void onPlayerLeaveTown(TownRemoveResidentEvent event) {

        Player player = event.getResident().getPlayer();
        Town town = event.getTown();

        // Removes the amount of bonus blocks equal to someone's rank
        town.addBonusBlocks(PlotsForRanks.getPlayerBonusBlocks(player) * -1);
        // Sets bonus blocks to 0 if bonus blocks is below 0, as a safe measure in case towny doesn't already do this
        if(town.getBonusBlocks() < 0) town.setBonusBlocks(0);
    }
}
