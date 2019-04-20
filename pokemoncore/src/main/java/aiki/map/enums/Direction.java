package aiki.map.enums;
import code.util.StringList;


public enum Direction {
    UP((byte)0,(byte)-1),DOWN((byte)0,(byte)1),LEFT((byte)-1,(byte)0),RIGHT((byte)1,(byte)0);
    private final byte xCoords;
    private final byte yCoords;
    Direction(byte _x,byte _y) {
        xCoords = _x;
        yCoords = _y;
    }
    public Direction getOpposite() {
        if (this == UP) {
            return DOWN;
        }
        if (this == DOWN) {
            return UP;
        }
        if (this == LEFT) {
            return RIGHT;
        }
        return LEFT;
    }
    public static Direction getDirectionByName(String _env) {
        for (Direction e: values()) {
            if (StringList.quickEq(e.name(), _env)) {
                return e;
            }
        }
        return UP;
    }
    public byte getx() {
        return xCoords;
    }
    public byte gety() {
        return yCoords;
    }
}
