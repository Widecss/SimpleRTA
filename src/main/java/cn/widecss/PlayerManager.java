package cn.widecss;

import org.bukkit.entity.Player;

import java.util.List;

public class PlayerManager {
    private SimpleRTAPlugin context;

    private Player runner;

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
        return otherPlayer;
    }

    public void setOtherPlayer(List<Player> otherPlayer) {
        this.otherPlayer = otherPlayer;
    }
}
