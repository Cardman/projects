package code.xml.components;

import code.util.CustList;
import code.util.ints.Listable;

public final class NodeList extends CustList<Node> {

    public NodeList() {
    }

    public NodeList(Listable<Node> _c) {
        super(_c);
    }

    public NodeList(Node... _elements) {
        super(_elements);
    }

}
