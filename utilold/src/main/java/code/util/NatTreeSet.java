package code.util;
import java.util.Iterator;

import code.util.annot.CapacityInit;
import code.util.comparators.NatComparator;
import code.util.ints.Cmp;
import code.util.ints.Comparing;
import code.util.ints.Listable;
import code.util.ints.SortableSet;

public final class NatTreeSet<E extends Cmp<E>> extends AbEqList<E> implements SortableSet<E> {

    //list cannot be null, even by reflection
    private final SortableCustList<E> list;

    public NatTreeSet() {
        list = new SortableCustList<E>();
    }

    public NatTreeSet(SortableSet<E> _s) {
        list = new SortableCustList<E>();
        for (E e: _s) {
            add(e);
        }
    }

    public NatTreeSet(Listable<E> _c) {
        list = new SortableCustList<E>();
        list.addAllElts(_c);
    }

    @CapacityInit
    public NatTreeSet(CollCapacity _capacity) {
        super(_capacity);
        list = new SortableCustList<E>();
    }

    @Override
    public Comparing<E> comparator() {
        return new NatComparator<E>();
    }

    @Override
    public E first() {
        return list.first();
    }

    @Override
    public E last() {
        return list.last();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

//    @Override
//    public <T> T[] toArray(T[] _a) {
//        return list.toArray(_a);
//    }

    @Override
    public boolean addEl(E _e) {
        int index_ = 0;
        while (true) {
            if (index_ >= list.size()) {
                list.add(_e);
                return true;
            }
            E e_ = list.get(index_);
            int res_ = _e.cmp(e_);
            if (res_ < 0) {
                list.add(index_, _e);
                return true;
            }
            if (res_ == 0) {
                return false;
            }
            index_++;
        }
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public E lower(E _e) {
        CustList<E> l_;
        l_ = new CustList<E>();
        for (E e: list) {
            int res_ = e.cmp(_e);
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
        for (E e: list) {
            int res_ = e.cmp(_e);
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
        for (E e: list) {
            int res_ = e.cmp(_e);
            if (res_ >= 0) {
                return e;
            }
        }
        return null;
    }

    @Override
    public E higher(E _e) {
        for (E e: list) {
            int res_ = e.cmp(_e);
            if (res_ > 0) {
                return e;
            }
        }
        return null;
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    @Override
    public void applyChanges() {
        list.sort();
    }

    public void removeComparableDuplicates() {
//        list.removeComparableDuplicates();
        list.removeDuplicates();
    }

    @Override
    public void addAllElts(Listable<E> _c) {
        list.addAllElts(_c);
    }

    @Override
    public void sortElts(Comparing<E> _comp) {
        list.sortElts(_comp);
    }

    public void sort() {
        list.sort();
    }
    @Override
    public NatTreeSet<E> subAbEq(int _from, int _to) {
        return sub(_from, _to);
    }

    @Override
    public NatTreeSet<E> sub(int _from, int _to) {
        if (_from > _to) {
            return new NatTreeSet<E>();
        }
        return new NatTreeSet<E>(super.sub(_from, _to));
    }
    @Override
    public boolean isValidIndex(int _index) {
        return list.isValidIndex(_index);
    }

    @Override
    public int getLastIndex() {
        return list.getLastIndex();
    }

    @Override
    public void removeAt(Number _n) {
        list.removeAt(_n);
    }

    @Override
    public E get(int _index) {
        return list.get(_index);
    }

    @Override
    public void set(int _index, E _element) {
        list.set(_index, _element);
    }

    @Override
    public void add(int _index, E _element) {
        list.add(_index, _element);
    }

    @Override
    public void remove(int _index) {
        list.remove(_index);
    }

    @Override
    public void removeAllElements(Listable<E> _c) {
        list.removeAllElements(_c);
    }

    @Override
    public void removeAllObj(E _obj) {
        list.removeAllObj(_obj);
    }

    @Override
    public void removeObj(E _obj) {
        list.removeObj(_obj);
    }

    @Override
    public CustList<E> intersect(CustList<E> _list) {
        return list.intersect(_list);
    }

    @Override
    public void retainAllElements(AbEqList<E> _c) {
        list.retainAllElements(_c);
    }

    @Override
    public boolean containsAllObj(Listable<E> _list) {
        return list.containsAllObj(_list);
    }

    @Override
    public boolean containsObj(E _obj) {
        return list.containsObj(_obj);
    }

    @Override
    public int indexOfObj(E _element, int _from) {
        if (_element == null) {
            return indexOfNull(_from);
        }
        int s_ = size();
        for (int i = _from; i < s_; i++) {
            E e_ = get(i);
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
//    public Numbers<Integer> indexesOfObj(E _element) {
//        if (_element == null) {
//            return indexesOfNull();
//        }
//        Numbers<Integer> indexes_;
//        indexes_ = new Numbers<Integer>();
//        int s_ = size();
//        for (int i = FIRST_INDEX; i < s_; i++) {
//            E e_ = get(i);
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
//    public int indexOfObj(E _element) {
//        return list.indexOfObj(_element);
//    }

}
