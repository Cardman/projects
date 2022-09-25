package code.util;
import code.util.ints.Listable;

/** Not compared in tests */
public final class EnumList<T> extends AbEqList<T> {

    public EnumList() {
    }

    public EnumList(Listable<T> _c) {
        super(_c);
    }

    public EnumList(T... _elements) {
        super(_elements);
    }


    public EnumList(CollCapacity _capacity) {
        super(_capacity);
    }

    @Override
    public boolean match(T _one, T _two) {
        return _one == _two;
    }

}
