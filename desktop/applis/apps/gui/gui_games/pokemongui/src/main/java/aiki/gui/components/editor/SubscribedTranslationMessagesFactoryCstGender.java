package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.map.pokemon.enums.*;
import code.util.*;

public final class SubscribedTranslationMessagesFactoryCstGender extends SubscribedTranslationMessagesFactoryCommonCst<Gender> {
    @Override
    public StringMap<IdMap<Gender, String>> buildMessages(FacadeGame _facade) {
        return _facade.getData().getTranslatedGenders();
    }
}
