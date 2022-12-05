package aiki.beans;

import aiki.fight.util.CategoryMult;
import code.bean.nat.NaNuSt;

public final class CategoryMultStruct extends NaNuSt {
    private final CategoryMult inst;
    public CategoryMultStruct(CategoryMult _instance) {
        inst=(_instance);
    }
    public CategoryMult getCategoryMult() {
        return inst;
    }
}
