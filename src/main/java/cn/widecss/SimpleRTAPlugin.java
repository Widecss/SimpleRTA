package cn.widecss;

import cn.widecss.game.GameManager;
import cn.widecss.listener.ListenerBlock;
import cn.widecss.listener.ListenerEntity;
import cn.widecss.listener.ListenerPlayer;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleRTAPlugin extends JavaPlugin {

    private boolean enabled;

    private GameManager gameManager;
    private PlayerManager playerManager;

    @Override
    public void onLoad() {
        gameManager = new GameManager(this);
        playerManager = new PlayerManager(this);

        getLogger().info("正在加载配置...");
        YamlConfiguration config = (YamlConfiguration) getConfig();
        if (!config.isSet("enabled")) {
            getLogger().info("正在初始化配置文件...");
            config.set("enabled", true);
            config.set("debug", false);
            config.options().header(
                    " Configuration\n" +
                            " Creation time: " + new SimpleDateFormat().format(new Date()));
            saveConfig();
        }
        {
            enabled = config.getBoolean("enabled");
        }

        getLogger().info("插件已加载");
    }

    @Override
    public void onEnable() {
        if (enabled) {
            {
                getLogger().info("正在注册监听器...");
                PluginManager manager = getServer().getPluginManager();
                manager.registerEvents(new ListenerBlock(this), this);
                manager.registerEvents(new ListenerEntity(this), this);
                manager.registerEvents(new ListenerPlayer(this), this);
            }
            {
                getLogger().info("正在设置命令...");
                PluginCommand rta = this.getCommand("rta");
                if (rta != null) {
                    rta.setExecutor(new CommandHandler(this));
                }
            }
            getLogger().info("插件已启用");
        } else {
            this.setEnabled(false);
        }
    }

    @Override
    public void onDisable() {
        getGameManager().resetGame();
        getLogger().info("插件已卸载");
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public static void main(String[] args) {

    }
}
