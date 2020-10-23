package cn.widecss.game;

public enum GameType {
    COOPERATE(0, "合作"),
    COMBAT(1, "对抗");

    private final int typeID;

    private final String typeName;

    GameType(int typeID, String typeName) {
        this.typeID = typeID;
        this.typeName = typeName;
    }

    public int getTypeID() {
        return typeID;
    }

    public String getTypeName() {
        return typeName;
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
