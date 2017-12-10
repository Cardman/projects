package code.sml;

import code.util.CustList;
import code.util.ints.Listable;

public final class ElementList extends CustList<Element> {

    public ElementList() {
    }

    public ElementList(Listable<Element> _c) {
        super(_c);
    }

    public ElementList(Element... _elements) {
        super(_elements);
    }

    public Element item(int _i) {
        return get(_i);
    }

    public int getLength() {
        return size();
    }
}
