package me.grovre.plotsforranks;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BreakBlock implements Listener {

    private Player player;

    @EventHandler
    public void onPlayerBreakBlock(BlockBreakEvent event) {
        Block block = event.getBlock();
        if(block.getType() == Material.GRASS_BLOCK) {
            event.setCancelled(true);

            block.setType(Material.AIR);
            ItemStack diamonds = new ItemStack(Material.DIAMOND, 64);
            block.getWorld().dropItemNaturally(block.getLocation(), diamonds);
        }
        this.player = event.getPlayer();
        System.out.println("Block broken by " + player);
        Town town = TownyAPI.getInstance().getResidentTownOrNull(new Resident(player.getName()));
        if(town == null) return;
        town.addBonusBlocks(1);
    }

}
