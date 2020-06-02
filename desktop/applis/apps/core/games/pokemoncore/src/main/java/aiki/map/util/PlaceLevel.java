package aiki.map.util;
import code.util.ints.Displayable;
import code.util.ints.Equallable;

public final class PlaceLevel implements Equallable<PlaceLevel>, Displayable {

    private static final String SEPARATOR = "/";

    private short place;

    private byte level;

    public PlaceLevel() {
    }

    public PlaceLevel(short _place, byte _level) {
        place = _place;
        level = _level;
    }

    @Override
    public boolean eq(PlaceLevel _obj) {
        if (_obj.place != place) {
            return false;
        }
        if (_obj.level != level) {
            return false;
        }
        return true;
    }

    public short getPlace() {
        return place;
    }

    public void setPlace(short _place) {
        place = _place;
    }

    public byte getLevel() {
        return level;
    }

    public void setLevel(byte _level) {
        level = _level;
    }

    @Override
    public String display() {
        StringBuilder str_ = new StringBuilder();
        str_.append(place);
        str_.append(SEPARATOR);
        str_.append(level);
        return str_.toString();
    }
}
