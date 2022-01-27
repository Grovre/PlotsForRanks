package me.grovre.plotsforranks;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.TownyUniverse;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class BreakBlock implements Listener {

    private Player player;
    private UUID uuid;
    private Resident resident;
    Town town = TownyAPI.getInstance().getResidentTownOrNull(resident);

    @EventHandler
    public void onPlayerBreakBlock(BlockBreakEvent event) {
        Block block = event.getBlock();
        if(block.getType() == Material.GRASS_BLOCK) {
            event.setCancelled(true);

            block.setType(Material.AIR);
            ItemStack diamonds = new ItemStack(Material.DIAMOND, 64);
            block.getWorld().dropItemNaturally(block.getLocation(), diamonds);
            if(town == null) {
                player.sendMessage("Your town is null, can't reward bonus block.");
            } else {
                player.sendMessage("You broke a grass block, rewarding 1 town bonus block.");
                town.addBonusBlocks(1);
                player.sendMessage("You now have " + town.getBonusBlocks() + "bonus blocks");
            }
        }
        this.player = event.getPlayer();
        this.uuid = player.getUniqueId();
        this.resident = TownyUniverse.getInstance().getResident(uuid);

        System.out.println("Block broken by " + player);

    }

}
