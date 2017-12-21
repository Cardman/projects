package code.expressionlanguage.classes;

import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;

import code.util.CustList;
import code.util.EqList;
import code.util.Numbers;
import code.util.SimpleItr;
import code.util.ints.Listable;

public class Ints implements Listable<Integer> {

    private Numbers<Integer> ints = new Numbers<Integer>();

    public void removeAllObj(Integer _obj) {
        ints.removeAllObj(_obj);
    }

    public void removeObj(Integer _obj) {
        ints.removeObj(_obj);
    }

    public CustList<Integer> intersect(CustList<Integer> _list) {
        return ints.intersect(_list);
    }

    @Override
    public void addAllElts(Listable<Integer> _c) {
        ints.addAllElts(_c);
    }

    public boolean containsObj(Integer _obj) {
        return ints.containsObj(_obj);
    }

    public void sortElts(Comparator<Integer> _comp) {
        ints.sortElts(_comp);
    }

    public int indexOfObj(Integer _obj) {
        return ints.indexOfObj(_obj);
    }

    public int lastIndexOfObj(Integer _obj) {
        return ints.lastIndexOfObj(_obj);
    }

    public void removeDuplicates() {
        ints.removeDuplicates();
    }

    @Override
    public boolean isValidIndex(int _index) {
        return ints.isValidIndex(_index);
    }

    @Override
    public Integer first() {
        return ints.first();
    }

    @Override
    public Integer last() {
        return ints.last();
    }

    public void setLast(Integer _element) {
        ints.setLast(_element);
    }

    public int getLastIndex() {
        return ints.getLastIndex();
    }

    public Integer getPrev(int _i) {
        return ints.getPrev(_i);
    }

    @Override
    public void removeAt(Number _n) {
        ints.removeAt(_n);
    }

    @Override
    public String join(String _join) {
        return ints.join(_join);
    }

    @Override
    public String join(char _join) {
        return ints.join(_join);
    }

    @Override
    public int size() {
        return ints.size();
    }

    @Override
    public boolean isEmpty() {
        return ints.isEmpty();
    }

    public Numbers<Integer> indexesOfObj(Integer _element) {
        return ints.indexesOfObj(_element);
    }

    @Override
    public Iterator<Integer> iterator() {
        return ints.iterator();
    }

    @Override
    public Object[] toArray() {
        return ints.toArray();
    }

    @Override
    public void add(Integer _e) {
        ints.add(_e);
    }

    public EqList<Numbers<Integer>> getAllIndexes() {
        return ints.getAllIndexes();
    }

    @Override
    public void clear() {
        ints.clear();
    }

    @Override
    public Integer get(int _index) {
        return ints.get(_index);
    }

    @Override
    public void set(int _index, Integer _element) {
        ints.set(_index, _element);
    }

    @Override
    public void add(int _index, Integer _element) {
        ints.add(_index, _element);
    }

    @Override
    public void remove(int _index) {
        ints.remove(_index);
    }

    @Override
    public ListIterator<Integer> listIterator() {
        return ints.listIterator();
    }

    public void sort() {
        ints.sort();
    }

    public Integer getMinimum() {
        return ints.getMinimum();
    }

    @Override
    public ListIterator<Integer> listIterator(int _index) {
        return ints.listIterator(_index);
    }

    @Override
    public void swapIndexes(int _i, int _j) {
        ints.swapIndexes(_i, _j);
    }

    @Override
    public void removeNull() {
        ints.removeNull();
    }

    @Override
    public boolean containsNull() {
        return ints.containsNull();
    }

    @Override
    public int indexOfNull() {
        return ints.indexOfNull();
    }

    public Integer getMaximum() {
        return ints.getMaximum();
    }

    @Override
    public int indexOfNull(int _from) {
        return ints.indexOfNull(_from);
    }

    public void removeOneNumber(Number _n) {
        ints.removeOneNumber(_n);
    }

    @Override
    public int lastIndexOfNull() {
        return ints.lastIndexOfNull();
    }

    public boolean contains(Number _element) {
        return ints.contains(_element);
    }

    @Override
    public Numbers<Integer> indexesOfNull() {
        return ints.indexesOfNull();
    }

    public int indexOf(Number _element) {
        return ints.indexOf(_element);
    }

    @Override
    public String toString() {
        return ints.toString();
    }

    public Numbers<Integer> subAbEq(int _from, int _to) {
        return ints.subAbEq(_from, _to);
    }

    @Override
    public Numbers<Integer> sub(int _from, int _to) {
        return ints.sub(_from, _to);
    }

    public Numbers<Integer> mid(int _beginIndex, int _nbElements) {
        return ints.mid(_beginIndex, _nbElements);
    }

    public int indexOfObj(Integer _element, int _from) {
        return ints.indexOfObj(_element, _from);
    }

    public void removeFirst() {
        ints.removeFirst();
    }

    public Numbers<Integer> indexesOfNumber(Integer _number) {
        return ints.indexesOfNumber(_number);
    }

    public void removeLast() {
        ints.removeLast();
    }

    public CustList<Integer> mid(int _beginIndex) {
        return ints.mid(_beginIndex);
    }

    public void removeAllNb(Integer _obj) {
        ints.removeAllNb(_obj);
    }

    @Override
    public Numbers<Integer> getReverse() {
        return ints.getReverse();
    }

    public boolean eq(Numbers<Integer> _g) {
        return ints.eq(_g);
    }

    @Override
    public SimpleItr simpleIterator() {
        return new SimpleItr(ints.toArray());
    }

}
