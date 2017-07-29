package code.util;
import java.util.Comparator;

import code.util.annot.CapacityInit;
import code.util.annot.RwXml;
import code.util.exceptions.NullComparatorException;
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
        if (_cmp == null) {
            throw new NullComparatorException();
        }
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
//    public TreeSet(Collection<? extends E> _c) {
//        comparator = null;
//        addAll(_c);
//    }

//    public TreeSet(Listable<? extends E> _c, Comparator<E> _cmp) {
//        comparator = _cmp;
//        addAllElts(_c);
//    }

//    @Override
//    public String toString() {
//        return list.toString();
//    }

    @Override
    public Comparator<E> comparator() {
        return comparator;
    }

//    @Override
//    public E first() {
//        return list.first();
//    }
//
//    @Override
//    public E last() {
//        return list.last();
//    }
//
//    @Override
//    public int size() {
//        return list.size();
//    }
//
//    @Override
//    public boolean isEmpty() {
//        return list.isEmpty();
//    }

//    @Override
//    public boolean contains(Object _o) {
//        if (comparator == null) {
//            for (E e:list) {
//                Comparable<E> c_ = (Comparable<E>) _o;
//                int res_ = c_.compareTo(e);
//                if (res_ == List.EQ_CMP) {
//                    return true;
//                }
//            }
//            return false;
//        }
//        for (E e:list) {
//            int res_ = comparator.compare((E) _o, e);
//            if (res_ == List.EQ_CMP) {
//                return true;
//            }
//        }
//        return false;
//    }

//    @Override
//    public Object[] toArray() {
//        return list.toArray();
//    }

//    @Override
//    public <T> T[] toArray(T[] _a) {
//        return list.toArray(_a);
//    }

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

//    @Override
//    public boolean remove(Object _o) {
//        if (comparator == null) {
//            int index_ = 0;
//            for (E e:list) {
//                Comparable<E> c_ = (Comparable<E>) _o;
//                int res_ = c_.compareTo(e);
//                if (res_ == List.EQ_CMP) {
//                    list.removeAt(index_);
//                    return true;
//                }
//                index_++;
//            }
//            return false;
//        }
//        int index_ = 0;
//        E key_ = (E) _o;
//        for (E e:list) {
//            int res_ = comparator.compare(key_, e);
//            if (res_ == List.EQ_CMP) {
//                list.removeAt(index_);
//                return true;
//            }
//            index_++;
//        }
//        return false;
//    }
//
//    @Override
//    public boolean containsAll(Collection<?> _c) {
//        for (Object o: _c) {
//            if (!contains(o)) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    @Override
//    public boolean addAll(Collection<? extends E> _c) {
//        boolean added_ = false;
//        for (E e: _c) {
//            boolean locAdd_ = add(e);
//            if (locAdd_) {
//                added_ = true;
//            }
//        }
//        return added_;
//    }
//
//    @Override
//    public boolean retainAll(Collection<?> _c) {
//        List<E> notContained_ = new List<E>();
//        for (E e: list) {
//            boolean contained_ = false;
//            for (Object f: _c) {
//                if (comparator == null) {
//                    Comparable<E> c_ = (Comparable<E>) f;
//                    int res_ = c_.compareTo(e);
//                    if (res_ == 0) {
//                        contained_ = true;
//                        break;
//                    }
//                } else {
//                    int res_ = comparator.compare((E) f, e);
//                    if (res_ == 0) {
//                        contained_ = true;
//                        break;
//                    }
//                }
//            }
//            if (!contained_) {
//                notContained_.add(e);
//            }
//        }
//        return removeAll(notContained_);
//    }
//
//    @Override
//    public boolean removeAll(Collection<?> _c) {
//        boolean removed_ = false;
//        for (Object o: _c) {
//            boolean localRemoved_ = remove(o);
//            if (localRemoved_) {
//                removed_ = true;
//            }
//        }
//        return removed_;
//    }

//    @Override
//    public void clear() {
//        list.clear();
//    }

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
