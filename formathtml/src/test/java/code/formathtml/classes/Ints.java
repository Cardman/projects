package code.formathtml.classes;

import code.util.Numbers;
import code.util.SimpleItr;
import code.util.ints.Countable;
import code.util.ints.Displayable;
import code.util.ints.SimpleIterable;
import code.util.ints.SimpleList;

public class Ints implements SimpleIterable , Countable, SimpleList, Displayable {

    private Numbers<Integer> ints = new Numbers<Integer>();

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

    @Override
    public String display() {
        return ints.display();
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
