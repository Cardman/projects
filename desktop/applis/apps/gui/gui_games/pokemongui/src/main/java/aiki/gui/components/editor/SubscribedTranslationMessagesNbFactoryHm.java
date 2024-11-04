package aiki.gui.components.editor;

import aiki.facade.FacadeGame;
import code.gui.initialize.AbstractProgramInfos;
import code.util.AbsMap;
import code.util.ShortMap;

public final class SubscribedTranslationMessagesNbFactoryHm implements SubscribedTranslationMessagesNbFactory {
    @Override
    public SubscribedTranslation buildSub(AbsMap<Short, String> _map, AbsMap<String, String> _messages) {
        return new SubscribedTranslationMessagesNb(_map,_messages,this);
    }

    @Override
    public ShortMap<String> retrieveMap(AbstractProgramInfos _api, FacadeGame _facade) {
        return _facade.getData().getHm();
    }
}
