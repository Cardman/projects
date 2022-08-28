package aiki.game;

import aiki.util.Coords;
import code.util.StringList;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;
import code.util.ints.Listable;

public final class NbFightCoords {

    private static final char SEPARATOR = '\'';

    private final Coords coords;

    private final int nbFight;

    public NbFightCoords(String _string) {
        StringList elements_ = StringUtil.splitChars(_string, SEPARATOR);
        coords = new Coords(elements_.first());
        nbFight = NumberUtil.parseInt(elements_.last());
    }

    public NbFightCoords(Coords _coords, int _nbFight) {
        coords = _coords;
        nbFight = _nbFight;
    }

    public static boolean equalsSet(Listable<NbFightCoords> _list1,Listable<NbFightCoords> _list2) {
        for (NbFightCoords a: _list2) {
            boolean contains_ = false;
            for (NbFightCoords b: _list1) {
                if (a.eq(b)) {
                    contains_ = true;
                    break;
                }
            }
            if (!contains_) {
                return false;
            }
        }
        return contains(_list1, _list2);
    }

    private static boolean contains(Listable<NbFightCoords> _list1, Listable<NbFightCoords> _list2) {
        for (NbFightCoords a: _list1) {
            boolean contains_ = false;
            for (NbFightCoords b: _list2) {
                if (a.eq(b)) {
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


    public static NbFightCoords newNbFightCoords(String _string) {
        return new NbFightCoords(_string);
    }

    public boolean eq(NbFightCoords _obj) {
        //getCoords() != null
        if (!Coords.eq(getCoords(), _obj.getCoords())) {
            return false;
        }
        return NumberUtil.eq(getNbFight(), _obj.getNbFight());
    }

    public Coords getCoords() {
        return coords;
    }

    public int getNbFight() {
        return nbFight;
    }

    public String display() {
        StringBuilder str_ = new StringBuilder(coords.display());
        str_.append(SEPARATOR);
        str_.append(nbFight);
        return str_.toString();
    }
}
