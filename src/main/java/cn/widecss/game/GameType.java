package cn.widecss.game;

public enum GameType {
    COOPERATE(0), COMBAT(1);

    private final int typeID;

    GameType(int typeID) {
        this.typeID = typeID;
    }

    public int getTypeID() {
        return typeID;
    }

    public static GameType getFromID(int typeID) {
        if (!checkTypeID(typeID)) {
            throw new IllegalArgumentException("模式id不存在: " + typeID);
        }
        if (typeID == 0) {
            return COOPERATE;
        } else {
            return COMBAT;
        }
    }

    private static boolean checkTypeID(int typeID) {
        return (COOPERATE.getTypeID() == typeID) || (COMBAT.getTypeID() == typeID);
    }
}
