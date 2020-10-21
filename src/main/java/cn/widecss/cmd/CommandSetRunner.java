package cn.widecss.cmd;

import cn.widecss.BukkitUtil;
import cn.widecss.SimpleRTAPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.concurrent.atomic.AtomicBoolean;

public class CommandSetRunner extends BaseCommand {

    public CommandSetRunner(SimpleRTAPlugin context) {
        super(context);
    }

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            if (strings.length == 0) {
                commandSender.sendMessage("请输入玩家名称");
            }

            AtomicBoolean found = new AtomicBoolean(false);
            BukkitUtil.runOnOnlinePlayer(player -> {
                if (player.getDisplayName().equals(strings[0])) {
                    found.set(true);
                }
            });

            if (found.get()) {
                this.context.setRunnerName(strings[0]);
            } else {
                commandSender.sendMessage("该玩家不存在, 请重新输入");
            }
            return true;
        }
        return false;
    }
}
