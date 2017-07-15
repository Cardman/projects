/**
    */
package code.util;
import code.util.comparators.NatComparator;
import code.util.ints.Cmp;
import code.util.ints.Equallable;
import code.util.ints.Listable;

/** Not compared in tests
    @author Cardman
 */
public final class SortableCustList<T extends Cmp<T>> extends AbEqList<T> implements Equallable<SortableCustList<T>> {

    /**
    */
    public SortableCustList() {
    }

    /**
        @param _elements
    */
    public SortableCustList(T... _elements) {
        super(_elements);
    }

    /**
        @param _c
    */
    public SortableCustList(Listable<? extends T> _c) {
        super(_c);
    }

//    public void removeComparableDuplicates() {
//        //setModified();
//        int i_ = FIRST_INDEX;
//        while (true) {
//            if(i_ >= size()) {
//                break;
//            }
//            int j_ = i_;
//            j_++;
//            while (true) {
//                if (j_ >= size()) {
//                    break;
//                }
////                if (get(i_).cmp(get(j_)) == 0)
//                if (get(i_) == null) {
//                    if (get(j_) == null) {
//                        removeAt(j_);
//                    } else {
//                        j_++;
//                    }
//                } else if (get(j_) == null) {
//                    j_++;
//                } else if (get(i_).eq(get(j_))) {
//                    removeAt(j_);
//                } else {
//                    j_++;
//                }
//            }
//            i_++;
//        }
//    }

    public void sort() {
//        int len_ = size();
//        for (int i = FIRST_INDEX; i <len_; i++) {
//            for (int j = i + 1; j <len_; j++) {
//                T one_ = get(i);
//                T two_ = get(j);
//                int res_ = one_.cmp(two_);
//                if (res_ > NO_SWAP_SORT) {
//                    set(i, two_);
//                    set(j, one_);
//                }
//            }
//        }
        sortElts(new NatComparator<T>());
//        Collections.sort(this, null);
    }
    @Override
    public SortableCustList<T> subAbEq(int _from, int _to) {
        return sub(_from, _to);
    }

    @Override
    public SortableCustList<T> sub(int _from, int _to) {
        if (_from > _to) {
            return new SortableCustList<T>();
        }
        return new SortableCustList<T>(super.sub(_from, _to));
    }
    @Override
    public boolean eq(SortableCustList<T> _b) {
        if (_b == null) {
            return false;
        }
        int len_ = size();
        if (_b.size() != len_) {
            return false;
        }
        for (int i = FIRST_INDEX; i < len_; i++) {
            T e_ = get(i);
            T f_ = _b.get(i);
            if (e_ == null) {
                if (f_ != null) {
                    return false;
                }
                continue;
            }
            if (f_ == null) {
                return false;
            }
            if (!e_.eq(f_)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int indexOfObj(T _element, int _from) {
        if (_element == null) {
            return indexOfNull(_from);
        }
        int s_ = size();
        for (int i = _from; i < s_; i++) {
            T e_ = get(i);
            if (e_ == null) {
                continue;
            }
            if (_element.eq(e_)) {
                return i;
            }
        }
        return INDEX_NOT_FOUND_ELT;
    }

//    @Override
//    public Numbers<Integer> indexesOfObj(T _element) {
//        if (_element == null) {
//            return indexesOfNull();
//        }
//        Numbers<Integer> indexes_;
//        indexes_ = new Numbers<Integer>();
//        int s_ = size();
//        for (int i = FIRST_INDEX; i < s_; i++) {
//            T e_ = get(i);
//            if (e_ == null) {
//                continue;
//            }
//            if (_element.eq(e_)) {
//                indexes_.add(i);
//            }
//        }
//        return indexes_;
//    }
//    @Override
//    public int indexOfObj(T _element) {
//        int len_ = size();
//        if (_element == null) {
//            for (int i = FIRST_INDEX; i < len_; i++) {
//                if (get(i) == null) {
//                    return i;
//                }
//            }
//            return INDEX_NOT_FOUND_ELT;
//        }
//        for (int i = FIRST_INDEX; i < len_; i++) {
//            T elt_ = get(i);
//            if (elt_ == null) {
//                continue;
//            }
//            if (_element.cmp(elt_) == EQ_CMP) {
//                return i;
//            }
//        }
//        return INDEX_NOT_FOUND_ELT;
//    }
}
