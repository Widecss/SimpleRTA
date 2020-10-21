package cn.widecss;

import org.bukkit.plugin.java.JavaPlugin;

public class SimpleRTA extends JavaPlugin {

    private ConfigManager configManager;

    @Override
    public void onLoad() {
        configManager = new ConfigManager(this);
        getLogger().info("SimpleRTA 已加载");
    }

    @Override
    public void onEnable() {
        if (configManager.isEnabled()) {
            getLogger().info("SimpleRTA 已启用");
        } else {
            this.setEnabled(false);
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("SimpleRTA 已禁用");
    }

    public static void main(String[] args) {

    }
}
