package cn.widecss.cmd;

import cn.widecss.SimpleRTAPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHelp extends BaseCommand {

    public CommandHelp(SimpleRTAPlugin context) {
        super(context);
    }

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        commandSender.sendMessage(
                "一个简单的插件, 用于维持规则" + "\n" +
                        "/rtahelp 输出帮助菜单" + "\n" +
                        "/setrunner <玩家名> 设置作为 Runner 的玩家（一名）, 其余的默认为 Hunter" + "\n" +
                        "/rtareload 重载一场游戏, 用于开新图的时候");
        return true;
    }
}
