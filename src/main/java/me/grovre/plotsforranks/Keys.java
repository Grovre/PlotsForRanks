package me.grovre.plotsforranks;

import org.bukkit.NamespacedKey;

public class Keys {

    public static NamespacedKey contributedToTownKey;

    public static void loadKeys() {

        contributedToTownKey = new NamespacedKey(PlotsForRanks.getPlugin(), "contributedToTown");

    }

}
