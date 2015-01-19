package com.tigerhix.bossbar.manager;

import com.tigerhix.bossbar.api.BossbarAPI;
import com.tigerhix.bossbar.bossbar.implementation.wither.CraftWitherBossbar;
import com.tigerhix.bossbar.bossbar.implementation.wither.WitherBossbar;
import com.tigerhix.bossbar.plugin.Plugin;
import com.tigerhix.bossbar.util.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class WitherManager implements Listener, Manager<WitherBossbar> {

    private final Map<UUID, WitherBossbar> withers = new HashMap<>();

    public WitherManager() {
        Plugin.instance().getServer().getPluginManager().registerEvents(this, Plugin.instance());
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Plugin.instance().getServer().getOnlinePlayers()) {
                    teleport(player);
                }
            }
        }.runTaskTimer(Plugin.instance(), 0L, 10L);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerQuit(PlayerQuitEvent event) {
        removeBossbar(event.getPlayer());
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerKick(PlayerKickEvent event) {
        removeBossbar(event.getPlayer());
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerTeleport(final PlayerTeleportEvent event) {
        teleport(event.getPlayer());
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerTeleport(final PlayerRespawnEvent event) {
        teleport(event.getPlayer());
    }

    private void teleport(final Player player) {
        if (!BossbarAPI.has(player)) return;
        new BukkitRunnable() {

            @Override
            public void run() {
                if (!BossbarAPI.has(player)) return;
                WitherBossbar bar = getBossbar(player);
                float percentage = bar.getPercentage();
                String message = bar.getMessage();
                Utils.sendPacket(player, bar.getDestroyPacket());
                bar = newBossbar(player, message);
                bar.setPercentage(percentage);
                updateBossbar(player, bar);
            }

        }.runTaskLater(Plugin.instance(), 2L);
    }

    public WitherBossbar newBossbar(Player player, String message) {
        WitherBossbar wither = new CraftWitherBossbar(message, player.getLocation().add(player.getEyeLocation().getDirection().multiply(20)));
        Utils.sendPacket(player, wither.getSpawnPacket());
        withers.put(player.getUniqueId(), wither);
        return wither;
    }

    public void removeBossbar(Player player) {
        try {
            Utils.sendPacket(player, getBossbar(player).getDestroyPacket());
            withers.remove(player.getUniqueId());
        } catch (Exception ignore) {
        }
    }

    public WitherBossbar getBossbar(Player player) {
        return withers.get(player.getUniqueId());
    }

    public boolean hasBossbar(Player player) {
        return withers.containsKey(player.getUniqueId());
    }

    public void updateBossbar(Player player, WitherBossbar wither) {
        Utils.sendPacket(player, wither.getMetaPacket(wither.getWatcher()));
        Utils.sendPacket(player, wither.getTeleportPacket(player.getLocation().add(player.getEyeLocation().getDirection().multiply(20))));
        withers.put(player.getUniqueId(), wither);
    }

    public void dispose() {
        for (Player player : Plugin.instance().getServer().getOnlinePlayers()) removeBossbar(player);
        withers.clear();
    }

}
