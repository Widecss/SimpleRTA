package cn.widecss;

public enum GameType {
    COOPERATE(0), COMBAT(1);

    private final int typeID;

    GameType(int typeID) {
        this.typeID = typeID;
    }

    public int getTypeID() {
        return typeID;
    }
}
