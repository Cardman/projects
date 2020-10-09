package code.util;
import code.util.ints.Listable;

public final class IdList<T> extends AbEqList<T> {

    public IdList() {
    }

    public IdList(T... _elements) {
        super(_elements);
    }

    public IdList(Listable<T> _c) {
        super(_c);
    }

    
    public IdList(CollCapacity _capacity) {
        super(_capacity);
    }

    @Override
    public boolean match(T _one, T _two) {
        return _one == _two;
    }

}
