package cn.widecss.cmd;

import cn.widecss.SimpleRTAPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandReload extends BaseCommand {

    public CommandReload(SimpleRTAPlugin context) {
        super(context);
    }

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            if (strings.length > 0) {
                commandSender.sendMessage("指令参数过多, 请检查正确后再重新执行");
            } else {
                context.reloadGame();
                context.sendToAllPlayer("游戏已被重载! 请设置一位 Runner 以开始游戏...");
                return true;
            }
        }
        return false;
    }
}
