package com.tigerhix.bossbar.util;

import net.minecraft.server.v1_8_R1.Packet;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Utils {

    public static String format(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static void sendPacket(Player p, Object packet) {
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket((Packet) packet);
    }

}