package code.gui;

import code.util.CustList;

public interface IntValidateElementAdd<E> {
    boolean valid(CustList<E> _ls, E _elt);
}
