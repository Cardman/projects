package aiki.gui.components.editor;

import aiki.fight.util.*;

public final class CategoryMultTechnicalCopier implements AbsTechnicalCopier<CategoryMult> {
    @Override
    public CategoryMult copy(CategoryMult _e) {
        CategoryMult cp_ = new CategoryMult();
        cp_.setCategory(_e.getCategory());
        cp_.setMult(_e.getMult());
        return cp_;
    }
}
