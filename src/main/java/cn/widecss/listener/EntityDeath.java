package cn.widecss.listener;

import cn.widecss.SimpleRTAPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityDeath extends BaseListener {
    public EntityDeath(SimpleRTAPlugin context) {
        super(context);
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {

    }
}
