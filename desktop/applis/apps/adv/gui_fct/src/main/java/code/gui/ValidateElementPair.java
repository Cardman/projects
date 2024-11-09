package code.gui;

import code.util.*;
import code.util.core.*;
import code.util.ints.*;

public final class ValidateElementPair<K,V> implements IntValidateElementAdd<EditedCrudPair<K,V>> {
    private final Comparing<EditedCrudPair<K,V>> comparing;

    public ValidateElementPair(Comparing<EditedCrudPair<K,V>> _c) {
        this.comparing = _c;
    }

    @Override
    public boolean valid(CustList<EditedCrudPair<K, V>> _ls, EditedCrudPair<K, V> _elt) {
        return inexistPair(_ls, _elt);
    }

    public boolean inexistPair(CustList<EditedCrudPair<K, V>> _ls, EditedCrudPair<K, V> _elt) {
        for (EditedCrudPair<K, V> e: _ls) {
            if (comparing.compare(_elt,e) == SortConstants.EQ_CMP) {
                return false;
            }
        }
        return true;
    }
}
