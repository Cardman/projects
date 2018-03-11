package aiki.map.util;
import aiki.map.enums.Direction;
import aiki.util.Point;
import code.sml.FromAndToString;
import code.util.StringList;
import code.util.ints.Displayable;
import code.util.ints.Equallable;

public final class PlaceInterConnect implements Equallable<PlaceInterConnect>, Displayable {

    static final char SEPARATOR = ';';

    private final Point source;

    private final Direction dir;

//    public PlaceInterConnect(){
//        source = null;
//        dir = null;
//    }

    public PlaceInterConnect(Point _source, Direction _dir) {
        source = _source;
        dir = _dir;
    }

    public PlaceInterConnect(String _str) {
        StringList elements_ = StringList.splitChars(_str, SEPARATOR);
        source = new Point(elements_.first());
        dir = Direction.getDirectionByName(elements_.last());
    }

    @FromAndToString
    public static PlaceInterConnect newPlaceInterConnect(String _string) {
        return new PlaceInterConnect(_string);
    }

    @Override
    public boolean eq(PlaceInterConnect _obj) {
        if (!Point.eq(source, _obj.source)) {
            return false;
        }
        if (dir != _obj.dir) {
            return false;
        }
        return true;
    }

    public Point getSource() {
        return source;
    }

    public Direction getDir() {
        return dir;
    }

    @FromAndToString
    @Override
    public String display() {
        StringBuilder str_ = new StringBuilder(source.display());
        str_.append(SEPARATOR);
        str_.append(dir.name());
        return str_.toString();
    }
}
