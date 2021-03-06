package cn.widecss.game;

import cn.widecss.BukkitUtil;
import cn.widecss.SimpleRTAPlugin;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class GameManager {

    private final SimpleRTAPlugin context;

    private boolean started;

    private GameType gameType;

    private long startTime, completeTime;

    private boolean isArrivedNether, isArrivedEnd;

    public GameManager(SimpleRTAPlugin context) {
        this.context = context;
    }

    public void startCooperate() {
        BukkitUtil.sendToAllPlayer("游戏即将开始!");
        BukkitRunnable startRunnable = new BukkitRunnable() {
            @Override
            public void run() {
                GameManager.this.startTime = System.currentTimeMillis();
                GameManager.this.setStarted(true);
                BukkitUtil.setAllPlayerGameMode(GameMode.SURVIVAL);
                BukkitUtil.sendToAllPlayer("游戏开始! 尽情享受!");
            }
        };
        new BukkitRunnable() {
            @Override
            public void run() {
                for (int i = 3; i > 0; i--) {
                    BukkitUtil.sendToAllPlayer(i + "!");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ignored) {
                    }
                }
                startRunnable.runTask(GameManager.this.context);
            }
        }.runTaskLaterAsynchronously(this.context, 20);
    }

    public void startCombat() {
        BukkitUtil.sendToAllPlayer("对不起, 对抗模式暂时未完成, 请选择合作模式开始游戏.");
    }

    public void completeGame(Player player) {
        completeTime = System.currentTimeMillis();
        if (player == null) {
            BukkitUtil.sendToAllPlayer("恭喜完成挑战! 用时: " + calculateTimeConsuming(completeTime));
        } else {
            BukkitUtil.sendToAllPlayer("恭喜完成挑战! 用时: " + calculateTimeConsuming(completeTime) +
                    "\n最终击杀: " + player.getDisplayName());
        }
        BukkitUtil.setAllPlayerGameMode(GameMode.SPECTATOR);
        setStarted(false);
    }

    public void resetGame() {
        startTime = 0L;
        completeTime = 0L;

        isArrivedNether = false;
        isArrivedEnd = false;

        gameType = null;

        this.context.getPlayerManager().setRunner(null);
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
        return calculateTimeConsuming(System.currentTimeMillis());
    }

    private String calculateTimeConsuming(long currTime) {
        long time = (currTime - startTime) / 1000;
        int day = (int) ((time / (60 * 60 * 24)));
        int hour = (int) ((time / (60 * 60)) % 24);
        int minute = (int) ((time / 60) % 60);
        int second = (int) (time % 60);

        return day + " 天 " + hour + " 时 " + minute + " 分 " + second + " 秒";
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public GameType getGameType() {
        if (gameType == null) {
            gameType = GameType.COOPERATE;
        }
        return gameType;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    public long getStartTime() {
        return startTime;
    }
}
