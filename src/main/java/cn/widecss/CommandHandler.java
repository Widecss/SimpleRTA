package cn.widecss;

import cn.widecss.game.GameType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public class CommandHandler implements CommandExecutor {

    private final SimpleRTAPlugin context;

    public CommandHandler(SimpleRTAPlugin context) {
        this.context = context;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 0) {
            commandSender.sendMessage("使用 /rta help 获取帮助");
        } else {
            String[] args = Arrays.copyOfRange(strings, 1, strings.length);
            switch (strings[0]) {
                case "help": {
                    help(commandSender, command, s, args);
                    break;
                }
                case "start": {
                    start(commandSender, command, s, args);
                    break;
                }
                case "reset": {
                    reset(commandSender, command, s, args);
                    break;
                }
                case "setrunner": {
                    setRunner(commandSender, command, s, args);
                    break;
                }
                case "setmode": {
                    setMode(commandSender, command, s, args);
                    break;
                }
                case "reload": {
                    reload(commandSender, command, s, args);
                    break;
                }
                default: {
                    commandSender.sendMessage("找不到功能 " + strings[0]);
                }
            }
        }
        return true;
    }

    public void help(CommandSender commandSender, Command command, String s, String[] strings) {
        commandSender.sendMessage(
                "一个简单的插件, 用于维持规则" + "\n" +
                        "/rta <func> [args...] " + "\n" +
                        " func: " + "\n" +
                        "  - setmode <id> " + "\n" +
                        "            设置游戏的模式,   0: 合作(COOPERATE),   1: 对抗(COMBAT) " + "\n" +
                        "  - setrunner <name> " + "\n" +
                        "            对抗模式下设置一名作为 Runner 的玩家, 其余的默认为 Hunter" + "\n" +
                        "  - start " + "\n" +
                        "            开始游戏 " + "\n" +
                        "  - reload " + "\n" +
                        "            重载配置文件 " + "\n" +
                        "  - reset " + "\n" +
                        "            重置游戏, 用于开新图的时候");
    }

    public void start(CommandSender commandSender, Command command, String s, String[] strings) {
        if (this.context.getGameManager().isStarted()) {
            commandSender.sendMessage("游戏已经开始, 请不要重复执行");
        }
        if (this.context.getGameManager().getGameType() == GameType.COMBAT) {
            if (this.context.getPlayerManager().getRunner() == null) {
                commandSender.sendMessage("还没有设置 Runner, 请使用 /rta setrunner <name> 设置");
                return;
            }
        }
        this.context.getGameManager().startGame();
    }

    public void reset(CommandSender commandSender, Command command, String s, String[] strings) {
        this.context.getGameManager().resetGame();
        BukkitUtil.sendToAllPlayer("游戏已被重置, 请设置一位 Runner 以开始游戏...");
    }

    public void setMode(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 0) {
            GameType gameType = this.context.getGameManager().getGameType();
            if (gameType == null) {
                commandSender.sendMessage("还没有设置游戏模式, 请输入模式id");
            } else {
                commandSender.sendMessage("目前的游戏模式为: " + gameType.getTypeName());
            }
        } else {
            try {
                GameType gameType = GameType.getFromID(Integer.parseInt(strings[0]));
                this.context.getGameManager().setGameType(gameType);
                BukkitUtil.sendToAllPlayer("游戏模式已被设置为" + gameType.getTypeName());
            } catch (NumberFormatException e) {
                commandSender.sendMessage("设置模式失败: 请输入数字, 而不是" + strings[0]);
            } catch (IllegalArgumentException e) {
                commandSender.sendMessage("设置模式失败: " + e.getMessage());
            }
        }
    }

    public void setRunner(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 0) {
            Player player = this.context.getPlayerManager().getRunner();
            if (player == null) {
                commandSender.sendMessage("还没有设置 Runner, 请输入玩家名称");
            } else {
                commandSender.sendMessage("当前 Runner 为: " + player.getDisplayName());
            }
        } else {
            AtomicReference<Player> reference = new AtomicReference<>(null);
            BukkitUtil.runOnOnlinePlayer(player -> {
                if (player.getDisplayName().equals(strings[0])) {
                    reference.set(player);
                }
            });

            Player player = reference.get();
            if (player == null) {
                commandSender.sendMessage("在线玩家" + strings[0] + "未找到, 请重新输入");
            } else {
                this.context.getPlayerManager().setRunner(player);
                BukkitUtil.sendToAllPlayer("玩家 " + player.getDisplayName() + " 已被设置为 Runner");
            }
        }
    }

    public void reload(CommandSender commandSender, Command command, String s, String[] strings) {
        this.context.reloadConfig();
        commandSender.sendMessage("配置文件加载成功");
    }
}
