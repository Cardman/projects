package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;

public final class GeneComponentModelMiniPk extends GeneComponentModelImg{
    public GeneComponentModelMiniPk(AbsCommonFrame _fr, AbstractProgramInfos _core, FacadeGame _facade, SubscribedTranslationList _sub) {
        super(_fr, _core, _facade, _sub);
    }

    @Override
    protected void buildSelector(int _select) {
        SubscribedTranslationMessagesFactoryPk factoryPk_ = getSubscribedTranslationList().getFactoryPk();
        SubscribedTranslationMessagesFactoryMiniPk factoryMiniPk_ = getSubscribedTranslationList().getFactoryMiniPk();
        buildKey(_select,factoryPk_,factoryMiniPk_.all(getFacade()).getKeys());
    }

}
