package cn.widecss.listener;

import cn.widecss.SimpleRTAPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinServer extends BaseListener {
    private SimpleRTAPlugin context;

    public PlayerJoinServer(SimpleRTAPlugin context) {
        super(context);
    }

    @EventHandler
    public void onPlayerJoinServer(PlayerJoinEvent event) {

    }
}
