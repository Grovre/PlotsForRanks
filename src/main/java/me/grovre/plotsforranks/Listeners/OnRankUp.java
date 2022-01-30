package me.grovre.plotsforranks.Listeners;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.TownyUniverse;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import me.grovre.plotsforranks.PlotsForRanks;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import sh.okx.rankup.events.PlayerRankupEvent;

import java.util.UUID;

public class OnRankUp implements Listener {

    private Player player;
    private Resident resident;
    private UUID uuid;
    private Town town;

    @EventHandler
    public void onPlayerRankup(PlayerRankupEvent event) {
        this.player = event.getPlayer();
        this.uuid = player.getUniqueId();
        this.resident = TownyUniverse.getInstance().getResident(uuid);
        this.town = TownyAPI.getInstance().getResidentTownOrNull(resident);

        // Gets a player's rank
        int rankValue = PlotsForRanks.getPlayerBonusBlocks(event.getRank().getRank());

        // Checks if player is in a town
        if(town == null) {
            player.sendMessage("You aren't in a town. When you join a town, your bonus blocks will be added to the town");
            System.out.println(player.getName() + " is not in a town, will give their new town blocks once they join");
            return;
        }

        // Adds an amount of bonus blocks equal to someone's rank as a number to the town
        town.addBonusBlocks(1);
    }

}
