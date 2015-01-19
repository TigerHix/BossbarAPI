package com.tigerhix.bossbar.bossbar.implementation.wither;

import com.tigerhix.bossbar.util.Reflections;
import net.minecraft.server.v1_8_R1.*;
import org.bukkit.Location;

public class CraftWitherBossbar extends WitherBossbar {

    private EntityWither wither;
    private int id;

    public CraftWitherBossbar(String name, Location loc) {
        super(name, loc);
    }

    @Override
    public Packet getSpawnPacket() {
        wither = new EntityWither(getWorld());
        wither.setLocation(getX(), getY(), getZ(), getPitch(), getYaw());
        wither.setInvisible(isVisible());
        wither.setCustomName(getName());
        wither.setHealth(getHealth());
        wither.motX = getMotX();
        wither.motY = getMotY();
        wither.motZ = getMotZ();
        this.id = wither.getId();
        return new PacketPlayOutSpawnEntityLiving(wither);
    }

    @Override
    public Packet getDestroyPacket() {
        PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy();
        Reflections.getField(PacketPlayOutEntityDestroy.class, "a", int[].class).set(packet, new int[]{id});
        return packet;
    }

    @Override
    public Packet getMetaPacket(DataWatcher watcher) {
        return new PacketPlayOutEntityMetadata(id, watcher, true);
    }

    @Override
    public Packet getTeleportPacket(Location location) {
        return new PacketPlayOutEntityTeleport(
                this.id,
                location.getBlockX() * 32,
                location.getBlockY() * 32,
                location.getBlockZ() * 32,
                (byte) ((int) location.getYaw() * 256 / 360),
                (byte) ((int) location.getPitch() * 256 / 360),
                false
        );
    }

    @Override
    public DataWatcher getWatcher() {
        DataWatcher watcher = new DataWatcher(wither);
        watcher.a(0, isVisible() ? (byte) 0 : (byte) 0x20);
        watcher.a(6, health);
        watcher.a(7, 0);
        watcher.a(8, (byte) 0);
        watcher.a(10, name);
        watcher.a(11, (byte) 1);
        return watcher;
    }

}
