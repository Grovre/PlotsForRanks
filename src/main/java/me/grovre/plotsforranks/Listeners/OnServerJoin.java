package me.grovre.plotsforranks.Listeners;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import me.grovre.plotsforranks.PlotsForRanks;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

public class OnServerJoin implements Listener {

    private Player player;
    private Resident resident;
    private Town town;
    private List<Resident> townResidents;

    @EventHandler
    public void OnPlayerJoin(PlayerJoinEvent event) {
        player = event.getPlayer();
        resident = TownyAPI.getInstance().getResident(player);
        town = TownyAPI.getInstance().getResidentTownOrNull(resident);
        townResidents = town.getResidents();

        if(town == null) {
            player.sendMessage("You aren't in a town. When you join a town, your bonus blocks will be added to the town");
            System.out.println(player.getName() + " is not in a town. Cannot update town bonus blocks");
            return;
        }

        // Gets the number of bonus plots the town SHOULD have with ranks from all residents added up
        int currentTownBonusBlocks = 0;
        for(Resident r : townResidents) {
            currentTownBonusBlocks += PlotsForRanks.getPlayerBonusBlocks(r);
        }
        int bonusBlocks = PlotsForRanks.getPlayerBonusBlocks(player);

        // If the town has more than or equal to the town blocks a town should have, doesn't update the town blocks
        if(town.getBonusBlocks() >= currentTownBonusBlocks) return;
        // Otherwise, updates town bonus blocks available
        player.sendMessage("Your town is being updated with new bonus plots. Thank you for playing and enjoy your new rank bonus!");
        town.setBonusBlocks(currentTownBonusBlocks);
    }
}
