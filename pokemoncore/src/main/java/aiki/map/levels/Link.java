package aiki.map.levels;
import aiki.DataBase;
import aiki.exceptions.DataException;
import aiki.map.enums.Direction;
import aiki.util.Coords;
import code.serialize.CheckedData;
import code.util.CustList;
import code.util.StringList;
import code.xml.FromAndToString;

@CheckedData
public final class Link {

    private static final char SEPARATOR = '\'';

    private String name;

    private Coords coords;

    private Direction dir;

    public Link() {

    }
    public Link(String _string) {
        StringList list_ = StringList.splitChars(_string, SEPARATOR);
        name = list_.first();
        if (_string.indexOf(SEPARATOR) != _string.lastIndexOf(SEPARATOR)) {
            dir = Direction.valueOf(list_.get(CustList.SECOND_INDEX));
        }
        coords = new Coords(list_.last());
    }

    public Link(String _name, Coords _coords) {
        setFileName(_name);
        setCoords(_coords);
    }

    @FromAndToString
    public static Link newLink(String _string) {
        return new Link(_string);
    }

    @Override
    @FromAndToString
    public String toString() {
        if (isValidDir()) {
            return name+SEPARATOR+dir.name()+SEPARATOR+coords;
        }
        return name+SEPARATOR+coords;
    }

    public boolean isValid(DataBase _data) {
        return !_data.getLink(getFileName()).isEmpty();
    }
    public void validateForEditing(DataBase _data) {
        if (_data.getLink(getFileName()).isEmpty()) {
            throw new DataException();
        }
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
}
