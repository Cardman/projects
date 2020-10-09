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

    public Ints indexesOfObj(boolean _element) {
        Ints indexes_;
        indexes_ = new Ints();
        int i_ = IndexConstants.FIRST_INDEX;
        while (true) {
            int found_ = indexOfObj(_element, i_);
            if (found_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
                break;
            }
            indexes_.add(found_);
            i_ = found_ + 1;
        }
        return indexes_;
    }

    public boolean containsObj(boolean _b) {
        return indexOfObj(_b, IndexConstants.FIRST_INDEX) > -1;
    }

    @Override
    public boolean match(Boolean _one, Boolean _two) {
        return _one == _two;
    }
}
