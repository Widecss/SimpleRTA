package cn.widecss.listener;

import cn.widecss.SimpleRTAPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class BaseListener implements Listener {

    protected SimpleRTAPlugin context;

    public BaseListener(SimpleRTAPlugin context) {
        this.context = context;
    }
}
