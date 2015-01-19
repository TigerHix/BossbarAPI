package com.tigerhix.bossbar.api;

import com.tigerhix.bossbar.bossbar.Bossbar;
import com.tigerhix.bossbar.manager.Manager;
import org.bukkit.entity.Player;

/**
 * Utilize boss bar for displaying messages!
 *
 * @author confuser, TigerHix
 */
@SuppressWarnings("unchecked")
public final class BossbarAPI {

    private static Manager manager;

    public static Bossbar get(Player player) {
        return manager.getBossbar(player);
    }

    public static String getMessage(Player player) {
        if (!has(player)) return "";
        return get(player).getMessage();
    }

    public static float getPercentage(Player player) {
        if (!has(player)) return 0;
        return get(player).getPercentage();
    }

    public static boolean has(Player player) {
        return manager.hasBossbar(player);
    }

    public static void remove(Player player) {
        if (!has(player)) return;
        manager.removeBossbar(player);
    }

    public static void setManager(Manager manager) {
        BossbarAPI.manager = manager;
    }

    public static void updateAll(Player player, String message, float percentage) {
        if (percentage > 1) percentage = 1;
        else if (percentage < 0) percentage = 0;
        Bossbar bar = get(player);
        if (bar == null) {
            bar = manager.newBossbar(player, message);
        }
        bar.setMessage(message);
        bar.setPercentage(percentage);
        manager.updateBossbar(player, bar);
    }

    public static void updateMessage(Player player, String message) {
        Bossbar bar = get(player);
        if (bar == null) {
            bar = manager.newBossbar(player, message);
        }
        bar.setMessage(message);
        manager.updateBossbar(player, bar);
    }

    public static void updatePercentage(Player player, float percentage) {
        if (percentage > 1) percentage = 1;
        else if (percentage < 0) percentage = 0;
        Bossbar bar = get(player);
        if (bar == null) {
            bar = manager.newBossbar(player, "&6&lBOSSBAR &c&lAPI");
        }
        bar.setPercentage(percentage);
        manager.updateBossbar(player, bar);
    }

}
