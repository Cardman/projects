package aiki.gui.components.editor;

import aiki.db.*;
import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class SubscribedTranslationMessagesFactoryMiniPk extends SubscribedTranslationMessagesFactoryImgPk {
    private GeneComponentModelMiniPk geneComponentModelMiniPk;
    @Override
    public StringMap<ImageArrayBaseSixtyFour> all(FacadeGame _facade) {
        return _facade.getData().getMiniPk();
    }

    @Override
    public GeneComponentModel<EditedCrudPair<String, ImageArrayBaseSixtyFour>> build(AbsCommonFrame _frame, AbstractProgramInfos _core, CrudGeneFormSubContent<EditedCrudPair<String, ImageArrayBaseSixtyFour>> _facade) {
        geneComponentModelMiniPk = new GeneComponentModelMiniPk(_frame,_core,_facade.getFacadeGame(),_facade.getSubscription());
        return geneComponentModelMiniPk;
    }

    @Override
    public IdList<SubscribedTranslation> all() {
        return geneComponentModelMiniPk.getGeneComponentModelSelectKey().getSubs();
    }
}
