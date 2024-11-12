package aiki.fight.util;

import code.maths.*;
import code.util.*;

public final class CategoryMults extends AbsBasicMap<CategoryMult,Rate> {
    public CategoryMults() {
    }
    public CategoryMults(CollCapacity _cap) {
        super(_cap);
    }

    @Override
    protected Rate def() {
        return Rate.zero();
    }

    @Override
    protected boolean matchKeys(CategoryMult _k, CategoryMult _e) {
        return _k.eq(_e);
    }

}
