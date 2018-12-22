package code.expressionlanguage.classes;

import java.util.Iterator;

import code.util.CustList;
import code.util.Numbers;
import code.util.SimpleItr;
import code.util.ints.Comparing;
import code.util.ints.Listable;

public class Ints implements Listable<Integer> {

    private Numbers<Integer> ints = new Numbers<Integer>();

    @Override
    public void addAllElts(Listable<Integer> _c) {
        ints.addAllElts(_c);
    }

    public void sortElts(Comparing<Integer> _comp) {
        ints.sortElts(_comp);
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

    public String join(String _join) {
        return ints.join(_join);
    }

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

    public void sort() {
        ints.sort();
    }

    public Integer getMinimum() {
        return ints.getMinimum();
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

    @Override
    public int lastIndexOfNull() {
        return ints.lastIndexOfNull();
    }

    @Override
    public Numbers<Integer> indexesOfNull() {
        return ints.indexesOfNull();
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

    public void removeFirst() {
        ints.removeFirst();
    }

    public void removeLast() {
        ints.removeLast();
    }

    public CustList<Integer> mid(int _beginIndex) {
        return ints.mid(_beginIndex);
    }

    @Override
    public Numbers<Integer> getReverse() {
        return ints.getReverse();
    }

    @Override
    public SimpleItr simpleIterator() {
        return new SimpleItr(ints.toArray());
    }

}
