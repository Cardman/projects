package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.maths.*;
import code.util.*;
import code.util.ints.*;

public final class DisplayEntryCustSubElementTypesDuo implements DisplayEntryCustSubElement<EditedCrudPair<TypesDuo, Rate>> {
    private final AbsMap<String, String> types;
    private final SubscribedTranslationMessagesFactory factoryTy;

    public DisplayEntryCustSubElementTypesDuo(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub) {
        factoryTy = _sub.getFactoryTy();
        this.types = factoryTy.buildMessages(_fact,_facade);
    }


    @Override
    public IdList<SubscribedTranslation> buildSub() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.add(new SubscribedTranslationMessages<String>(types,factoryTy, new StringMap<String>()));
        return ids_;
    }

    @Override
    public DisplayEntryCust<Integer, EditedCrudPair<TypesDuo, Rate>> buildDisplay() {
        return new DisplayEntryCustTypeDuo(types);
    }

    @Override
    public Comparing<EditedCrudPair<TypesDuo, Rate>> buildCmp() {
        return new ComparingKeyTypesDuo(types);
    }
}
