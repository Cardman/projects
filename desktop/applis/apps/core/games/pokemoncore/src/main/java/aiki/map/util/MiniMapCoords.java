package aiki.map.util;
import code.util.StringList;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;
import code.util.ints.Displayable;

public final class MiniMapCoords implements Displayable {

    private static final char SEPARATOR = ' ';

    private final short xCoords;

    private final short yCoords;

    MiniMapCoords(String _value) {
        StringList list_ = StringUtil.splitChars(_value, SEPARATOR);
        xCoords = (short) NumberUtil.parseInt(list_.first());
        yCoords = (short) NumberUtil.parseInt(list_.last());
    }

    public MiniMapCoords(short _x, short _y) {
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

    public short getXcoords() {
        return xCoords;
    }

    public short getYcoords() {
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
