package code.util;
import code.util.annot.CapacityInit;
import code.util.ints.Equallable;
import code.util.ints.Listable;

public final class BooleanList extends AbEqList<Boolean> implements Equallable<BooleanList> {

    public BooleanList() {
    }

    public BooleanList(Boolean... _elements) {
        super(_elements);
    }
    public BooleanList(Listable<? extends Boolean> _c) {
        super(_c);
    }

    @CapacityInit
    public BooleanList(CollCapacity _capacity) {
        super(_capacity);
    }

//    public BooleanList(Iterable<? extends Boolean> _c) {
//        super(_c);
//    }

    public boolean containsBool(boolean _char) {
        return containsObj(_char);
    }

    @Override
    public int indexOfObj(Boolean _element, int _from) {
        if (_element == null) {
            return indexOfNull(_from);
        }
        int s_ = size();
        for (int i = _from; i < s_; i++) {
            Boolean e_ = get(i);
            if (e_ == _element) {
                return i;
            }
        }
        return INDEX_NOT_FOUND_ELT;
    }

//    public Numbers<Integer> indexesOfObj(Boolean _element) {
//        if (_element == null) {
//            return indexesOfNull();
//        }
//        Numbers<Integer> indexes_;
//        indexes_ = new Numbers<Integer>();
//        int s_ = size();
//        for (int i = FIRST_INDEX; i < s_; i++) {
//            Boolean e_ = get(i);
//            if (e_ == null) {
//                continue;
//            }
//            if (e_ == _element) {
//                indexes_.add(i);
//            }
//        }
//        return indexes_;
//    }
    @Override
    public boolean eq(BooleanList _g) {
        if (_g == null) {
            return false;
        }
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

    @Override
    public BooleanList subAbEq(int _from, int _to) {
        return sub(_from, _to);
    }

    @Override
    public BooleanList sub(int _from, int _to) {
        if (_from > _to) {
            return new BooleanList();
        }
        return new BooleanList(super.sub(_from, _to));
    }
}
