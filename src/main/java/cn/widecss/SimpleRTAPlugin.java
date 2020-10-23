package cn.widecss;

import cn.widecss.game.GameManager;
import cn.widecss.listener.ListenerBlock;
import cn.widecss.listener.ListenerEntity;
import cn.widecss.listener.ListenerPlayer;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.YamlConfiguration;
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

        YamlConfiguration config = (YamlConfiguration) getConfig();
        if (!config.isSet("enabled")) {
            config.set("enabled", true);
            config.set("debug", false);
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

            getServer().getPluginManager().registerEvents(new ListenerBlock(this),this);
            getServer().getPluginManager().registerEvents(new ListenerEntity(this),this);
            getServer().getPluginManager().registerEvents(new ListenerPlayer(this),this);
            this.addCommand();
        } else {
            this.setEnabled(false);
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("SimpleRTA 已卸载");
    }

    private void addCommand() {
        PluginCommand rta = this.getCommand("rta");
        if (rta != null) {
            rta.setExecutor(new CommandHandler(this));
        }
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
