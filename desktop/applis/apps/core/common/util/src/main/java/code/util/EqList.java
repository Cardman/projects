package code.util;
import code.util.ints.Equallable;
import code.util.ints.Listable;

/** Not compared in tests */
public final class EqList<T extends Equallable<T>> extends AbEqList<T> {
    public EqList() {
    }

    public EqList(T... _elements) {
        super(_elements);
    }
    public EqList(Listable<T> _c) {
        super(_c);
    }

    
    public EqList(CollCapacity _capacity) {
        super(_capacity);
    }

    @Override
    public boolean match(T _one, T _two) {
        return _one.eq(_two);
    }

}
