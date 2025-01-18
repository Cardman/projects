package aiki.map.enums;
import aiki.db.*;
import code.util.CustList;
import code.util.core.StringUtil;


public enum Direction {
    UP(0,-1, DataBase.DEF_DIR_UP),DOWN(0,1, DataBase.DEF_DIR_DOWN),LEFT(-1,0, DataBase.DEF_DIR_LEFT),RIGHT(1,0, DataBase.DEF_DIR_RIGHT);
    private final int xCoords;
    private final int yCoords;
    private final String dirName;
    Direction(int _x, int _y, String _d) {
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

    public int getx() {
        return xCoords;
    }
    public int gety() {
        return yCoords;
    }
}
