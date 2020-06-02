package code.sml;

import code.util.CustList;

public final class NodeList extends CustList<Node> {

    public Node item(int _i) {
        return get(_i);
    }

    public int getLength() {
        return size();
    }
}
