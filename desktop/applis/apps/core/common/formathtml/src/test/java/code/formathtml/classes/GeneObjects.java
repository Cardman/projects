package code.formathtml.classes;

import code.util.CustList;
import code.util.ints.Listable;

public class GeneObjects extends CustList<Object> {

    public GeneObjects() {
    }

    public GeneObjects(Object... _c) {
        super(_c);
    }

    public GeneObjects(Listable<Object> _c) {
        super(_c);
    }
}
