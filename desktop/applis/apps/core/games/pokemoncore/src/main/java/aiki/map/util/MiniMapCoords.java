package aiki.map.util;
import code.util.StringList;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;
import code.util.ints.Displayable;

public final class MiniMapCoords implements Displayable {

    private static final char SEPARATOR = ' ';

    private final int xCoords;

    private final int yCoords;

    MiniMapCoords(String _value) {
        StringList list_ = StringUtil.splitChars(_value, SEPARATOR);
        xCoords = NumberUtil.parseInt(list_.first());
        yCoords = NumberUtil.parseInt(list_.last());
    }

    public MiniMapCoords(int _x, int _y) {
        xCoords = _x;
        yCoords = _y;
    }

    
    public static MiniMapCoords newMiniMapCoords(String _string) {
        return new MiniMapCoords(_string);
    }

    public boolean eq(MiniMapCoords _obj) {
        if (!NumberUtil.eq(xCoords, _obj.xCoords)) {
            return false;
        }
        return NumberUtil.eq(yCoords, _obj.yCoords);
    }

    public int getXcoords() {
        return xCoords;
    }

    public int getYcoords() {
        return yCoords;
    }

    
    @Override
    public String display() {
        StringBuilder str_ = new StringBuilder();
        str_.append(xCoords);
        str_.append(SEPARATOR);
        str_.append(yCoords);
        return str_.toString();
    }
}
