package aiki.map;

import aiki.util.Coords;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.core.IndexConstants;

public final class Condition extends CustList<Coords> {

    public Condition() {
    }

    public Condition(Condition _coords) {
        super(_coords);
    }

    public Condition(CollCapacity _cap) {
        super(_cap);
    }

    public static Condition newList(Coords... _coords) {
        Condition co_ = new Condition(new CollCapacity(_coords.length));
        for (Coords c: _coords) {
            co_.add(c);
        }
        return co_;
    }
    public boolean exists(Condition _gymLeaders) {
        return !_gymLeaders.containsAllObj(this);
    }
    public static boolean equalsSet(Condition _list1,Condition _list2) {
        return Coords.equalsSet(_list1, _list2);
    }

    public void retainAllElements(Condition _c) {
        int i_ = size() - 1;
        while (i_ >= IndexConstants.FIRST_INDEX) {
            Coords e_ = get(i_);
            if (!_c.containsObj(e_)) {
                remove(i_);
            }
            i_--;
        }
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

    public void removeAllElements(CustList<Coords> _invalidCoords) {
        for (Coords s: _invalidCoords) {
            removeAllObj(s);
        }
    }

    public void removeAllObj(Coords _obj) {
        int index_ = size() - 1;
        while (index_ >= IndexConstants.FIRST_INDEX) {
            Coords current_ = get(index_);
            if (Coords.eq(_obj,current_)) {
                remove(index_);
            }
            index_--;
        }
    }
}
