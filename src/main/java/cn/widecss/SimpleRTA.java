package cn.widecss;

import org.bukkit.plugin.java.JavaPlugin;

public class SimpleRTA extends JavaPlugin {

    @Override
    public void onLoad() {
        getLogger().info("SimpleRTA 已加载");
    }

    @Override
    public void onEnable() {
        getLogger().info("SimpleRTA 已启用");
    }

    @Override
    public void onDisable() {
        getLogger().info("SimpleRTA 已禁用");
    }

    public static void main(String[] args) {

    }
}
