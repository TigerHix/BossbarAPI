package com.tigerhix.bossbar.plugin;

import com.tigerhix.bossbar.api.BossbarAPI;
import com.tigerhix.bossbar.manager.Manager;
import com.tigerhix.bossbar.manager.WitherManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Plugin extends JavaPlugin {

    private static Plugin plugin;
    private Manager manager;

    public static Plugin instance() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;
        manager = new WitherManager();
        BossbarAPI.setManager(manager);
    }

    @Override
    public void onDisable() {
        manager.dispose();
    }

    public Manager getManager() {
        return manager;
    }

}
