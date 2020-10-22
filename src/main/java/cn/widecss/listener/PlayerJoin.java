package cn.widecss.listener;

import cn.widecss.SimpleRTAPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin extends BaseListener {
    private SimpleRTAPlugin context;

    public PlayerJoin(SimpleRTAPlugin context) {
        super(context);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

    }
}
