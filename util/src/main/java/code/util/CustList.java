package code.util;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;

import code.util.annot.CapacityInit;
import code.util.ints.Listable;

public class CustList<T> implements Listable<T> {

    public static final int SWAP_SORT = 1;

    public static final int NO_SWAP_SORT = -1;

    public static final int EQ_CMP = 0;

    public static final int INDEX_NOT_FOUND_ELT = -1;

    public static final int FIRST_INDEX = 0;

    public static final int SECOND_INDEX = 1;

    public static final int ONE_ELEMENT = 1;

    public static final int SIZE_EMPTY = 0;

    protected static final String EMPTY_STRING = "";

    private final transient ArrayList<T> list;

    public CustList() {
        list = new ArrayList<T>();
    }

    public CustList(T... _elements) {
        list = new ArrayList<T>(_elements.length);
        for (T e: _elements) {
            add(e);
        }
    }

    public CustList(Listable<? extends T> _c) {
        list = new ArrayList<T>(_c.size());
        for (T e: _c) {
            add(e);
        }
    }

    @CapacityInit
    protected CustList(int _capacity) {
        list = new ArrayList<T>(_capacity);
    }

//    public CustList(Iterable<? extends T> _c) {
//        list = new ArrayList<T>();
//        for (T e: _c) {
//            add(e);
//        }
//    }
    public static void add(Listable<?> _list, Object _o) {
        Listable<? super Object> l_ = (Listable<? super Object>) _list;
        l_.add(_o);
    }
    public static void set(Listable<?> _list, int _i, Object _o) {
        Listable<? super Object> l_ = (Listable<? super Object>) _list;
        l_.set(_i, _o);
    }
    @Override
    public void addAllElts(Listable<? extends T> _c) {
        for (T e: _c) {
            add(e);
        }
    }
//    public void addAllElts(Collection<? extends T> _c) {
//        addAll(_c);
//    }
    public void sortElts(Comparator<? super T> _comp) {
        Collections.sort(list, _comp);
//        int len_ = size();
//        for (int i = FIRST_INDEX; i <len_; i++) {
//            for (int j = i + 1; j <len_; j++) {
//                T one_ = get(i);
//                T two_ = get(j);
//                int res_ = _comp.compare(one_, two_);
//                if (res_ > EQ_CMP) {
//                    set(i, two_);
//                    set(j, one_);
//                }
//            }
//        }
//        Collections.sort(this, _comp);
    }

    @Override
    public boolean isValidIndex(int _index) {
        if (_index < FIRST_INDEX) {
            return false;
        }
        if (_index >= size()) {
            return false;
        }
        return true;
    }
    @Override
    public T first() {
        return get(FIRST_INDEX);
    }

    @Override
    public T last() {
        return get(getLastIndex());
    }
    public void setLast(T _element) {
        set(getLastIndex(), _element);
    }

    public int getLastIndex() {
        int last_ = size();
        last_--;
        return last_;
    }

    public T getPrev(int _i) {
        // TODO Auto-generated method stub
        return get(_i-1);
    }

    @Override
    public void removeAt(Number _n) {
        list.remove(_n.intValue());
    }
    @Override
    public String join(String _join) {
        if (isEmpty()) {
            return EMPTY_STRING;
        }
        StringBuilder return_ = new StringBuilder(String.valueOf(get(FIRST_INDEX)));
        int size_ = size();
        for (int i=SECOND_INDEX;i<size_;i++) {
            return_.append(_join);
            return_.append(get(i));
        }
        return return_.toString();
    }

    @Override
    public String join(char _join) {
        if (isEmpty()) {
            return EMPTY_STRING;
        }
        StringBuilder return_ = new StringBuilder(String.valueOf(get(FIRST_INDEX)));
        int size_ = size();
        for (int i=SECOND_INDEX;i<size_;i++) {
            return_.append(_join);
            return_.append(get(i));
        }
        return return_.toString();
    }
    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

//    @Override
//    public boolean contains(Object _o) {
//        return list.contains(_o);
//    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

//    @Override
//    public <E> E[] toArray(E[] _a) {
//        return list.<E>toArray(_a);
//    }

    @Override
    public void add(T _e) {
        list.add(_e);
    }

//    @Override
//    public boolean remove(Object _o) {
//        return list.remove(_o);
//    }
//
//    @Override
//    public boolean containsAll(Collection<?> _c) {
//        return list.containsAll(_c);
//    }

//    @Override
//    public boolean addAll(Collection<? extends T> _c) {
//        return list.addAll(_c);
//    }

//    @Override
    public boolean addAll(int _index, Collection<? extends T> _c) {
        return list.addAll(_index, _c);
    }
    public void retainAllElements(AbEqList<? super T> _c) {
        int i_ = FIRST_INDEX;
        while (i_ < size()) {
            T e_ = get(i_);
            if (!_c.containsObj(e_)) {
                removeAt(i_);
            } else {
                i_++;
            }
        }
    }
//    @Override
//    public boolean removeAll(Collection<?> _c) {
//        return list.removeAll(_c);
//    }
//
//    @Override
//    public boolean retainAll(Collection<?> _c) {
//        return list.retainAll(_c);
//    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public T get(int _index) {
        return list.get(_index);
    }

    @Override
    public void set(int _index, T _element) {
        list.set(_index, _element);
    }

    @Override
    public void add(int _index, T _element) {
        list.add(_index, _element);
    }

    @Override
    public void remove(int _index) {
        list.remove(_index);
    }

//    @Override
//    public int indexOf(Object _o) {
//        return list.indexOf(_o);
//    }
//
//    @Override
//    public int lastIndexOf(Object _o) {
//        return list.lastIndexOf(_o);
//    }

    @Override
    public ListIterator<T> listIterator() {
        return list.listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int _index) {
        return list.listIterator(_index);
    }

    @Override
    public CustList<T> getReverse() {
        CustList<T> list_ = new CustList<T>(this);
        int i_ = FIRST_INDEX;
        int j_ = list_.size();
        j_--;
        while (i_ < j_) {
            list_.swapIndexes(i_, j_);
            i_++;
            j_--;
        }
        return list_;
    }

    @Override
    public void swapIndexes(int _i, int _j) {
        Collections.swap(list, _i, _j);
    }

    @Override
    public void removeNull() {
        Numbers<Integer> indexes_;
        indexes_ = indexesOfNull().getReverse();
        for (int i: indexes_) {
            removeAt(i);
        }
    }

    @Override
    public boolean containsNull() {
        return !indexesOfNull().isEmpty();
    }
    
    @Override
    public int indexOfNull() {
        Numbers<Integer> indexes_;
        indexes_ = indexesOfNull();
        if (indexes_.isEmpty()) {
            return INDEX_NOT_FOUND_ELT;
        }
        return indexes_.first();
    }

    @Override
    public int indexOfNull(int _from) {
        int s_ = size();
        for (int i = _from; i < s_; i++) {
            if (get(i) == null) {
                return i;
            }
        }
        return INDEX_NOT_FOUND_ELT;
    }

    @Override
    public int lastIndexOfNull() {
        Numbers<Integer> indexes_;
        indexes_ = indexesOfNull();
        if (indexes_.isEmpty()) {
            return INDEX_NOT_FOUND_ELT;
        }
        return indexes_.last();
    }

    @Override
    public Numbers<Integer> indexesOfNull() {
        Numbers<Integer> indexes_;
        indexes_ = new Numbers<Integer>();
        int s_ = size();
        for (int i = FIRST_INDEX; i < s_; i++) {
            if (get(i) == null) {
                indexes_.add(i);
            }
        }
        return indexes_;
    }

    @Override
    public String toString() {
        return list.toString();
    }

    public CustList<? extends CustList<T>> getGroupsSameCompare(Comparator<T> _cmp) {
        CustList<T> copy_ = new CustList<T>(this);
        copy_.sortElts(_cmp);
        CustList<CustList<T>> groups_;
        groups_ = new CustList<CustList<T>>();
        CustList<T> group_;
        group_ = new CustList<T>();
        int i_ = FIRST_INDEX;
        int j_ = i_;
        j_--;
        while (i_ < copy_.size()) {
            if (i_ > FIRST_INDEX) {
                int res_ = _cmp.compare(copy_.get(i_), copy_.get(j_));
                if (res_ != EQ_CMP) {
                    groups_.add(group_);
                    group_ = new CustList<T>();
                }
            }
            group_.add(copy_.get(i_));
            i_++;
            j_++;
        }
        groups_.add(group_);
        return groups_;
    }
//
//    public static <E extends Equallable<E>> boolean eq(CustList<E> _a, CustList<E> _b) {
//        if (_a == null) {
//            return _b == null;
//        }
//        int len_ = _a.size();
//        if (_b.size() != len_) {
//            return false;
//        }
//        for (int i = FIRST_INDEX; i < len_; i++) {
//            E e_ = _a.get(i);
//            E f_ = _b.get(i);
//            if (e_ == null) {
//                if (f_ != null) {
//                    return false;
//                }
//                continue;
//            }
//            if (f_ == null) {
//                return false;
//            }
//            if (!e_.eq(f_)) {
//                return false;
//            }
//        }
//        return true;
//    }

    public void removeFirst() {
        if (isEmpty()) {
            return;
        }
        removeAt(FIRST_INDEX);
    }

    public void removeLast() {
        if (isEmpty()) {
            return;
        }
        removeAt(getLastIndex());
    }

    public CustList<T> mid(int _beginIndex, int _nbElements) {
        if (_beginIndex+_nbElements > size()) {
            return mid(_beginIndex);
        }
        return new CustList<T>(sub(_beginIndex, _beginIndex+_nbElements));
    }

    public CustList<T> mid(int _beginIndex) {
        return new CustList<T>(sub(_beginIndex,size()));
    }

    @Override
    public CustList<T> sub(int _from, int _to) {
        if (_from > _to) {
            return new CustList<T>();
        }
        int b_ = Math.max(_from,FIRST_INDEX);
        int e_ = Math.min(_to, size());
        CustList<T> l_ = new CustList<T>();
        for (T e: list.subList(Math.max(b_,FIRST_INDEX), Math.min(e_, size()))) {
            l_.add(e);
        }
        return l_;
    }

//    public CustList<T> subList(int _from, int _to) {
//        if (_from > _to) {
//            return new CustList<T>();
//        }
//        CustList<T> l_ = new CustList<T>();
//        for (T e: list.subList(Math.max(_from,FIRST_INDEX), Math.min(_to, size()))) {
//            l_.add(e);
//        }
//        return l_;
//    }

}
