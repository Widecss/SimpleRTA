package cn.widecss.cmd;

import cn.widecss.BukkitUtil;
import cn.widecss.SimpleRTAPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandStart extends BaseCommand {

    public CommandStart(SimpleRTAPlugin context) {
        super(context);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length > 0) {
            commandSender.sendMessage("指令参数过多, 请检查正确后再重新执行");
            return false;
        } else {
            BukkitUtil.sendToAllPlayer("游戏开始! 尽情享受!");
            return true;
        }
    }
}
