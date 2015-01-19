package com.tigerhix.bossbar.bossbar;

import net.minecraft.server.v1_8_R1.DataWatcher;
import net.minecraft.server.v1_8_R1.Packet;
import org.bukkit.Location;

public interface Bossbar {

    public String getMessage();

    public void setMessage(String message);

    public float getPercentage();

    public void setPercentage(float percentage);

    public Packet getSpawnPacket();

    public Packet getDestroyPacket();

    public Packet getMetaPacket(DataWatcher watcher);

    public Packet getTeleportPacket(Location location);

    public DataWatcher getWatcher();

}
