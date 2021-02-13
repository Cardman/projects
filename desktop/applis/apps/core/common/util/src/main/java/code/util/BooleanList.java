package code.util;
import code.util.core.IndexConstants;
import code.util.ints.Listable;

public final class BooleanList extends AbEqList<Boolean> {

    public BooleanList() {
    }

    public BooleanList(Boolean... _elements) {
        super(_elements);
    }
    public BooleanList(Listable<Boolean> _c) {
        super(_c);
    }

    
    public BooleanList(CollCapacity _capacity) {
        super(_capacity);
    }

    public Ints indexesOfBool(boolean _element) {
        return indexesOfObj(_element);
    }

    public boolean containsObj(boolean _b) {
        return indexOfObj(_b, IndexConstants.FIRST_INDEX) > -1;
    }

    @Override
    public boolean match(Boolean _one, Boolean _two) {
        return _one == _two;
    }
}
