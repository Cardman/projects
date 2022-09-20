package aiki.map.levels;

import aiki.db.DataBase;
import aiki.map.enums.Direction;
import aiki.util.Coords;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;
import code.util.ints.Displayable;

public final class Link implements Displayable {

    private static final char SEPARATOR = '\'';

    private String name;

    private Coords coords;

    private Direction dir;

    public Link() {

    }

    public Link(String _string) {
        StringList list_ = StringUtil.splitChars(_string, SEPARATOR);
        name = list_.first();
        if (_string.indexOf(SEPARATOR) != _string.lastIndexOf(SEPARATOR)) {
            dir = Direction
                    .getDirectionByName(list_.get(IndexConstants.SECOND_INDEX));
        }
        coords = new Coords(list_.last());
    }

    public Link(String _name, Coords _coords) {
        setFileName(_name);
        setCoords(_coords);
    }

    
    public static Link newLink(String _string) {
        return new Link(_string);
    }

    public boolean isValid(DataBase _data) {
        return _data.getLink(getFileName()).length != 0;
    }

    public boolean isValidDir() {
        return dir != null;
    }

    public void setFileName(String _name) {
        name = _name;
    }

    public String getFileName() {
        return name;
    }

    public void setCoords(Coords _coords) {
        coords = _coords;
    }

    public Coords getCoords() {
        return coords;
    }

    public Direction getDir() {
        return dir;
    }

    public void setDir(Direction _dir) {
        dir = _dir;
    }

    
    @Override
    public String display() {
        StringBuilder str_ = new StringBuilder();
        str_.append(name);
        str_.append(SEPARATOR);
        if (isValidDir()) {
            str_.append(dir.getDirName());
            str_.append(SEPARATOR);
        }
        str_.append(coords.display());
        return str_.toString();
    }
}
