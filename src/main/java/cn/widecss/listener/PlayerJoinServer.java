package cn.widecss.listener;

import cn.widecss.SimpleRTAPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinServer implements Listener {
    private SimpleRTAPlugin context;

    public PlayerJoinServer(SimpleRTAPlugin context) {
        this.context = context;
    }

    @EventHandler
    public void onPlayerJoinServer(PlayerJoinEvent event) {

    }
}
