package code.gui;

import code.util.*;
import code.util.ints.*;

public final class ValidateElementPair<K,V> implements IntValidateElementAdd<EditedCrudPair<K,V>> {
    private final Comparing<EditedCrudPair<K,V>> comparing;

    public ValidateElementPair(Comparing<EditedCrudPair<K,V>> _c) {
        this.comparing = _c;
    }

    @Override
    public boolean valid(CustList<EditedCrudPair<K, V>> _ls, int _selectedIndex, EditedCrudPair<K, V> _elt) {
        return _selectedIndex >= 0 || indexPair(_ls, _elt) < 0;
    }

    public int indexPair(CustList<EditedCrudPair<K, V>> _ls, EditedCrudPair<K, V> _elt) {
        return new ComparingCrudUtil<EditedCrudPair<K,V>>(comparing).indexPair(_ls,_elt);
    }
}
