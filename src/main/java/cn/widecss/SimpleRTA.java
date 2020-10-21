package cn.widecss;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleRTA extends JavaPlugin {

    private YamlConfiguration config;
    private boolean enabled;

    @Override
    public void onLoad() {
        config = (YamlConfiguration) getConfig();
        if (!config.isSet("enabled")) {
            config.set("enabled", true);
            config.options().header(
                    " Configuration\n" +
                    " Creation time: " + new SimpleDateFormat().format(new Date()));
            saveConfig();
        }
        enabled = config.getBoolean("enabled");

        getLogger().info("SimpleRTA 已加载");
    }

    @Override
    public void onEnable() {
        if (enabled) {
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
