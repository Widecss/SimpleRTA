package cn.widecss.cmd;

import cn.widecss.BukkitUtil;
import cn.widecss.SimpleRTAPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.concurrent.atomic.AtomicReference;

public class CommandSetRunner extends BaseCommand {

    public CommandSetRunner(SimpleRTAPlugin context) {
        super(context);
    }

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 0) {
            commandSender.sendMessage("请输入玩家名称");
        }

        AtomicReference<Player> atomicReference = new AtomicReference<>(null);
        BukkitUtil.runOnOnlinePlayer(player -> {
            if (player.getDisplayName().equals(strings[0])) {
                atomicReference.set(player);
            }
        });

        Player player = atomicReference.get();
        if (player == null) {
            commandSender.sendMessage("该玩家不存在, 请重新输入");
        } else {
            this.context.getPlayerManager().setRunner(player);
            BukkitUtil.sendToAllPlayer("玩家 " + player.getDisplayName() + " 已被设置为 Runner");
        }
        return true;
    }
}
