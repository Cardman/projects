package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;
import code.util.ints.*;

public final class DisplayEntryCustSubElementTypesDuoElt implements DisplayEntryCustSubElement<TypesDuo> {
    private final AbsMap<String, String> types;
    private final SubscribedTranslationMessagesFactory factoryTy;

    public DisplayEntryCustSubElementTypesDuoElt(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub) {
        factoryTy = _sub.getFactoryTy();
        this.types = factoryTy.getContainer().buildMessages(_fact,_facade);
    }


    @Override
    public IdList<SubscribedTranslation> buildSub() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.add(new SubscribedTranslationMessages<String>(types,factoryTy, new StringMap<String>()));
        return ids_;
    }

    @Override
    public DisplayEntryCust<Integer, TypesDuo> buildDisplay() {
        return new DisplayEntryCustTypeDuoElt(types);
    }

    @Override
    public Comparing<TypesDuo> buildCmp() {
        return new ComparingTypesDuo(types);
    }
}
