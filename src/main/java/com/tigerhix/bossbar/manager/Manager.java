package com.tigerhix.bossbar.manager;

import com.tigerhix.bossbar.bossbar.Bossbar;
import org.bukkit.entity.Player;

public interface Manager<T extends Bossbar> {

    public T newBossbar(Player player, String message);

    public void removeBossbar(Player player);

    public T getBossbar(Player player);

    public boolean hasBossbar(Player player);

    public void updateBossbar(Player player, T entity);

    public void dispose();

}
