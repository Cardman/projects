package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.initialize.*;
import code.util.*;

public final class SubscribedTranslationMessagesNbFactoryTm implements SubscribedTranslationMessagesNbFactory {
    @Override
    public SubscribedTranslation buildSub(AbsMap<Short, String> _map, AbsMap<String, String> _messages) {
        return new SubscribedTranslationMessagesNb(_map,_messages,this);
    }

    @Override
    public ShortMap<String> retrieveMap(AbstractProgramInfos _api, FacadeGame _facade) {
        return _facade.getData().getTm();
    }
}
