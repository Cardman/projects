package aiki.gui.components.editor;

import code.gui.*;
import code.maths.*;
import code.util.*;
import code.util.ints.*;

public final class DisplayEntryCustSubElementEventString implements DisplayEntryCustSubElement<EditedCrudPair<String, LgInt>> {
    private final CrudGeneFormSimpleElementSub<EditedCrudPair<String, LgInt>> crud;

    public DisplayEntryCustSubElementEventString(CrudGeneFormSimpleElementSub<EditedCrudPair<String, LgInt>> _c) {
        this.crud = _c;
    }

    @Override
    public IdList<SubscribedTranslation> buildSub() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.add(new SubscribedTranslationRenamingIdCrud<LgInt>(crud));
        ids_.add(new SubscribedTranslationRenamingMidCrud<LgInt>(crud));
        ids_.add(new SubscribedTranslationRenamingPrefCrud<LgInt>(crud));
        return ids_;
    }

    @Override
    public DisplayEntryCust<Integer, EditedCrudPair<String, LgInt>> buildDisplay() {
        return new StringLgIntDisplayEntryCust();
    }

    @Override
    public Comparing<EditedCrudPair<String, LgInt>> buildCmp() {
        return new ComparingStringKey<LgInt>();
    }
}
