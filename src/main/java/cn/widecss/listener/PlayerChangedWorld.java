package cn.widecss.listener;

import cn.widecss.SimpleRTAPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class PlayerChangedWorld extends BaseListener {
    public PlayerChangedWorld(SimpleRTAPlugin context) {
        super(context);
    }

    @EventHandler
    public void onPlayerChangedWorld(PlayerChangedWorldEvent event) {

    }
}
