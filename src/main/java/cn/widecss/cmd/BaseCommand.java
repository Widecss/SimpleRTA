package cn.widecss.cmd;

import cn.widecss.SimpleRTAPlugin;
import org.bukkit.command.CommandExecutor;

public abstract class BaseCommand implements CommandExecutor {

    protected final SimpleRTAPlugin context;

    public BaseCommand(SimpleRTAPlugin context) {
        this.context = context;
    }
}
