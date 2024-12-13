package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.game.params.enums.*;
import code.util.*;

public final class SubscribedTranslationMessagesFactoryCstDifficultyModelLaw extends SubscribedTranslationMessagesFactoryCommonCst<DifficultyModelLaw> {
    @Override
    public StringMap<IdMap<DifficultyModelLaw, String>> buildMessages(FacadeGame _facade) {
        return _facade.getData().getTranslatedDiffModelLaw();
    }
}
