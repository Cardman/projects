package aiki.gui.components.editor;

import aiki.db.*;
import aiki.facade.FacadeGame;
import code.gui.AbsCommonFrame;
import code.gui.EditedCrudPair;
import code.gui.GeneComponentModel;
import code.gui.initialize.AbstractProgramInfos;
import code.util.AbsMap;
import code.util.IdList;
import code.util.StringMap;

public abstract class SubscribedTranslationMessagesFactoryImg implements SubscribedTranslationMessagesFactoryCommonInt<ImageArrayBaseSixtyFour> {

    private final SubscribedTranslationMessagesFactoryCoreContainer<String> container = new SubscribedTranslationMessagesFactoryCoreContainer<String>(this);

    private GeneComponentModelImg geneComponentModelImg;

    public SubscribedTranslationMessagesFactoryCoreContainer<String> getContainer() {
        return container;
    }

    @Override
    public GeneComponentModel<EditedCrudPair<String, ImageArrayBaseSixtyFour>> build(AbsCommonFrame _frame, AbstractProgramInfos _core, CrudGeneFormSubContent<EditedCrudPair<String, ImageArrayBaseSixtyFour>> _facade) {
        geneComponentModelImg = buildQuick(_frame,_core,_facade);
        return geneComponentModelImg;
    }

    @Override
    public IdList<SubscribedTranslation> all() {
        return geneComponentModelImg.getGeneComponentModelSelectKey().getSubs();
    }
    public abstract GeneComponentModelImg buildQuick(AbsCommonFrame _frame, AbstractProgramInfos _core, CrudGeneFormSubContent<EditedCrudPair<String, ImageArrayBaseSixtyFour>> _facade);
    public abstract StringMap<StringMap<String>> buildMessages(FacadeGame _facade);

    @Override
    public void delete(FacadeGame _facade, String _key) {
        all(_facade).removeKey(_key);
    }

    @Override
    public AbsMap<String, String> buildMessagesCopy(FacadeGame _facade, String _lg) {
        return new StringMap<String>(buildMessages(_facade).getVal(_lg));
    }

}
