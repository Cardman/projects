package code.xml.components;

import code.util.CustList;
import code.util.ints.Listable;

public final class NamedNodeMap extends CustList<Attr> {

    public NamedNodeMap() {
    }

    public NamedNodeMap(Attr... _elements) {
        super(_elements);
    }

    public NamedNodeMap(Listable<Attr> _c) {
        super(_c);
    }

}
