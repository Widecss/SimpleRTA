package cn.widecss.listener;

import cn.widecss.SimpleRTAPlugin;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;

public class ListenerEntity extends BaseListener {
    public ListenerEntity(SimpleRTAPlugin context) {
        super(context);
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        // -- debug --
        if (this.context.getConfig().getBoolean("debug")) {
            if (event.getEntity().getType().equals(EntityType.ZOMBIE)) {
                this.context.getGameManager().completeGame(event.getEntity().getKiller());
            }
        } else {
            if (event.getEntity().getType().equals(EntityType.ENDER_DRAGON)) {
                this.context.getGameManager().completeGame(event.getEntity().getKiller());
            }
        }
    }
}
