package aiki.gui.components.editor;

import aiki.db.*;
import code.gui.*;
import code.util.*;

public final class ValidateElementPairIdImgHeros implements IntValidateElementAdd<EditedCrudPair<ImageHeroKey, ImageArrayBaseSixtyFour>> {
    @Override
    public boolean valid(CustList<EditedCrudPair<ImageHeroKey, ImageArrayBaseSixtyFour>> _ls, int _selectedIndex, EditedCrudPair<ImageHeroKey, ImageArrayBaseSixtyFour> _elt) {
        if (_elt.getValue().getImage().length <= 0) {
            return false;
        }
        int s_ = _ls.size();
        for (int i = 0; i < s_; i++) {
            if (i != _selectedIndex && _ls.get(i).getKey().eq(_elt.getKey())) {
                return false;
            }
        }
        return true;
    }
}
