package cn.widecss.game;

public enum GameState {
    PREPARATION(0, "准备"),
    START(1, "开始"),
    FINAL(2, "结束");

    private final int typeID;

    private final String typeName;

    GameState(int typeID, String typeName) {
        this.typeID = typeID;
        this.typeName = typeName;
    }

    public int getTypeID() {
        return typeID;
    }

    public String getTypeName() {
        return typeName;
    }

    public static GameState getFromID(int typeID) {
        checkTypeID(typeID);
        if (typeID == 0) {
            return PREPARATION;
        } else if (typeID == 1) {
            return START;
        } else {
            return FINAL;
        }
    }

    private static void checkTypeID(int typeID) {
        if ((PREPARATION.getTypeID() == typeID)
                || (START.getTypeID() == typeID)
                || (FINAL.getTypeID() == typeID)) {
            return;
        }
        throw new IllegalArgumentException("模式id不存在: " + typeID);
    }
}
