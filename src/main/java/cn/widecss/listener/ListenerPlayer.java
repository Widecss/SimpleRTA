package cn.widecss.listener;

import cn.widecss.ItemFactory;
import cn.widecss.PlayerManager;
import cn.widecss.SimpleRTAPlugin;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.PlayerInventory;

public class ListenerPlayer extends BaseListener {

    public ListenerPlayer(SimpleRTAPlugin context) {
        super(context);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (this.context.getGameManager().isStarted()) {
            PlayerManager manager = this.context.getPlayerManager();
            if (manager.getRunner().getUniqueId().equals(player.getUniqueId())) {
                switch (player.getWorld().getEnvironment()) {
                    case NORMAL: {
                        manager.setOverLocation(event.getTo());
                        break;
                    }
                    case NETHER: {
                        manager.setNetherLocation(event.getTo());
                        break;
                    }
                    case THE_END: {
                        manager.setEndLocation(event.getTo());
                        break;
                    }
                    default:
                }
            }
        } else {
            if (GameMode.SURVIVAL.equals(player.getGameMode())) {
                event.setCancelled(true);
                player.sendMessage("游戏还未开始, 请不要移动...");
            }
        }
    }

    @EventHandler
    public void onPlayerChangedWorld(PlayerChangedWorldEvent event) {
        switch (event.getFrom().getEnvironment()) {
            case NETHER: {
                this.context.getGameManager().arrivedNether(event.getPlayer());
                break;
            }
            case THE_END: {
                this.context.getGameManager().arrivedEnd(event.getPlayer());
                break;
            }
            default:
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        this.context.getPlayerManager().getOtherPlayer().add(player);
        player.setGameMode(GameMode.ADVENTURE);

        // -- debug --
        if (this.context.getConfig().getBoolean("debug")) {
            PlayerInventory inventory = player.getInventory();
            inventory.addItem(ItemFactory.getNiceDiamondSword());
            inventory.addItem(ItemFactory.getASetOfZombieSpawnEgg());
        }
    }
}
