package aiki.util;

import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;
import code.util.ints.Listable;

public final class Coords {

    static final char SEPARATOR = ';';
    static final String INVALID = "";

    private static final int INVALID_NUMBER = IndexConstants.INDEX_NOT_FOUND_ELT - 1;

    private int numberPlace;

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
        if (StringUtil.quickEq(_string, INVALID)) {
            numberPlace = INVALID_NUMBER;
            level = new LevelPoint();
            return;
        }
        StringList elements_ = StringUtil.splitChars(_string,SEPARATOR);
        numberPlace = NumberUtil.parseInt(elements_.first());
        if (_string.indexOf(SEPARATOR) == _string.lastIndexOf(SEPARATOR)) {
            insideBuilding = null;
        } else {
            insideBuilding = new Point(elements_.get(IndexConstants.SECOND_INDEX));
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
        return containsAll(_list1, _list2);
    }

    private static boolean containsAll(Listable<Coords> _list1, Listable<Coords> _list2) {
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
            setInsideBuilding(new Point(_pt));
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
        return !NumberUtil.eq(numberPlace, INVALID_NUMBER);
    }

    public static boolean eq(Coords _c1,Coords _c2) {
        return _c1.eq(_c2);
    }

    public boolean eq(Coords _g) {
        if (!NumberUtil.eq(numberPlace,_g.numberPlace)) {
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
        return LevelPoint.eq(level, _g.level);
    }

    public int getNumberPlace() {
        return numberPlace;
    }

    public void setNumberPlace(int _numberPlace) {
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
