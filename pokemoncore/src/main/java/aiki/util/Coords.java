package aiki.util;
import code.util.CustList;
import code.util.Numbers;
import code.util.StringList;
import code.util.ints.Displayable;
import code.util.ints.Equallable;
import code.util.ints.Listable;

public final class Coords implements Equallable<Coords>, Displayable {

    static final char SEPARATOR = ';';
    static final String INVALID = "";

    private static final byte INVALID_NUMBER = CustList.INDEX_NOT_FOUND_ELT - 1;

    private short numberPlace;

    private Point insideBuilding;

    private LevelPoint level;

    public Coords() {
        numberPlace = INVALID_NUMBER;
        level = new LevelPoint();
    }

    public Coords(Coords _coords) {
        numberPlace = _coords.numberPlace;
        level = new LevelPoint(_coords.level);
        if (_coords.insideBuilding != null) {
            insideBuilding = new Point(_coords.insideBuilding);
        }
    }

    public Coords(String _string) {
        if (StringList.quickEq(_string, INVALID)) {
            numberPlace = INVALID_NUMBER;
            return;
        }
        StringList elements_ = StringList.splitChars(_string,SEPARATOR);
        numberPlace = Short.parseShort(elements_.first());
        if (_string.indexOf(SEPARATOR) == _string.lastIndexOf(SEPARATOR)) {
            insideBuilding = null;
        } else {
            insideBuilding = new Point(elements_.get(CustList.SECOND_INDEX));
        }
        level = new LevelPoint(elements_.last());
    }

    public static boolean equalsSet(Listable<Coords> _list1,Listable<Coords> _list2) {
        for (Coords c: _list2) {
            boolean contains_ = false;
            for (Coords d: _list1) {
                if (Coords.eq(c, d)) {
                    contains_ = true;
                    break;
                }
            }
            if (!contains_) {
                return false;
            }
        }
        for (Coords c: _list1) {
            boolean contains_ = false;
            for (Coords d: _list2) {
                if (Coords.eq(c, d)) {
                    contains_ = true;
                    break;
                }
            }
            if (!contains_) {
                return false;
            }
        }
        return true;
    }
    
    public static Coords newCoords(String _string) {
        return new Coords(_string);
    }

    public void outside() {
        affectInside(null);
    }

    public void affectInside(Point _pt) {
        if (_pt == null) {
            insideBuilding = null;
        } else {
            insideBuilding = new Point(_pt);
        }
    }

    public void affect(Coords _coords) {
        numberPlace = _coords.numberPlace;
        level.affect(_coords.level);
        affectInside(_coords.insideBuilding);
    }

    public boolean isInside() {
        return insideBuilding != null;
    }

    public boolean isValid() {
        return !Numbers.eq(numberPlace, INVALID_NUMBER);
    }

    public static boolean eq(Coords _c1,Coords _c2) {
        return _c1.eq(_c2);
    }

    @Override
    public boolean eq(Coords _g) {
        if (!Numbers.eq(numberPlace,_g.numberPlace)) {
            return false;
        }
        if (isInside() && _g.isInside()) {
            if (!Point.eq(insideBuilding,_g.insideBuilding)) {
                return false;
            }
        } else if (!isInside()) {
            if (_g.isInside()) {
                return false;
            }
        } else {
            return false;
        }
        if (!LevelPoint.eq(level,_g.level)) {
            return false;
        }
        return true;
    }

    public short getNumberPlace() {
        return numberPlace;
    }

    public void setNumberPlace(short _numberPlace) {
        numberPlace = _numberPlace;
    }

    public Point getInsideBuilding() {
        return insideBuilding;
    }

    public void setInsideBuilding(Point _insideBuilding) {
        insideBuilding = _insideBuilding;
    }

    public LevelPoint getLevel() {
        return level;
    }

    public void setLevel(LevelPoint _level) {
        level = _level;
    }

    @Override
    
    public String display() {
        if (!isValid()) {
            return INVALID;
        }
        String validLevel_ = level.display();
        StringBuilder str_ = new StringBuilder();
        str_.append(numberPlace);
        str_.append(SEPARATOR);
        if (isInside()) {
            str_.append(insideBuilding.display());
            str_.append(SEPARATOR);
        }
        str_.append(validLevel_);
        return str_.toString();
    }
}
