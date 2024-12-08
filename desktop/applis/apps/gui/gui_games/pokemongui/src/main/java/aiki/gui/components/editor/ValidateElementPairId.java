package aiki.gui.components.editor;

import aiki.db.*;
import code.gui.*;
import code.util.*;

public final class ValidateElementPairId<V> implements IntValidateElementAdd<EditedCrudPair<String,V>> {
    @Override
    public boolean valid(CustList<EditedCrudPair<String, V>> _ls, int _selectedIndex, EditedCrudPair<String, V> _elt) {
        return DataBase.isCorrectIdentifier(_elt.getKey()) && (_selectedIndex >= 0 || new ValidateElementPair<String, V>(new ComparingStringKey<V>()).indexPair(_ls, _elt) < 0);
    }
}
