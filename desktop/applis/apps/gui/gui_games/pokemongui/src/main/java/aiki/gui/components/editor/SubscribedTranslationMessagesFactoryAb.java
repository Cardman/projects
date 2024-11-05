package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.abilities.AbilityData;
import code.util.*;

public final class SubscribedTranslationMessagesFactoryAb extends SubscribedTranslationMessagesFactoryCommonParam<AbilityData> {

    @Override
    public StringMap<StringMap<String>> buildMessages(FacadeGame _facade) {
        return _facade.getData().getTranslatedAbilities();
    }

    @Override
    public void rename(FacadeGame _facade, String _previous, String _next) {
        _facade.getData().renameAbility(_previous,_next);
    }

    @Override
    public StringMap<AbilityData> all(FacadeGame _facade) {
        return _facade.getData().getAbilities();
    }
}
