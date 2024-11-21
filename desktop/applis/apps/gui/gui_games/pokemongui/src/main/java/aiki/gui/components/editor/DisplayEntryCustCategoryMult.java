package aiki.gui.components.editor;

import aiki.fight.util.*;
import code.gui.*;
import code.maths.*;
import code.util.*;
import code.util.core.*;

public final class DisplayEntryCustCategoryMult implements DisplayEntryCust<Integer, EditedCrudPair<CategoryMult, Rate>> {
    private final AbsMap<String, String> categories;

    public DisplayEntryCustCategoryMult(AbsMap<String, String> _t) {
        this.categories = _t;
    }


    @Override
    public String display(Integer _k, EditedCrudPair<CategoryMult, Rate> _v) {
        return StringUtil.nullToEmpty(categories.getVal(_v.getKey().getCategory())) + " "+_v.getKey().getMult();
    }
}
