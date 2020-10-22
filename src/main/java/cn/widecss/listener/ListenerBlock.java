package cn.widecss.listener;

import cn.widecss.SimpleRTAPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;

public class ListenerBlock extends BaseListener {
    public ListenerBlock(SimpleRTAPlugin context) {
        super(context);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (this.context.getGameManager().isStarted()) {
            player.sendMessage("游戏还未开始, 请不要破坏方块...");
            event.setCancelled(true);
            event.setDropItems(false);
        }
    }
}
