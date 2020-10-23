package cn.widecss;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerManager {

    private final SimpleRTAPlugin context;

    private Player runner;

    private Location overLocation, netherLocation, endLocation;

    private List<Player> otherPlayer;

    public PlayerManager(SimpleRTAPlugin context) {
        this.context = context;
    }

    public void setRunner(Player runner) {
        this.runner = runner;
    }

    public Player getRunner() {
        return runner;
    }

    public List<Player> getOtherPlayer() {
        if (otherPlayer == null) {
            otherPlayer = new ArrayList<>();
        }
        return otherPlayer;
    }

    public void setOtherPlayer(List<Player> otherPlayer) {
        this.otherPlayer = otherPlayer;
    }

    public Location getOverLocation() {
        return overLocation;
    }

    public void setOverLocation(Location overLocation) {
        this.overLocation = overLocation;
    }

    public Location getNetherLocation() {
        return netherLocation;
    }

    public void setNetherLocation(Location netherLocation) {
        this.netherLocation = netherLocation;
    }

    public Location getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(Location endLocation) {
        this.endLocation = endLocation;
    }
}
