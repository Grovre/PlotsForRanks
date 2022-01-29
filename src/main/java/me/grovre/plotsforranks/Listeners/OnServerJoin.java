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
        this.player = event.getPlayer();
        this.resident = TownyAPI.getInstance().getResident(player);
        this.town = TownyAPI.getInstance().getResidentTownOrNull(resident);
        if( town != null) this.townResidents = town.getResidents();

        // Gatekeeper
        if(!player.hasPlayedBefore()) return;

        if(town == null && PlotsForRanks.getPlayerBonusBlocks(player) > 0) {
            player.sendMessage("You aren't in a town. When you join a town, your bonus blocks will be added to the town");
            System.out.println(player.getName() + " is not in a town. Cannot update town bonus blocks");
            return;
        }

        // Gets the number of bonus plots the town SHOULD have with ranks from all residents added up
        int shouldHaveBonusBlocks = 0;
        for(Resident r : townResidents) {
            shouldHaveBonusBlocks += PlotsForRanks.getPlayerBonusBlocks(r);
        }

        // Gatekeeper
        // If the town has more than or equal to the town blocks a town should have, doesn't update the town blocks
        if(town.getBonusBlocks() >= shouldHaveBonusBlocks) return;

        // Otherwise, updates town bonus blocks available
        town.setBonusBlocks(shouldHaveBonusBlocks);

        if(player == town.getMayor().getPlayer()) {
            player.sendMessage("All residents of a town now contribute to a town's amount of bonus blocks based on their rank. Your town now has " + town.getBonusBlocks() + " bonus blocks. Thank you for playing and enjoy your new rank bonus!");
        }
    }
}
