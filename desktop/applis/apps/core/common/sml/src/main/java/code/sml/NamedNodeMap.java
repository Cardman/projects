package code.sml;

import java.util.Iterator;

import code.util.CustList;
import code.util.StringList;
import code.util.ints.Listable;

public final class NamedNodeMap implements Iterable<Attr> {

    private final CustList<Attr> attrs;

    public NamedNodeMap() {
        attrs = new CustList<Attr>();
    }

    public NamedNodeMap(Listable<Attr> _c) {
        attrs = new CustList<Attr>(_c);
    }

    public Attr item(int _i) {
        return attrs.get(_i);
    }

    public int getLength() {
        return attrs.size();
    }

    @Override
    public Iterator<Attr> iterator() {
        return attrs.iterator();
    }

    public boolean isEmpty() {
        return attrs.isEmpty();
    }

    public int size() {
        return attrs.size();
    }

    public void remove(int _index) {
        attrs.remove(_index);
    }

    public void add(Attr _attr) {
        attrs.add(_attr);
    }
}
