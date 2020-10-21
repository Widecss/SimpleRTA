package cn.widecss;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConfigManager {

    private final Plugin context;
    private File configFile;

    private boolean enabled;

    public ConfigManager(Plugin context) {
        if (context == null) {
            throw new IllegalArgumentException("Plugin cannot be null!");
        } else {
            this.context = context;

            configFile = new File(context.getDataFolder(), "config.yml");

            YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
            if (!config.isSet("enabled")) {
                config.set("enable", true);
                config.options().header("Configuration\n Creation time: " + new SimpleDateFormat().format(new Date()));

                try {
                    config.save(configFile);
                } catch (IOException e) {
                    context.getLogger().severe("初始化配置文件失败");
                }
            }

            enabled = config.getBoolean("enabled");
        }
    }

    public void loadFromFile() {
        YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
        enabled = config.getBoolean("enabled");

        context.getLogger().info("读取配置文件成功");
    }

    public void saveToFile() {
        YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
        config.set("enabled", enabled);

        try {
            config.save(configFile);
            context.getLogger().info("保存配置文件成功");
        } catch (IOException e) {
            context.getLogger().severe("保存配置文件失败" + e);
        }
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
