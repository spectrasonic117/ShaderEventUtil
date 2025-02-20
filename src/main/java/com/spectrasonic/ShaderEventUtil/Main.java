package com.spectrasonic.ShaderEventUtil;

import com.spectrasonic.ShaderEventUtil.Utils.MessageUtils;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getMessenger().registerOutgoingPluginChannel(this, "soup:channel");
        getCommand("shaderutil").setExecutor(new ShaderUtilCommand(this));
        getCommand("shaderutil").setTabCompleter(new ShaderUtilTabCompleter());
        MessageUtils.sendStartupMessage(this);
    }

    @Override
    public void onDisable() {
        MessageUtils.sendShutdownMessage(this);
    }
}
