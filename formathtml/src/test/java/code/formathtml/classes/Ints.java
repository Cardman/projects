package code.formathtml.classes;

import code.util.CustList;
import code.util.Numbers;
import code.util.SimpleItr;
import code.util.ints.Countable;
import code.util.ints.Displayable;
import code.util.ints.SimpleIterable;
import code.util.ints.SimpleList;

public class Ints implements SimpleIterable , Countable, SimpleList, Displayable {

    private Numbers<Integer> ints = new Numbers<Integer>();

    public void setLast(Integer _element) {
        ints.setLast(_element);
    }

    public int getLastIndex() {
        return ints.getLastIndex();
    }

    public Integer getPrev(int _i) {
        return ints.getPrev(_i);
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
    public Object[] toArray() {
        return ints.toArray();
    }

    @Override
    public Integer get(int _index) {
        return ints.get(_index);
    }

    public void sort() {
        ints.sort();
    }

    public Integer getMinimum() {
        return ints.getMinimum();
    }

    public Integer getMaximum() {
        return ints.getMaximum();
    }

    @Override
    public String display() {
        return ints.display();
    }

    public Numbers<Integer> subAbEq(int _from, int _to) {
        return ints.subAbEq(_from, _to);
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
    public SimpleItr simpleIterator() {
        return new SimpleItr(ints.toArray());
    }

    public void add(int _i) {
        ints.add(_i);
    }

    public void set(int _index, Integer _element) {
        ints.set(_index, _element);
    }
}
