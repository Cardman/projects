package aiki.map.enums;
import aiki.db.*;
import code.util.CustList;
import code.util.core.StringUtil;


public enum Direction {
    UP((byte)0,(byte)-1, DataBase.DEF_DIR_UP),DOWN((byte)0,(byte)1, DataBase.DEF_DIR_DOWN),LEFT((byte)-1,(byte)0, DataBase.DEF_DIR_LEFT),RIGHT((byte)1,(byte)0, DataBase.DEF_DIR_RIGHT);
    private final byte xCoords;
    private final byte yCoords;
    private final String dirName;
    Direction(byte _x, byte _y, String _d) {
        xCoords = _x;
        yCoords = _y;
        dirName = _d;
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
        for (Direction e: all()) {
            if (StringUtil.quickEq(e.getDirName(), _env)) {
                return e;
            }
        }
        return UP;
    }
    public static CustList<Direction> all() {
        CustList<Direction> ls_ = new CustList<Direction>();
        ls_.add(UP);
        ls_.add(DOWN);
        ls_.add(LEFT);
        ls_.add(RIGHT);
        return ls_;
    }

    public String getDirName() {
        return dirName;
    }

    public byte getx() {
        return xCoords;
    }
    public byte gety() {
        return yCoords;
    }
}
