package cn.widecss;

import cn.widecss.cmd.CommandHelp;
import cn.widecss.cmd.CommandReload;
import cn.widecss.cmd.CommandSetRunner;
import cn.widecss.cmd.CommandStart;
import cn.widecss.inter.PlayerExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

public class SimpleRTAPlugin extends JavaPlugin {

    private YamlConfiguration config;
    private boolean enabled;

    private String runnerName;
    private AtomicBoolean started;

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

    public void reloadGame() {

    }

    private void addCommand() {
        PluginCommand rtaHelp = this.getCommand("rtahelp");
        if (rtaHelp != null) {
            rtaHelp.setExecutor(new CommandHelp(this));
        }
        PluginCommand rtaReload = this.getCommand("rtareload");
        if (rtaReload != null) {
            rtaReload.setExecutor(new CommandReload(this));
        }
        PluginCommand setRunner = this.getCommand("setrunner");
        if (setRunner != null) {
            setRunner.setExecutor(new CommandSetRunner(this));
        }
        PluginCommand start = this.getCommand("start");
        if (start != null) {
            start.setExecutor(new CommandStart(this));
        }
    }

    public void setRunnerName(String runnerName) {
        this.runnerName = runnerName;
    }

    public static void main(String[] args) {

    }
}
