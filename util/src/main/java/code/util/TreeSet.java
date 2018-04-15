package code.util;
import java.util.Comparator;

import code.util.annot.CapacityInit;
import code.util.annot.RwXml;
import code.util.ints.SortableSet;

public final class TreeSet<E> extends AbEqList<E> implements SortableSet<E> {

    //list cannot be null, even by reflection
//    private final transient CustList<E> list = new CustList<E>();

    @RwXml
    private final Comparator<E> comparator;

    @RwXml
    private TreeSet() {
        comparator = null;
    }

    public TreeSet(Comparator<E> _cmp) {
        comparator = _cmp;
    }

    public TreeSet(SortableSet<E> _s) {
        super(_s);
        comparator = _s.comparator();
        applyChanges();
//        for (E e: _s) {
//            add(e);
//        }
    }

    @CapacityInit
    public TreeSet(CollCapacity _capacity) {
        super(_capacity);
        comparator = null;
    }

    @Override
    public Comparator<E> comparator() {
        return comparator;
    }

    @Override
    public boolean addEl(E _e) {
        if (comparator == null) {
            int index_ = 0;
            while (true) {
                if (index_ >= size()) {
                    super.add(_e);
                    return true;
                }
                E e_ = get(index_);
                Comparable<E> c_ = (Comparable<E>) _e;
                int res_ = c_.compareTo(e_);
                if (res_ < 0) {
                    super.add(index_, _e);
                    return true;
                }
                if (res_ == 0) {
                    return false;
                }
                index_++;
            }
        }
        int index_ = 0;
        while (true) {
            if (index_ >= size()) {
                super.add(_e);
                return true;
            }
            E c_ = get(index_);
            int res_ = comparator.compare(_e, c_);
            if (res_ < 0) {
                super.add(index_, _e);
                return true;
            }
            if (res_ == 0) {
                return false;
            }
            index_++;
        }
    }

    @Override
    public E lower(E _e) {
        CustList<E> l_;
        l_ = new CustList<E>();
        if (comparator == null) {
            for (E e: this) {
                Comparable<E> c_ = (Comparable<E>) e;
                int res_ = c_.compareTo(_e);
                if (res_ >= 0) {
                    continue;
                }
                l_.add(e);
            }
            if (l_.isEmpty()) {
                return null;
            }
            return l_.last();
        }
        for (E e: this) {
            int res_ = comparator.compare(e, _e);
            if (res_ >= 0) {
                continue;
            }
            l_.add(e);
        }
        if (l_.isEmpty()) {
            return null;
        }
        return l_.last();
    }

    @Override
    public E floor(E _e) {
        CustList<E> l_;
        l_ = new CustList<E>();
        if (comparator == null) {
            for (E e: this) {
                Comparable<E> c_ = (Comparable<E>) e;
                int res_ = c_.compareTo(_e);
                if (res_ > 0) {
                    continue;
                }
                l_.add(e);
            }
            if (l_.isEmpty()) {
                return null;
            }
            return l_.last();
        }
        for (E e: this) {
            int res_ = comparator.compare(e, _e);
            if (res_ > 0) {
                continue;
            }
            l_.add(e);
        }
        if (l_.isEmpty()) {
            return null;
        }
        return l_.last();
    }

    @Override
    public E ceiling(E _e) {
        if (comparator == null) {
            for (E e: this) {
                Comparable<E> c_ = (Comparable<E>) e;
                int res_ = c_.compareTo(_e);
                if (res_ >= 0) {
                    return e;
                }
            }
            return null;
        }
        for (E e: this) {
            int res_ = comparator.compare(e, _e);
            if (res_ >= 0) {
                return e;
            }
        }
        return null;
    }

    @Override
    public E higher(E _e) {
        if (comparator == null) {
            for (E e: this) {
                Comparable<E> c_ = (Comparable<E>) e;
                int res_ = c_.compareTo(_e);
                if (res_ > 0) {
                    return e;
                }
            }
            return null;
        }
        for (E e: this) {
            int res_ = comparator.compare(e, _e);
            if (res_ > 0) {
                return e;
            }
        }
        return null;
    }

    @Override
    public void applyChanges() {
        sortElts(comparator);
    }
    @Override
    public TreeSet<E> subAbEq(int _from, int _to) {
        return sub(_from, _to);
    }

    @Override
    public TreeSet<E> sub(int _from, int _to) {
        if (_from > _to) {
            return new TreeSet<E>(comparator);
        }
        TreeSet<E> tr_ = new TreeSet<E>(comparator);
        for (E e: super.sub(_from, _to)) {
            tr_.add(e);
        }
        return tr_;
    }
//    @Override
//    public Numbers<Integer> indexesOfObj(E _element) {
//        if (_element == null) {
//            return indexesOfNull();
//        }
//        Numbers<Integer> indexes_;
//        indexes_ = new Numbers<Integer>();
//        int s_ = size();
//        if (comparator == null) {
//            for (int i = FIRST_INDEX; i < s_; i++) {
//                E e_ = get(i);
//                if (e_ == null) {
//                    continue;
//                }
//                Comparable<E> c_ = (Comparable<E>) _element;
//                int res_ = c_.compareTo(e_);
//                if (res_ == EQ_CMP) {
//                    indexes_.add(i);
//                }
//            }
//        } else {
//            for (int i = FIRST_INDEX; i < s_; i++) {
//                E e_ = get(i);
//                if (e_ == null) {
//                    continue;
//                }
//                int res_ = comparator.compare(_element, e_);
//                if (res_ == EQ_CMP) {
//                    indexes_.add(i);
//                }
//            }
//        }
//         return indexes_;
//    }

    @Override
    public int indexOfObj(E _element, int _from) {
        if (_element == null) {
            return indexOfNull(_from);
        }
        int s_ = size();
        if (comparator == null) {
            for (int i = _from; i < s_; i++) {
                E e_ = get(i);
                if (e_ == null) {
                    continue;
                }
                Comparable<E> c_ = (Comparable<E>) _element;
                int res_ = c_.compareTo(e_);
                if (res_ == EQ_CMP) {
                    return i;
                }
            }
            return INDEX_NOT_FOUND_ELT;
        }
        for (int i = _from; i < s_; i++) {
            E e_ = get(i);
            if (e_ == null) {
                continue;
            }
            int res_ = comparator.compare(_element, e_);
            if (res_ == EQ_CMP) {
                return i;
            }
        }
        return INDEX_NOT_FOUND_ELT;
    }

//    @Override
//    public int indexOfObj(E _element) {
//        if (comparator == null) {
//            int index_ = 0;
//            while (true) {
//                if (index_ >= size()) {
//                    return INDEX_NOT_FOUND_ELT;
//                }
//                E e_ = get(index_);
//                Comparable<E> c_ = (Comparable<E>) _element;
//                int res_ = c_.compareTo(e_);
//                if (res_ < 0) {
//                    return INDEX_NOT_FOUND_ELT;
//                }
//                if (res_ == 0) {
//                    return index_;
//                }
//                index_++;
//            }
//        }
//        int index_ = 0;
//        while (true) {
//            if (index_ >= size()) {
//                return INDEX_NOT_FOUND_ELT;
//            }
//            E c_ = get(index_);
//            int res_ = comparator.compare(_element, c_);
//            if (res_ < 0) {
//                return INDEX_NOT_FOUND_ELT;
//            }
//            if (res_ == 0) {
//                return index_;
//            }
//            index_++;
//        }
//    }
//    @Override
//    public Iterator<E> iterator() {
//        return list.iterator();
//    }
}
