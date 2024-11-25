package aiki.gui.components.editor;

import code.gui.*;
import code.maths.*;
import code.util.core.*;

public final class BoolLgIntDisplayEntryCust implements DisplayEntryCust<Integer, EditedCrudPair<BoolVal, LgInt>> {
    @Override
    public String display(Integer _k, EditedCrudPair<BoolVal,LgInt> _v) {
        if (_v.getKey() == BoolVal.TRUE) {
            return "1:"+_v.getValue().toNumberString();
        }
        return "0:"+_v.getValue().toNumberString();
    }
}
