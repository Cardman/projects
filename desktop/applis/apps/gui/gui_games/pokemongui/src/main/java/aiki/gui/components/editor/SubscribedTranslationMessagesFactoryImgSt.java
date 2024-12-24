package aiki.gui.components.editor;

import aiki.db.*;
import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class SubscribedTranslationMessagesFactoryImgSt extends SubscribedTranslationMessagesFactoryImg {
    @Override
    public StringMap<ImageArrayBaseSixtyFour> all(FacadeGame _facade) {
        return _facade.getData().getAnimStatus();
    }

    @Override
    public GeneComponentModelImg buildQuick(AbsCommonFrame _frame, AbstractProgramInfos _core, CrudGeneFormSubContent _facade) {
        return new GeneComponentModelImg(_frame,_core,_facade.getFacadeGame(),_facade.getSubscription(), _facade.getSubscription().getFactorySt(), this);
    }
    @Override
    public StringMap<StringMap<String>> buildMessages(FacadeGame _facade) {
        return _facade.getData().getTranslatedStatus();
    }
}
