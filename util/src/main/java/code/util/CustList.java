package code.util;
import java.util.ArrayList;
import java.util.Iterator;

import code.util.ints.Comparing;
import code.util.ints.Listable;

public class CustList<T> implements Listable<T> {

    public static final byte SWAP_SORT = 1;

    public static final byte NO_SWAP_SORT = -1;

    public static final byte EQ_CMP = 0;

    public static final byte INDEX_NOT_FOUND_ELT = -1;

    public static final byte FIRST_INDEX = 0;

    public static final byte SECOND_INDEX = 1;

    public static final byte ONE_ELEMENT = 1;

    public static final byte SIZE_EMPTY = 0;

    protected static final String EMPTY_STRING = "";

    private final ArrayList<T> list;

    public CustList() {
        list = new ArrayList<T>();
    }

    public CustList(T... _elements) {
        list = new ArrayList<T>(_elements.length);
        for (T e: _elements) {
            add(e);
        }
    }

    public CustList(Listable<T> _c) {
        list = new ArrayList<T>(_c.size());
        for (T e: _c) {
            add(e);
        }
    }

    
    public CustList(CollCapacity _capacity) {
        list = new ArrayList<T>(_capacity.getCapacity());
    }

    @Override
    public void addAllElts(Listable<T> _c) {
        for (T e: _c) {
            add(e);
        }
    }
    public void sortElts(Comparing<T> _comp) {
        int len_ = list.size();
        for (int i = FIRST_INDEX; i <len_; i++) {
            for (int j = i + 1; j <len_; j++) {
                T one_ = list.get(i);
                T two_ = list.get(j);
                int res_ = _comp.compare(one_, two_);
                if (res_ > EQ_CMP) {
                    list.set(i, two_);
                    list.set(j, one_);
                }
            }
        }
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
        return get(_i-1);
    }

    @Override
    public void removeAt(int _n) {
        list.remove(_n);
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
    public Iterable<T> getList() {
        return list;
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @Override
    public void add(T _e) {
        list.add(_e);
    }

    public void retainAllElements(AbEqList<T> _c) {
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
        T first_ = list.get(_i);
        list.set(_i, list.get(_j));
        list.set(_j, first_);
    }

    public CustList<CustList<T>> getBaseGroupsSameCompare(Comparing<T> _cmp) {
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
        for (T e: list.subList(b_, e_)) {
            l_.add(e);
        }
        return l_;
    }

    @Override
    public final SimpleItr simpleIterator() {
        return new SimpleItr(list.toArray());
    }

}
