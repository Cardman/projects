package code.formathtml.render;

import code.util.CustList;
import code.util.core.BoolVal;

public final class MetaOrderedList extends MetaContainer {

    private final BoolVal ordered;
    private final CustList<MetaNumberedLabel> numbers = new CustList<MetaNumberedLabel>();
    public MetaOrderedList(MetaContainer _parent, BoolVal _v) {
        super(_parent, MetaLayout.BOX);
        ordered = _v;
    }

    public BoolVal getOrdered() {
        return ordered;
    }

    public CustList<MetaNumberedLabel> getNumbers() {
        return numbers;
    }
}
