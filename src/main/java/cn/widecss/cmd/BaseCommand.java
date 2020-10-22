package cn.widecss.cmd;

import cn.widecss.SimpleRTAPlugin;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class BaseCommand implements CommandExecutor {

    protected final SimpleRTAPlugin context;

    public BaseCommand(SimpleRTAPlugin context) {
        this.context = context;
    }

    public boolean isPlayer(CommandSender commandSender) {
        return commandSender instanceof Player;
    }
}
