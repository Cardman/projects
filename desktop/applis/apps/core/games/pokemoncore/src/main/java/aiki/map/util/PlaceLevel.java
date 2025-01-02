package aiki.map.util;

public final class PlaceLevel {

    private static final String SEPARATOR = "/";

    private int place;

    private int level;

    public PlaceLevel() {
    }

    public PlaceLevel(int _place, int _level) {
        place = _place;
        level = _level;
    }

    public boolean eq(PlaceLevel _obj) {
        if (_obj.place != place) {
            return false;
        }
        return _obj.level == level;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int _place) {
        place = _place;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int _level) {
        level = _level;
    }

    public String display() {
        StringBuilder str_ = new StringBuilder();
        str_.append(place);
        str_.append(SEPARATOR);
        str_.append(level);
        return str_.toString();
    }
}
