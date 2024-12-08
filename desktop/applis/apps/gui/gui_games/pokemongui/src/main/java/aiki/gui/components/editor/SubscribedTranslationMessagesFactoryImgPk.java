package aiki.gui.components.editor;

import aiki.db.*;
import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.util.*;

public abstract class SubscribedTranslationMessagesFactoryImgPk extends SubscribedTranslationMessagesFactoryImg {
    @Override
    public GeneComponentModelImg buildQuick(AbsCommonFrame _frame, AbstractProgramInfos _core, CrudGeneFormSubContent<EditedCrudPair<String, ImageArrayBaseSixtyFour>> _facade) {
        return new GeneComponentModelImg(_frame,_core,_facade.getFacadeGame(),_facade.getSubscription(), _facade.getSubscription().getFactoryPk(), this);
    }
    @Override
    public void removeOpenSub(CrudGeneFormSubContent<EditedCrudPair<String, ImageArrayBaseSixtyFour>> _base) {
        _base.removeOpenSub();
    }

    @Override
    public StringMap<StringMap<String>> buildMessages(FacadeGame _facade) {
        return _facade.getData().getTranslatedPokemon();
    }
}
