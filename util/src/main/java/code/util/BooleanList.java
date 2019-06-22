package code.util;
import code.util.ints.Equallable;
import code.util.ints.Listable;

public final class BooleanList extends CustList<Boolean> implements Equallable<BooleanList> {

    public BooleanList() {
    }

    public BooleanList(Boolean... _elements) {
        super(_elements);
    }
    public BooleanList(Listable<Boolean> _c) {
        super(_c);
    }

    
    public BooleanList(CollCapacity _capacity) {
        super(_capacity);
    }

    public int indexOfObj(Boolean _element, int _from) {
        int s_ = size();
        for (int i = _from; i < s_; i++) {
            Boolean e_ = get(i);
            if (e_ == _element) {
                return i;
            }
        }
        return INDEX_NOT_FOUND_ELT;
    }

    @Override
    public boolean eq(BooleanList _g) {
        int len_ = size();
        if (_g.size() != len_) {
            return false;
        }
        for (int i = FIRST_INDEX; i < len_; i++) {
            Boolean e_ = get(i);
            Boolean f_ = _g.get(i);
            if (e_ != f_) {
                return false;
            }
        }
        return true;
    }

    public Ints indexesOfObj(boolean _element) {
        Ints indexes_;
        indexes_ = new Ints();
        int i_ = FIRST_INDEX;
        while (true) {
            int found_ = indexOfObj(_element, i_);
            if (found_ == INDEX_NOT_FOUND_ELT) {
                break;
            }
            indexes_.add(found_);
            i_ = found_ + 1;
        }
        return indexes_;
    }

    public boolean containsObj(boolean _b) {
        return indexOfObj(_b, FIRST_INDEX) > -1;
    }
}
