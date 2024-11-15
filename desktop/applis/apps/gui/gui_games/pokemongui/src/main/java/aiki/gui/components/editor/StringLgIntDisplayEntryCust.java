package aiki.gui.components.editor;

import code.gui.*;
import code.maths.*;

public final class StringLgIntDisplayEntryCust implements DisplayEntryCust<Integer, EditedCrudPair<String, LgInt>> {
    @Override
    public String display(Integer _k, EditedCrudPair<String,LgInt> _v) {
        return _v.getKey()+":"+_v.getValue().toNumberString();
    }
}
