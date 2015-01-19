package com.tigerhix.bossbar.bossbar.implementation.wither;

import com.tigerhix.bossbar.bossbar.Bossbar;
import com.tigerhix.bossbar.util.Utils;
import net.minecraft.server.v1_8_R1.World;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R1.CraftWorld;

public abstract class WitherBossbar implements Bossbar {

    public static final float MAX_HEALTH = 300;
    public float health = 0;
    public String name;
    private int x;
    private int y;
    private int z;
    private int pitch = 0;
    private int yaw = 0;
    private byte motX = 0;
    private byte motY = 0;
    private byte motZ = 0;
    private boolean visible = false;
    private World world;

    public WitherBossbar(String name, Location location) {
        setMessage(name);
        this.health = MAX_HEALTH;
        this.x = location.getBlockX();
        this.y = location.getBlockY();
        this.z = location.getBlockZ();
        this.world = ((CraftWorld) location.getWorld()).getHandle();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public int getPitch() {
        return pitch;
    }

    public void setPitch(int pitch) {
        this.pitch = pitch;
    }

    public int getYaw() {
        return yaw;
    }

    public void setYaw(int yaw) {
        this.yaw = yaw;
    }

    public byte getMotX() {
        return motX;
    }

    public void setMotX(byte motX) {
        this.motX = motX;
    }

    public byte getMotY() {
        return motY;
    }

    public void setMotY(byte motY) {
        this.motY = motY;
    }

    public byte getMotZ() {
        return motZ;
    }

    public void setMotZ(byte motZ) {
        this.motZ = motZ;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public float getHealthScaled() {
        return health / MAX_HEALTH;
    }

    public void setHealthScaled(float percentage) {
        this.health = percentage * MAX_HEALTH;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    @Override
    public String getMessage() {
        return getName();
    }

    @Override
    public void setMessage(String message) {
        setName(Utils.format(message));
    }

    @Override
    public float getPercentage() {
        return getHealthScaled();
    }

    @Override
    public void setPercentage(float percentage) {
        setHealthScaled(percentage);
    }

}
