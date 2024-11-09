package aiki.gui.components.editor;

import aiki.db.DataBase;
import code.gui.*;
import code.util.*;

public final class ValidateElementPairId<V> implements IntValidateElementAdd<EditedCrudPair<String,V>> {
    @Override
    public boolean valid(CustList<EditedCrudPair<String, V>> _ls, EditedCrudPair<String, V> _elt) {
        return DataBase.isCorrectIdentifier(_elt.getKey()) && new ValidateElementPair<String, V>(new ComparingStringKey<V>()).valid(_ls, _elt);
    }
}
