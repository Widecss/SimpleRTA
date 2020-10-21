package cn.widecss;

import cn.widecss.cmd.CommandHelp;
import cn.widecss.cmd.CommandReload;
import cn.widecss.cmd.CommandSetRunner;
import cn.widecss.inter.PlayerExecutor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

public class SimpleRTAPlugin extends JavaPlugin {

    private YamlConfiguration config;
    private boolean enabled;

    private String runnerName;

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
            this.addCommand();

        } else {
            this.setEnabled(false);
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("SimpleRTA 已禁用");
    }

    public void sendToAllPlayer(String msg) {
        BukkitUtil.runOnOnlinePlayer(player -> player.sendMessage(msg));
    }

    public void reloadGame() {

    }

    private void addCommand() {
        this.getCommand("rtahelp").setExecutor(new CommandHelp(this));
        this.getCommand("rtareload").setExecutor(new CommandReload(this));
        this.getCommand("setrunner").setExecutor(new CommandSetRunner(this));
    }

    public void setRunnerName(String runnerName) {
        this.runnerName = runnerName;
    }

    public static void main(String[] args) {

    }
}
