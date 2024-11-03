package aiki.gui.components.editor;

import aiki.facade.FacadeGame;
import code.gui.initialize.AbstractProgramInfos;
import code.util.*;

public final class SubscribedTranslationMessagesFactoryPk implements SubscribedTranslationMessagesFactory {
    @Override
    public SubscribedTranslation buildSub(AbsMap<String, String> _map) {
        return new SubscribedTranslationPkMessages(_map);
    }

    @Override
    public StringMap<String> buildMessages(AbstractProgramInfos _api, FacadeGame _facade) {
        return new StringMap<String>(_facade.getData().getTranslatedPokemon().getVal(_api.getLanguage()));
    }
}
