package code.util;
import code.util.annot.CapacityInit;
import code.util.ints.Equallable;
import code.util.ints.Listable;

public final class IdList<T> extends AbEqList<T> implements Equallable<IdList<T>> {

    public IdList() {
    }

    public IdList(T... _elements) {
        super(_elements);
    }

    public IdList(Listable<? extends T> _c) {
        super(_c);
    }

    @CapacityInit
    private IdList(int _capacity) {
    	super(_capacity);
    }
//    public IdList(Iterable<? extends T> _c) {
//        super(_c);
//    }

    @Override
    public IdList<T> subAbEq(int _from, int _to) {
        return sub(_from, _to);
    }

    @Override
    public IdList<T> sub(int _from, int _to) {
        if (_from > _to) {
            return new IdList<T>();
        }
        return new IdList<T>(super.sub(_from, _to));
    }

    @Override
    public boolean eq(IdList<T> _g) {
        if (_g == null) {
            return false;
        }
        int len_ = size();
        if (_g.size() != len_) {
            return false;
        }
        for (int i = FIRST_INDEX; i < len_; i++) {
            T e_ = get(i);
            T f_ = _g.get(i);
            if (e_ != f_) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int indexOfObj(T _element, int _from) {
//        if (_element == null) {
//            return indexOfNull(_from);
//        }
        int s_ = size();
        for (int i = _from; i < s_; i++) {
            T e_ = get(i);
            if (e_ == _element) {
                return i;
            }
        }
        return INDEX_NOT_FOUND_ELT;
    }

//    @Override
//    public Numbers<Integer> indexesOfObj(T _element) {
//        Numbers<Integer> indexes_;
//        indexes_ = new Numbers<Integer>();
//        int s_ = size();
//        for (int i = FIRST_INDEX; i < s_; i++) {
//            T e_ = get(i);
//            if (_element == e_) {
//                indexes_.add(i);
//            }
//        }
//        return indexes_;
//    }
//    @Override
//    public int indexOfObj(T _element) {
//        int len_ = size();
//        for (int i = FIRST_INDEX; i < len_; i++) {
//            if (get(i) != _element) {
//                continue;
//            }
//            return i;
//        }
//        return INDEX_NOT_FOUND_ELT;
//    }

}
