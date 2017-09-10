package aiki.map;
import aiki.util.Coords;
import code.util.AbEqList;
import code.util.EqList;
import code.util.ints.Equallable;
import code.util.ints.Listable;

public final class Condition extends AbEqList<Coords> implements Equallable<Condition> {

    public Condition() {
    }

    public Condition(Coords _coords) {
        super(_coords);
    }

    Condition(Listable<Coords> _coords) {
        super(_coords);
    }

    public boolean exists(EqList<Coords> _gymLeaders) {
        return !_gymLeaders.containsAllObj(this);
    }
    public static boolean equalsSet(Condition _list1,Condition _list2) {
        return Coords.equalsSet(_list1, _list2);
    }

    @Override
    public boolean eq(Condition _g) {
        return equalsSet(this, _g);
    }

    @Override
    public int indexOfObj(Coords _element, int _from) {
        if (_element == null) {
            return indexOfNull(_from);
        }
        int s_ = size();
        for (int i = _from; i < s_; i++) {
            Coords e_ = get(i);
            if (e_ == null) {
                continue;
            }
            if (_element.eq(e_)) {
                return i;
            }
        }
        return INDEX_NOT_FOUND_ELT;
    }

    @Override
    public Condition subAbEq(int _from, int _to) {
        return sub(_from, _to);
    }

    @Override
    public Condition sub(int _from, int _to) {
        if (_from > _to) {
            return new Condition();
        }
        return new Condition(super.sub(_from, _to));
    }
//    @Override
//    public Numbers<Integer> indexesOfObj(Coords _element) {
//        if (_element == null) {
//            return indexesOfNull();
//        }
//        Numbers<Integer> indexes_;
//        indexes_ = new Numbers<Integer>();
//        int s_ = size();
//        for (int i = FIRST_INDEX; i < s_; i++) {
//            Coords e_ = get(i);
//            if (e_ == null) {
//                continue;
//            }
//            if (_element.eq(e_)) {
//                indexes_.add(i);
//            }
//        }
//        return indexes_;
//    }
}
