package cn.widecss;

import cn.widecss.inter.IPlayerExecutor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Iterator;

public class BukkitUtil {

    public static Iterator<? extends Player> getOnlinePlays() {
        Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();
        return onlinePlayers.iterator();
    }

    public static void runOnOnlinePlayer(IPlayerExecutor executor) {
        Iterator<? extends Player> playerIterator = getOnlinePlays();
        while (playerIterator.hasNext()) {
            Player player = playerIterator.next();
            executor.run(player);
        }
    }

    public static void setAllPlayerGameMode(GameMode mode) {
        runOnOnlinePlayer(player -> player.setGameMode(mode));
    }

    public static void sendToAllPlayer(String msg) {
        runOnOnlinePlayer(player -> player.sendMessage(msg));
    }

    public String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
