package code.gui;

import code.util.CustList;

public interface IntValidateElementAdd<E> {
    boolean valid(CustList<E> _ls, int _selectedIndex, E _elt);
}
