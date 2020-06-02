package aiki.game;
import aiki.util.Coords;
import code.util.*;
import code.util.StringList;
import code.util.ints.Displayable;
import code.util.ints.Equallable;
import code.util.ints.Listable;

public final class NbFightCoords implements Equallable<NbFightCoords>, Displayable {

    private static final char SEPARATOR = '\'';

    private final Coords coords;

    private final int nbFight;

    public NbFightCoords(String _string) {
        StringList elements_ = StringList.splitChars(_string, SEPARATOR);
        coords = new Coords(elements_.first());
        nbFight = Numbers.parseInt(elements_.last());
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

    @Override
    public boolean eq(NbFightCoords _obj) {
        //getCoords() != null
        if (!Coords.eq(getCoords(), _obj.getCoords())) {
            return false;
        }
        if (!Numbers.eq(getNbFight(), _obj.getNbFight())) {
            return false;
        }
        return true;
    }

    public Coords getCoords() {
        return coords;
    }

    public int getNbFight() {
        return nbFight;
    }

    
    @Override
    public String display() {
        StringBuilder str_ = new StringBuilder(coords.display());
        str_.append(SEPARATOR);
        str_.append(nbFight);
        return str_.toString();
    }
}
