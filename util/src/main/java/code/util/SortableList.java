/**
    */
package code.util;
import code.util.annot.CapacityInit;
import code.util.comparators.NaturalComparator;
import code.util.ints.Equallable;
import code.util.ints.Listable;

/**Few employed class
    Not compared in tests
    @author Cardman
 */
public final class SortableList<T extends Comparable<T>> extends AbEqList<T> implements Equallable<SortableList<T>> {

    /**
    */
    public SortableList() {
    }

    /**
        @param _elements
    */
    public SortableList(T... _elements) {
        super(_elements);
    }

    /**
        @param _c
    */
    public SortableList(Listable<? extends T> _c) {
        super(_c);
    }

    @CapacityInit
    private SortableList(int _capacity) {
    	super(_capacity);
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
//                if (get(i_).compareTo(get(j_)) == 0) {
//                    removeAt(j_);
//                } else {
//                    j_++;
//                }
//            }
//            i_++;
//        }
//    }

    public void sort() {
        sortElts(new NaturalComparator<T>());
    }

    @Override
    public SortableList<T> subAbEq(int _from, int _to) {
        return sub(_from, _to);
    }

    @Override
    public boolean eq(SortableList<T> _g) {
        if (_g == null) {
            return false;
        }
        int size_ = size();
        int otherSize_ = _g.size();
        if (size_ != otherSize_) {
            return false;
        }
        for (int i = FIRST_INDEX; i < size_; i++) {
            T one_ = get(i);
            T two_ = _g.get(i);
            if (one_.compareTo(two_) != EQ_CMP) {
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
            if (_element.compareTo(e_) == EQ_CMP) {
                return i;
            }
        }
        return INDEX_NOT_FOUND_ELT;
    }

    @Override
    public SortableList<T> sub(int _from, int _to) {
        return new SortableList<T>(super.sub(_from, _to));
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
//            if (_element.compareTo(e_) == EQ_CMP) {
//                indexes_.add(i);
//            }
//        }
//        return indexes_;
//    }
}
