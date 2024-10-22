package aiki.db;

import aiki.fight.util.*;

public final class ChangeStringFieldCategoryMult implements ChangeStringField {
    private final CategoryMult categoryMult;

    public ChangeStringFieldCategoryMult(CategoryMult _l) {
        this.categoryMult = _l;
    }

    @Override
    public String value() {
        return categoryMult.getCategory();
    }

    @Override
    public void value(String _v) {
        categoryMult.setCategory(_v);
    }
}
