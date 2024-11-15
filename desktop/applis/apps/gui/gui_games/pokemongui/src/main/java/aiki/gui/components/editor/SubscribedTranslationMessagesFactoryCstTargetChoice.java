package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.moves.enums.*;
import code.util.*;

public final class SubscribedTranslationMessagesFactoryCstTargetChoice extends SubscribedTranslationMessagesFactoryCommonCst<TargetChoice> {
    @Override
    public StringMap<IdMap<TargetChoice, String>> buildMessages(FacadeGame _facade) {
        return _facade.getData().getTranslatedTargets();
    }
}
