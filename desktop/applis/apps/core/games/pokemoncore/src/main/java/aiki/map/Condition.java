package aiki.map;
import aiki.util.Coords;
import code.util.CustList;
import code.util.EqList;
import code.util.core.IndexConstants;

public final class Condition extends CustList<Coords> {

    public Condition() {
    }

    public Condition(Coords _coords) {
        super(_coords);
    }

    public boolean exists(EqList<Coords> _gymLeaders) {
        return !_gymLeaders.containsAllObj(this);
    }
    public static boolean equalsSet(Condition _list1,Condition _list2) {
        return Coords.equalsSet(_list1, _list2);
    }


    public void removeDuplicates()  {
        int i_ = IndexConstants.FIRST_INDEX;
        while (i_ < size()) {
            Coords e_ = get(i_);
            boolean rem_ = false;
            int next_ = indexOfObj(e_, i_ + 1);
            while (next_ != IndexConstants.INDEX_NOT_FOUND_ELT) {
                remove(next_);
                rem_ = true;
                next_ = indexOfObj(e_, i_ + 1);
            }
            if (!rem_) {
                i_++;
            }
        }
    }

    private int indexOfObj(Coords _element, int _from) {
        int s_ = size();
        for (int i = _from; i < s_; i++) {
            Coords e_ = get(i);
            if (_element.eq(e_)) {
                return i;
            }
        }
        return IndexConstants.INDEX_NOT_FOUND_ELT;
    }

    public boolean containsObj(Coords _coords) {
        return indexOfObj(_coords, IndexConstants.FIRST_INDEX) > -1;
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
