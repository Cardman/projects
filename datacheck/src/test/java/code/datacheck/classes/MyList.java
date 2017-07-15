package code.datacheck.classes;
import java.util.Iterator;
import java.util.ListIterator;

import code.util.CustList;
import code.util.Numbers;
import code.util.ints.Listable;

public final class MyList<E> implements Listable<E> {

    private CustList<E> elements = new CustList<E>();

    public MyList() {
    }

    public MyList(E... _elts) {
        for (E e: _elts) {
            elements.add(e);
        }
    }

    @Override
    public void add(int _index, E _element) {
        elements.add(_index, _element);
    }

    @Override
    public void add(E _e) {
        elements.add(_e);
    }
//
//    @Override
//    public boolean addAll(Collection<? extends E> _c) {
//        return elements.addAll(_c);
//    }

//    @Override
//    public boolean addAll(int _index, Collection<? extends E> _c) {
//        return elements.addAll(_index, _c);
//    }

    @Override
    public void clear() {
        elements.clear();
    }

//    @Override
//    public boolean contains(Object _o) {
//        return elements.contains(_o);
//    }
//
//    @Override
//    public boolean containsAll(Collection<?> _arg0) {
//        return elements.containsAll(_arg0);
//    }

    @Override
    public E get(int _index) {
        return elements.get(_index);
    }

//    @Override
//    public int indexOf(Object _o) {
//        return elements.indexOf(_o);
//    }

    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public Iterator<E> iterator() {
        return elements.iterator();
    }

//    @Override
//    public int lastIndexOf(Object _o) {
//        return elements.lastIndexOf(_o);
//    }

    @Override
    public ListIterator<E> listIterator() {
        return elements.listIterator();
    }

    @Override
    public ListIterator<E> listIterator(int _index) {
        return elements.listIterator(_index);
    }

    @Override
    public void remove(int _index) {
        elements.remove(_index);
    }

//    @Override
//    public boolean removeAll(Collection<?> _c) {
//        return elements.removeAll(_c);
//    }
//
//    @Override
//    public boolean remove(Object _o) {
//        return elements.remove(_o);
//    }
//
//    @Override
//    public boolean retainAll(Collection<?> _c) {
//        return elements.retainAll(_c);
//    }

    @Override
    public void set(int _index, E _element) {
        elements.set(_index, _element);
    }

    @Override
    public int size() {
        return elements.size();
    }

//    @Override
//    public List<E> subList(int _fromIndex, int _toIndex) {
//        return elements.subList(_fromIndex, _toIndex);
//    }

    @Override
    public Object[] toArray() {
        return elements.toArray();
    }

//    @Override
//    public <T> T[] toArray(T[] _a) {
//        return elements.toArray(_a);
//    }

//    @Override
//    public Object clone() {
//        return elements.clone();
//    }

    @Override
    public boolean isValidIndex(int _index) {
        return elements.isValidIndex(_index);
    }

//    public Numbers<Integer> indexesOfObject(E _string) {
//        return elements.indexesOfObject(_string);
//    }

//    public void sort(Comparator<? super E> _comp) {
//        elements.sortElts(new _comp);
//    }

//    public void replace(E _old, E _new) {
//        elements.replace(_old, _new);
//    }

    @Override
    public String join(String _join) {
        return elements.join(_join);
    }

//    public boolean containsObj(E _element) {
//        return elements.containsObj(_element);
//    }

//    public boolean containsAllObj(List<E> _list) {
//        return elements.containsAllObj(_list);
//    }

//    public void ensureCapacity(int _minCapacity) {
//        elements.ensureCapacity(_minCapacity);
//    }

//    public void removeObj(E _obj) {
//        elements.removeObj(_obj);
//    }

//    public boolean removedObj(E _obj) {
//        return elements.removedObj(_obj);
//    }

//    public void removeDuplicates() {
//        elements.removeDuplicates();
//    }

//    public boolean removeAll(List<E> _list) {
//        return elements.removeAll(_list);
//    }

    @Override
    public E first() {
        return elements.first();
    }

//    public boolean retainAll(List<E> _c) {
//        return elements.retainAll(_c);
//    }

//    public int indexOfObj(E _element) {
//        return elements.indexOfObj(_element);
//    }

//    public E removeAtAndGet(Number _n) {
//        return elements.removeAtAndGet(_n);
//    }

    @Override
    public E last() {
        return elements.last();
    }

//    public List<E> mid(int _beginIndex, int _nbElements) {
//        return elements.mid(_beginIndex, _nbElements);
//    }

//    public void setModified() {
//        elements.setModified();
//    }
//
//    public void setUnmodified() {
//        elements.setUnmodified();
//    }
//
//    public boolean isModified() {
//        return elements.isModified();
//    }

    @Override
    public String toString() {
        return elements.toString();
    }

//    public void trimToSize() {
//        elements.trimToSize();
//    }

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
            return -1;
        }
        return indexes_.first();
    }

    @Override
    public int lastIndexOfNull() {
        Numbers<Integer> indexes_;
        indexes_ = indexesOfNull();
        if (indexes_.isEmpty()) {
            return -1;
        }
        return indexes_.last();
    }

    @Override
    public Numbers<Integer> indexesOfNull() {
        Numbers<Integer> indexes_;
        indexes_ = new Numbers<Integer>();
        int s_ = size();
        for (int i = 0; i < s_; i++) {
            if (get(i) == null) {
                indexes_.add(i);
            }
        }
        return indexes_;
    }

    @Override
    public Listable<E> sub(int _from, int _to) {
        return elements.sub(_from, _to);
    }

    @Override
    public String join(char _char) {
        return elements.join(_char);
    }

    @Override
    public void addAllElts(Listable<? extends E> _c) {
        elements.addAllElts(_c);
    }

    @Override
    public void removeAt(Number _index) {
        elements.removeAt(_index);
    }

    @Override
    public int indexOfNull(int _from) {
        int s_ = size();
        for (int i = _from; i < s_; i++) {
            if (get(i) == null) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Listable<E> getReverse() {
        return elements.getReverse();
    }

    @Override
    public void swapIndexes(int _i, int _j) {
        elements.swapIndexes(_i, _j);
    }
}
