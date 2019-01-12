package code.expressionlanguage.classes;

import code.util.CustList;
import code.util.Numbers;
import code.util.SimpleItr;
import code.util.ints.Comparing;
import code.util.ints.Countable;
import code.util.ints.SimpleIterable;
import code.util.ints.SimpleList;

public class Ints implements SimpleIterable , Countable, SimpleList {

    private Numbers<Integer> ints = new Numbers<Integer>();

    public Integer first() {
        return ints.first();
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

    public void add(Integer _e) {
        ints.add(_e);
    }

    @Override
    public Integer get(int _index) {
        return ints.get(_index);
    }

    @Override
    public SimpleItr simpleIterator() {
        return new SimpleItr(ints.toArray());
    }

}
