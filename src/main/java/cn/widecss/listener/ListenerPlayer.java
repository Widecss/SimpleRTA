package cn.widecss.listener;

import cn.widecss.ItemFactory;
import cn.widecss.PlayerManager;
import cn.widecss.SimpleRTAPlugin;
import cn.widecss.game.GameType;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ListenerPlayer extends BaseListener {

    public ListenerPlayer(SimpleRTAPlugin context) {
        super(context);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (this.context.getGameManager().isStarted()) {
            if (this.context.getGameManager().getGameType() == GameType.COMBAT) {
                PlayerManager manager = this.context.getPlayerManager();
                if (manager.getRunner().getName().equals(player.getName())) {
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
            }
        } else {
            if (GameMode.ADVENTURE.equals(player.getGameMode())) {
                event.setCancelled(true);
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
        player.getInventory().clear();

        // -- debug --
        if (this.context.getConfig().getBoolean("debug")) {
            player.getInventory().addItem(ItemFactory.getNiceDiamondSword());
            player.getInventory().addItem(ItemFactory.getASetOfZombieSpawnEgg());
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        this.context.getPlayerManager().getOtherPlayer().remove(event.getPlayer());
    }
}
