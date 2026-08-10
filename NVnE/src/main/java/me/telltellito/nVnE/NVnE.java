package me.telltellito.nVnE;

import me.telltellito.nVnE.handler.NightVisionHandler;
import me.telltellito.nVnE.handler.ShopHandler;
import me.telltellito.nVnE.handler.SpawnerHandler;
import org.bukkit.plugin.java.JavaPlugin;

public final class NVnE extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("hello world");

        getCommand("shop").setExecutor(new ShopHandler());




        getLogger().info("loading 1");
        new SpawnerHandler(this);
        getLogger().info("loaded 1");
        getLogger().info("loading 2");
        new NightVisionHandler(this);
        getLogger().info("loaded 2");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
