package code.util;
import java.util.ArrayList;
import java.util.Iterator;

import code.util.core.IndexConstants;
import code.util.core.SortConstants;
import code.util.ints.Comparing;
import code.util.ints.Listable;

public class CustList<T> implements Listable<T> {

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
    public final void addAllElts(Listable<T> _c) {
        for (T e: _c) {
            add(e);
        }
    }
    public final void sortElts(Comparing<T> _comp) {
        int len_ = list.size();
        for (int i = IndexConstants.FIRST_INDEX; i <len_; i++) {
            for (int j = i + 1; j <len_; j++) {
                T one_ = list.get(i);
                T two_ = list.get(j);
                int res_ = _comp.compare(one_, two_);
                if (res_ > SortConstants.EQ_CMP) {
                    list.set(i, two_);
                    list.set(j, one_);
                }
            }
        }
    }

    @Override
    public final boolean isValidIndex(int _index) {
        if (_index < IndexConstants.FIRST_INDEX) {
            return false;
        }
        return _index < size();
    }
    @Override
    public final T first() {
        return get(IndexConstants.FIRST_INDEX);
    }

    @Override
    public final T last() {
        return get(getLastIndex());
    }
    public final void setLast(T _element) {
        set(getLastIndex(), _element);
    }

    public final int getLastIndex() {
        int last_ = size();
        last_--;
        return last_;
    }

    public final T getPrev(int _i) {
        return get(_i-1);
    }

    @Override
    public final int size() {
        return list.size();
    }

    @Override
    public final boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public final Iterable<T> getList() {
        return list;
    }

    @Override
    public final Iterator<T> iterator() {
        return list.iterator();
    }

    public final Object[] toArray() {
        return list.toArray();
    }

    @Override
    public final void add(T _e) {
        list.add(_e);
    }

    public final void retainAllElements(AbEqList<T> _c) {
        int i_ = size() - 1;
        while (i_ >= IndexConstants.FIRST_INDEX) {
            T e_ = get(i_);
            if (!_c.containsObj(e_)) {
                remove(i_);
            }
            i_--;
        }
    }

    @Override
    public final void clear() {
        list.clear();
    }

    public final T get(int _index) {
        return list.get(_index);
    }

    @Override
    public final void set(int _index, T _element) {
        list.set(_index, _element);
    }

    @Override
    public final void add(int _index, T _element) {
        list.add(_index, _element);
    }

    @Override
    public final void remove(int _index) {
        list.remove(_index);
    }

    @Override
    public final CustList<T> getReverse() {
        CustList<T> list_ = new CustList<T>(this);
        int i_ = IndexConstants.FIRST_INDEX;
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
    public final void swapIndexes(int _i, int _j) {
        T first_ = list.get(_i);
        list.set(_i, list.get(_j));
        list.set(_j, first_);
    }

    public final CustList<CustList<T>> getBaseGroupsSameCompare(Comparing<T> _cmp) {
        CustList<T> copy_ = new CustList<T>(this);
        copy_.sortElts(_cmp);
        CustList<CustList<T>> groups_;
        groups_ = new CustList<CustList<T>>();
        CustList<T> group_;
        group_ = new CustList<T>();
        int i_ = IndexConstants.FIRST_INDEX;
        int j_ = i_;
        j_--;
        while (i_ < copy_.size()) {
            if (i_ > IndexConstants.FIRST_INDEX) {
                int res_ = _cmp.compare(copy_.get(i_), copy_.get(j_));
                if (res_ != SortConstants.EQ_CMP) {
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

    public final void removeLast() {
        if (isEmpty()) {
            return;
        }
        removeQuicklyLast();
    }

    public void removeQuicklyLast() {
        remove(getLastIndex());
    }

    public final CustList<T> left(int _nbElements) {
        if (_nbElements < 1) {
            return new CustList<T>(new CollCapacity(0));
        }
        if (_nbElements >= size()) {
            return new CustList<T>(this);
        }
        CustList<T> list_ = new CustList<T>(new CollCapacity(_nbElements));
        for (int i = 0; i < _nbElements; i++) {
            list_.add(get(i));
        }
        return list_;
    }

    public final CustList<T> leftMinusOne(int _nbElements) {
        if (_nbElements < 1) {
            return new CustList<T>(new CollCapacity(0));
        }
        int indexMax_ = Math.max(Math.min(size()-1,_nbElements),0);
        CustList<T> list_ = new CustList<T>(new CollCapacity(indexMax_));
        for (int i = 1; i <= indexMax_; i++) {
            list_.add(get(i));
        }
        return list_;
    }
    public final CustList<T> mid(int _beginIndex, int _nbElements) {
        if (_beginIndex+_nbElements > size()) {
            return mid(_beginIndex);
        }
        return new CustList<T>(sub(_beginIndex, _beginIndex+_nbElements));
    }

    public final CustList<T> mid(int _beginIndex) {
        if (_beginIndex > size()) {
            return new CustList<T>(new CollCapacity(0));
        }
        int b_ = Math.max(_beginIndex, IndexConstants.FIRST_INDEX);
        int e_ = size();
        CustList<T> l_ = new CustList<T>(new CollCapacity(Math.max(e_-b_,0)));
        for (int i = b_; i < e_; i++) {
            l_.add(get(i));
        }
        return l_;
    }

    @Override
    public final CustList<T> sub(int _from, int _to) {
        if (_from > _to) {
            return new CustList<T>(new CollCapacity(0));
        }
        int b_ = Math.max(_from, IndexConstants.FIRST_INDEX);
        int e_ = Math.max(Math.min(_to, size()),b_);
        CustList<T> l_ = new CustList<T>(new CollCapacity(e_-b_));
        for (int i = b_; i < e_; i++) {
            l_.add(get(i));
        }
        return l_;
    }

    @Override
    public final SimpleItr simpleIterator() {
        return new SimpleItr(list.toArray());
    }

}
