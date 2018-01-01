package aiki.map.util;
import code.serialize.CheckedData;
import code.sml.FromAndToString;
import code.util.Numbers;
import code.util.StringList;
import code.util.ints.Displayable;
import code.util.ints.Equallable;

@CheckedData
public final class MiniMapCoords implements Equallable<MiniMapCoords>, Displayable {

    private static final char SEPARATOR = ' ';

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
        return display();
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
