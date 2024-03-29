package aiki.map.util;
import aiki.map.enums.Direction;
import aiki.util.Point;
import code.util.StringList;
import code.util.core.StringUtil;
import code.util.ints.Displayable;

public final class PlaceInterConnect implements Displayable {

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
        StringList elements_ = StringUtil.splitChars(_str, SEPARATOR);
        source = new Point(elements_.first());
        dir = Direction.getDirectionByName(elements_.last());
    }

    
    public static PlaceInterConnect newPlaceInterConnect(String _string) {
        return new PlaceInterConnect(_string);
    }

    public boolean eq(PlaceInterConnect _obj) {
        if (!Point.eq(source, _obj.source)) {
            return false;
        }
        return dir == _obj.dir;
    }

    public Point getSource() {
        return source;
    }

    public Direction getDir() {
        return dir;
    }

    
    @Override
    public String display() {
        StringBuilder str_ = new StringBuilder(source.display());
        str_.append(SEPARATOR);
        str_.append(dir.getDirName());
        return str_.toString();
    }
}
