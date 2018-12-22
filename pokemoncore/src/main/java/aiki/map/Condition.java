package aiki.map;
import aiki.util.Coords;
import code.util.CustList;
import code.util.EqList;
import code.util.ints.Equallable;
import code.util.ints.Listable;

public final class Condition extends CustList<Coords> implements Equallable<Condition> {

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


    public void removeDuplicates()  {
        int i_ = FIRST_INDEX;
        while (true) {
            if(i_ >= size()) {
                break;
            }
            Coords e_ = get(i_);
            boolean rem_ = false;
            int next_ = indexOfObj(e_, i_ + 1);
            while (next_ != INDEX_NOT_FOUND_ELT) {
                removeAt(next_);
                rem_ = true;
                next_ = indexOfObj(e_, next_ + 1);
            }
            if (!rem_) {
                i_++;
            }
        }
    }

    public int indexOfObj(Coords _element, int _from) {
        int s_ = size();
        for (int i = _from; i < s_; i++) {
            Coords e_ = get(i);
            if (_element.eq(e_)) {
                return i;
            }
        }
        return INDEX_NOT_FOUND_ELT;
    }

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

    public boolean containsObj(Coords _coords) {
        return indexOfObj(_coords, FIRST_INDEX) > -1;
    }

    public boolean containsAllObj(Condition _val) {
        for (Coords e: _val) {
            if (!containsObj(e)) {
                return false;
            }
        }
        return true;
    }
}
