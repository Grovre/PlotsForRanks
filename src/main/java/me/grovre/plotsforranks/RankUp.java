package me.grovre.plotsforranks;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.TownyUniverse;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import sh.okx.rankup.events.PlayerRankupEvent;

import java.util.UUID;

public class RankUp implements Listener {

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

        int rankValue = PlotsForRanks.getPlayerBonusBlocks(event.getRank().getRank());
        player.sendMessage("Retrieved rank: " + rankValue);

        // Checks if player is in a town
        if(town == null) {
            player.sendMessage("Town is null! See RankUp.java");
            player.sendMessage("Can't add bonus blocks!");
            player.sendMessage("You aren't in a town. When you join a town, your bonus blocks will be added to the town");
            return;
        }

        town.addBonusBlocks(rankValue);
    }

}
