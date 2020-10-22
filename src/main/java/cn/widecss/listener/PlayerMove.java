package cn.widecss.listener;

import cn.widecss.SimpleRTAPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove extends BaseListener {
    public PlayerMove(SimpleRTAPlugin context) {
        super(context);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {

    }
}
