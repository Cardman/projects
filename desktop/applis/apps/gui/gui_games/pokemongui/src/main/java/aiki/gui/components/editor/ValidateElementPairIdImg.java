package aiki.gui.components.editor;

import aiki.db.*;
import code.gui.*;
import code.util.*;

public final class ValidateElementPairIdImg implements IntValidateElementAdd<EditedCrudPair<String, ImageArrayBaseSixtyFour>> {
    @Override
    public boolean valid(CustList<EditedCrudPair<String, ImageArrayBaseSixtyFour>> _ls, EditedCrudPair<String, ImageArrayBaseSixtyFour> _elt) {
        return DataBase.isCorrectIdentifier(_elt.getKey()) && _elt.getValue().getImage().length > 0;
    }
}
