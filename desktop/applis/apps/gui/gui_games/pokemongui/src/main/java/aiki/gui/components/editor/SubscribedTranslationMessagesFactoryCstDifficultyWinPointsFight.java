package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.game.params.enums.*;
import code.util.*;

public final class SubscribedTranslationMessagesFactoryCstDifficultyWinPointsFight extends SubscribedTranslationMessagesFactoryCommonCst<DifficultyWinPointsFight> {
    @Override
    public StringMap<IdMap<DifficultyWinPointsFight, String>> buildMessages(FacadeGame _facade) {
        return _facade.getData().getTranslatedDiffWinPts();
    }
}
