package me.grovre.plotsforranks.Listeners;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import me.grovre.plotsforranks.PlotsForRanks;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnServerJoin implements Listener {


    @EventHandler
    public void OnPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if(!player.hasPlayedBefore()) return;
        if(PlotsForRanks.hasContributedToTownBefore(player)) return;
        Resident resident = TownyAPI.getInstance().getResident(player);
        Town town = TownyAPI.getInstance().getResidentTownOrNull(resident);
        if(town == null && PlotsForRanks.getPlayerBonusBlocks(player) > 0) {
            player.sendMessage(ChatColor.YELLOW + "You aren't in a town. When you join a town, your bonus blocks will be added to the town.");
            System.out.println(player.getName() + " is not in a town. Cannot update town bonus blocks");
            return;
        }

        assert town != null;
        town.setBonusBlocks(town.getBonusBlocks() + PlotsForRanks.getPlayerBonusBlocks(player));
        PlotsForRanks.setHasContributedToTownBefore(player, true);
    }
}
