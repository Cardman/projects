package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.util.*;
import code.gui.DisplayEntryCust;
import code.gui.EditedCrudPair;
import code.gui.initialize.AbstractProgramInfos;
import code.maths.Rate;
import code.util.AbsMap;
import code.util.IdList;
import code.util.StringMap;
import code.util.ints.Comparing;

public final class DisplayEntryCustSubElementCategoryMult implements DisplayEntryCustSubElement<EditedCrudPair<CategoryMult, Rate>> {
    private final AbsMap<String, String> categories;
    private final SubscribedTranslationMessagesFactory factoryCa;

    public DisplayEntryCustSubElementCategoryMult(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub) {
        factoryCa = _sub.getFactoryCa();
        this.categories = factoryCa.getContainer().buildMessages(_fact,_facade);
    }


    @Override
    public IdList<SubscribedTranslation> buildSub() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.add(new SubscribedTranslationMessages<String>(categories, factoryCa, new StringMap<String>()));
        return ids_;
    }

    @Override
    public DisplayEntryCust<Integer, EditedCrudPair<CategoryMult, Rate>> buildDisplay() {
        return new DisplayEntryCustCategoryMult(categories);
    }

    @Override
    public Comparing<EditedCrudPair<CategoryMult, Rate>> buildCmp() {
        return new ComparingKeyCategoryMult(categories);
    }
}
