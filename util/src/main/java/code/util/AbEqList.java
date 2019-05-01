package code.util;
import code.util.ints.Listable;

public abstract class AbEqList<T> extends CustList<T> {

    public AbEqList() {
    }

    public AbEqList(Listable<T> _c) {
        super(_c);
    }

    public AbEqList(T... _elements) {
        super(_elements);
    }

    protected AbEqList(CollCapacity _capacity) {
        super(_capacity);
    }

    public void removeAllElements(Listable<T> _c) {
        for (T s: _c) {
            if (containsObj(s)) {
                removeAllObj(s);
            }
        }
    }

    public void removeAllObj(T _obj) {
        while (containsObj(_obj)) {
            removeObj(_obj);
        }
    }

    public void removeObj(T _obj) {
        int index_ = indexOfObj(_obj);
        if (index_ == CustList.INDEX_NOT_FOUND_ELT) {
            return;
        }
        removeAt(index_);
    }

    public boolean containsAllObj(Listable<T> _list) {
        for (T e: _list) {
            if (!containsObj(e)) {
                return false;
            }
        }
        return true;
    }
    public boolean containsObj(T _obj) {
        return indexOfObj(_obj) != INDEX_NOT_FOUND_ELT;
    }

    public int indexOfObj(T _obj) {
        return indexOfObj(_obj, FIRST_INDEX);
    }

    public abstract int indexOfObj(T _element, int _from);

}
