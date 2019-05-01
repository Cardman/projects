package code.sml;

import code.util.CustList;

public final class ElementList extends CustList<Element> {

    public Element item(int _i) {
        return get(_i);
    }

    public int getLength() {
        return size();
    }
}
