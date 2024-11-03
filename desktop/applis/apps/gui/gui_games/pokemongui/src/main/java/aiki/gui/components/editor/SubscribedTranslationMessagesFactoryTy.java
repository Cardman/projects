package aiki.gui.components.editor;

import aiki.facade.FacadeGame;
import code.gui.initialize.AbstractProgramInfos;
import code.util.AbsMap;
import code.util.StringMap;

public final class SubscribedTranslationMessagesFactoryTy implements SubscribedTranslationMessagesFactory {
    @Override
    public SubscribedTranslation buildSub(AbsMap<String, String> _map) {
        return new SubscribedTranslationMessages(_map,this);
    }

    @Override
    public StringMap<String> buildMessages(AbstractProgramInfos _api, FacadeGame _facade) {
        return new StringMap<String>(_facade.getData().getTranslatedTypes().getVal(_api.getLanguage()));
    }
}
