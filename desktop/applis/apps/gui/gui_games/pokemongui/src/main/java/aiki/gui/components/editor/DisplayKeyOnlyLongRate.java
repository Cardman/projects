package aiki.gui.components.editor;

import code.gui.*;
import code.maths.*;

public final class DisplayKeyOnlyLongRate implements DisplayEntryCust<Integer, EditedCrudPair<Long,Rate>> {

    @Override
    public String display(Integer _k, EditedCrudPair<Long, Rate> _v) {
        return _v.getKey()+ConverterCommonMapUtil.K_V+_v.getValue().toNumberString();
    }
}
