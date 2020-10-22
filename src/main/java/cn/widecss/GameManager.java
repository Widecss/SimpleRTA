package cn.widecss;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class GameManager {

    private final SimpleRTAPlugin context;

    private boolean started;

    private GameType gameType;

    private long startTime;

    private boolean isArrivedNether, isArrivedEnd;

    public GameManager(SimpleRTAPlugin context) {
        this.context = context;
    }

    public void startGame() {
        startTime = System.currentTimeMillis();
        BukkitUtil.setAllPlayerGameMode(GameMode.SURVIVAL);
        setStarted(true);
    }

    public void completeGame() {
        BukkitUtil.setAllPlayerGameMode(GameMode.SPECTATOR);
        setStarted(false);
    }

    public void reloadGame() {
        startTime = 0L;

        isArrivedNether = false;
        isArrivedEnd = false;

        setStarted(false);
    }

    public void arrivedNether(Player player) {
        if (isArrivedNether) {
            return;
        }
        BukkitUtil.sendToAllPlayer("玩家 " + player.getDisplayName() +
                " 已成功到达地狱, 用时: " + calculateTimeConsuming());
        isArrivedNether = true;
    }

    public void arrivedEnd(Player player) {
        if (isArrivedEnd) {
            return;
        }
        BukkitUtil.sendToAllPlayer("玩家 " + player.getDisplayName() +
                " 已成功到达地狱, 用时: " + calculateTimeConsuming());
        isArrivedEnd = true;
    }

    private String calculateTimeConsuming() {
        long time = (System.currentTimeMillis() - startTime) / 1000;
        int day = (int) ((time / (60 * 60 * 24)));
        int hour = (int) ((time / (60 * 60)) % 24);
        int minute = (int) ((time / 60) % 60);
        int second = (int) (time % 60);

        return day + " 天 " + hour + " 时 " + minute + " 分 " + second + "秒";
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public GameType getGameType() {
        return gameType;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    public long getStartTime() {
        return startTime;
    }
}
