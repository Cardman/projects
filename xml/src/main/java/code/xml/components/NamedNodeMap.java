package code.xml.components;

import code.util.CustList;
import code.util.StringList;
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

    public Attr getNamedItem(String _key) {
        for (Attr a: this) {
            if (StringList.quickEq(a.getName(), _key)) {
                return a;
            }
        }
        return null;
    }

    public Attr item(int _i) {
        return get(_i);
    }

    public int getLength() {
        return size();
    }
}
