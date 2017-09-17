package aiki.map.util;
import code.serialize.CheckedData;
import code.util.Numbers;
import code.util.StringList;
import code.util.ints.Equallable;
import code.xml.FromAndToString;

@CheckedData
public final class MiniMapCoords implements Equallable<MiniMapCoords> {

    private static final char SEPARATOR = ' ';

    private static final String EMPTY_STRING = "";

    private final short xCoords;

    private final short yCoords;

    MiniMapCoords(String _value) {
        StringList list_ = StringList.splitChars(_value, SEPARATOR);
        xCoords = Short.parseShort(list_.first());
        yCoords = Short.parseShort(list_.last());
    }

    public MiniMapCoords(short _x, short _y) {
        xCoords = _x;
        yCoords = _y;
    }

    @FromAndToString
    public static MiniMapCoords newMiniMapCoords(String _string) {
        return new MiniMapCoords(_string);
    }

    @Override
    public boolean eq(MiniMapCoords _obj) {
        if (!Numbers.eq(xCoords, _obj.xCoords)) {
            return false;
        }
        if (!Numbers.eq(yCoords, _obj.yCoords)) {
            return false;
        }
        return true;
    }

    @Override
    @FromAndToString
    public String toString() {
        return EMPTY_STRING+xCoords+SEPARATOR+yCoords;
    }

    public short getXcoords() {
        return xCoords;
    }

    public short getYcoords() {
        return yCoords;
    }
}
