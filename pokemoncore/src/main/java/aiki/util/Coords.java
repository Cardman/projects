package aiki.util;
import code.serialize.CheckedData;
import code.sml.FromAndToString;
import code.util.CustList;
import code.util.Numbers;
import code.util.StringList;
import code.util.ints.Equallable;
import code.util.ints.Listable;

@CheckedData
public final class Coords implements Equallable<Coords> {

    static final char SEPARATOR = ';';
    static final String INVALID = "";

    private static final byte INVALID_NUMBER = CustList.INDEX_NOT_FOUND_ELT - 1;

    private static final String EMPTY_STRING = "";

    private short numberPlace;

    private Point insideBuilding;

    private LevelPoint level;

    public Coords() {
        numberPlace = INVALID_NUMBER;
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
    @FromAndToString
    public static Coords newCoords(String _string) {
        return new Coords(_string);
    }

    public void outside() {
        affectInside(null);
    }

    public void affectInside(Point _pt) {
        if (_pt == null) {
            insideBuilding = _pt;
        } else if (insideBuilding == null) {
            insideBuilding = new Point(_pt);
        } else {
            insideBuilding.affect(_pt);
        }
    }

    public void affect(Coords _coords) {
        numberPlace = _coords.numberPlace;
        if (_coords.level != null) {
            if (level != null) {
                level.affect(_coords.level);
            } else {
                level = new LevelPoint(_coords.level);
            }
        } else {
            level = null;
        }
        if (_coords.insideBuilding != null) {
            if (insideBuilding != null) {
                insideBuilding.affect(_coords.insideBuilding);
            } else {
                insideBuilding = new Point(_coords.insideBuilding);
            }
        } else {
            insideBuilding = null;
        }
    }

    public boolean isInside() {
        return insideBuilding != null;
    }

    public boolean isValid() {
        return !Numbers.eq(numberPlace, INVALID_NUMBER);
    }

    public static boolean eq(Coords _c1,Coords _c2) {
        if (_c1 == null) {
            return _c2 == null;
        }
        return _c1.eq(_c2);
    }

    @Override
    @FromAndToString
    public String toString() {
        if (!isValid()) {
            return INVALID;
        }
        String validLevel_ = level.toString();
        if (!isInside()) {
            return EMPTY_STRING+numberPlace+SEPARATOR+validLevel_;
        }
        return EMPTY_STRING+numberPlace+SEPARATOR+insideBuilding+SEPARATOR+validLevel_;
    }

    @Override
    public boolean eq(Coords _g) {
        if (!Numbers.eq(numberPlace,_g.numberPlace)) {
            return false;
        }
        if (!Point.eq(insideBuilding,_g.insideBuilding)) {
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
}
