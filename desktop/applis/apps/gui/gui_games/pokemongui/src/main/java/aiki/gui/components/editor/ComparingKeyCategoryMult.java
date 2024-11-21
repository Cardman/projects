package aiki.gui.components.editor;

import aiki.beans.facade.comparators.*;
import aiki.fight.util.*;
import code.gui.*;
import code.maths.*;
import code.util.*;
import code.util.core.*;
import code.util.ints.*;

public final class ComparingKeyCategoryMult implements Comparing<EditedCrudPair<CategoryMult, Rate>> {

    private final AbsMap<String,String> cats;

    public ComparingKeyCategoryMult(AbsMap<String, String> _c) {
        cats = _c;
    }

    @Override
    public int compare(EditedCrudPair<CategoryMult, Rate> _one, EditedCrudPair<CategoryMult, Rate> _two) {
        return ComparatorCategoryMult.cmp(tr(_one), tr(_two));
    }

    private CategoryMult tr(EditedCrudPair<CategoryMult, Rate> _elt) {
        CategoryMult tr_ = new CategoryMult();
        tr_.setCategory(StringUtil.nullToEmpty(cats.getVal(_elt.getKey().getCategory())));
        tr_.setMult(_elt.getKey().getMult());
        return tr_;
    }
}
