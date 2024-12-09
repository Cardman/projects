package aiki.gui.components.editor;

import aiki.db.*;
import code.gui.*;
import code.util.*;
import code.util.core.*;

public final class ValidateElementPairIdImgFree implements IntValidateElementAdd<EditedCrudPair<String, ImageArrayBaseSixtyFour>> {
    private String oldValue;
    @Override
    public boolean valid(CustList<EditedCrudPair<String, ImageArrayBaseSixtyFour>> _ls, int _selectedIndex, EditedCrudPair<String, ImageArrayBaseSixtyFour> _elt) {
        if (_elt.getValue().getImage().length <= 0) {
            oldValue = null;
            return false;
        }
        int s_ = _ls.size();
        for (int i = 0; i < s_; i++) {
            if (i != _selectedIndex && StringUtil.quickEq(_ls.get(i).getKey(),_elt.getKey())) {
                oldValue = null;
                return false;
            }
        }
        if (_selectedIndex < 0) {
            oldValue = null;
            return true;
        }
        oldValue = _ls.get(_selectedIndex).getKey();
        return true;
    }

    public String getOldValue() {
        return oldValue;
    }
}
